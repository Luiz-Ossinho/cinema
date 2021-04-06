package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import prova3bi.Cinema.Domain.Entities.Login;
import prova3bi.Cinema.Domain.Entities.PermissionLevel;
import prova3bi.Cinema.Domain.Interfaces.Services.ILoginService;
import prova3bi.Cinema.Domain.Validations.ValidationHelper;
import prova3bi.Cinema.Services.UnitFactory;
import prova3bi.Cinema.Singletons.LoginHolder;
import prova3bi.Cinema.Util.Alerts;

public class SignInController implements Initializable {

	@FXML
	private TextField textField;

	@FXML
	private PasswordField passField;

	private ILoginService service;

	@FXML
	private void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("InitialPage");
	}

	@FXML
	private void switchLogin() throws IOException {
		var username = textField.getText();
		var password = passField.getText();

		if (ValidationHelper.isNullOrEmpty(username) || ValidationHelper.isNullOrEmpty(password)) {
			Alerts.showAlert("Ops... aconteceu algum problema!", "Pelo visto você esqueceu de inserir os dados.",
					"Preencha os campos para continuar!", AlertType.INFORMATION);
			return;
		}

		if (service.IsFirstLogin())
			FirstLogin(username, password);
		else
			UsualLogin(username, password);
	}
	
	private void FirstLogin(String username, String password){
		Login log = new Login(username, password, PermissionLevel.Admin);
		LoginHolder.getInstance().setLogin(service.Add(log));
		GoToDashboard();
	}
	
	private void UsualLogin(String username, String password) {
		Login log = service.VerificarUsuario(username, password);
		if (log != null) {
			LoginHolder.getInstance().setLogin(log);
			GoToDashboard();
		} else 
			Alerts.showAlert("Ops... aconteceu algum problema!",
					"Aparentemente o usuário ou a senha esta incorreta!", "Tente novamente!", AlertType.ERROR);
	}
	
	private void GoToDashboard() {
		try {
			App.setRoot("Dashboard");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = UnitFactory.getLoginService();
	}

}