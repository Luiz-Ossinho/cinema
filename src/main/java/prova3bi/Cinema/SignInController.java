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
import prova3bi.Cinema.Domain.Entidades.Login;
import prova3bi.Cinema.Domain.Entidades.NivelPermissao;
import prova3bi.Cinema.Domain.Interfaces.Services.ILoginService;
import prova3bi.Cinema.Services.UnitFactory;
import prova3bi.Cinema.Util.Alerts;

public class SignInController implements Initializable {

	@FXML
	private TextField textField;

	@FXML
	private PasswordField passField;

	private ILoginService service;

	private Login signedUser;
	
	@FXML
	private void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("InitialPage");
	}

	@FXML
	private void switchLogin() throws IOException {
		if (textField.getText().isEmpty() || passField.getText().isEmpty()) {
			Alerts.showAlert("Ops... aconteceu algum problema!", "Pelo visto você esqueceu de inserir os dados.",
					"Preencha os campos para continuar!", AlertType.INFORMATION);
		} else {
			String text1 = textField.getText();
			String text2 = passField.getText();
			if (!service.IsFirstLogin()) {
				Login log = new Login(text1, text2, NivelPermissao.Admin);
				service.Add(log);
				this.signedUser = log;
			} else {
				Login log = service.VerificarUsuario(text1, text2);

				if (log != null) {
					App.setRoot("Dashboard");
					this.signedUser = log;

				} else {
					Alerts.showAlert("Ops... aconteceu algum problema!",
							"Aparentemente o usuário ou a senha esta incorreta!", "Tente novamente!", AlertType.ERROR);
				}

			}
			DashboardController controller = new DashboardController();
			controller.setUserSigned(signedUser);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = UnitFactory.getLoginService();
	}

}
