package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
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

public class EmployeeAdminFormController implements Initializable{

	@FXML
	private TextField txtUser;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Label userLabel;

	@FXML
	private Label passLabel;

	private ILoginService service;

	@FXML
	void onSubmit(ActionEvent event) {
		var username = txtUser.getText();
		var password = txtPassword.getText();

		if (ValidationHelper.isNullOrEmpty(username) || ValidationHelper.isNullOrEmpty(password)) {
			Alerts.showAlert("Ops... aconteceu algum problema!", "Pelo visto você esqueceu de inserir os dados.",
					"Preencha os campos para continuar!", AlertType.INFORMATION);
			return;
		}

		addNewEmployee(username, password);

	}

	private void addNewEmployee(String username, String password) {
		Login log = new Login(username, password, PermissionLevel.Atendente);
		LoginHolder.getInstance().setLogin(service.Add(log));
		Alerts.showAlert("Cadastrado!", "Mais um integrante da familias Cinenow!", "Seja bem vindo " + username + "!",
				AlertType.INFORMATION);
	}

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("Dashboard");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = UnitFactory.getLoginService();
	}

}
