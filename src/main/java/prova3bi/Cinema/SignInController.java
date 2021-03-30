package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import prova3bi.Cinema.Services.LoginService;
import prova3bi.Cinema.Util.Alerts;

public class SignInController implements Initializable {

	@FXML
	private TextField textField;

	@FXML
	private PasswordField passField;

	private LoginService service;

	@FXML
	private void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("InitialPage");
	}

	@FXML
	private void switchLogin() throws IOException {
		if (!textField.getText().isEmpty() || !passField.getText().isEmpty()) {
			if (service.VerificarUsuario(textField.getText(), passField.getText()) != null) {
				App.setRoot("Dashboard");
			} else {
				Alerts.showAlert("Ops... aconteceu algum problema!",
						"Aparentemente o usuário ou a senha esta incorreta!", "Tente novamente!", AlertType.ERROR);
			}
		} else {
			Alerts.showAlert("Ops... aconteceu algum problema!", "Pelo visto você esqueceu de inserir os dados.",
					"Preencha os campos para continuar!", AlertType.INFORMATION);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
