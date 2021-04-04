package prova3bi.Cinema;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EmployeeAdminFormController {

	@FXML
	private TextField txtUser;

	@FXML
	private TextField txtPassword;

	@FXML
	private Label userLabel;

	@FXML
	private Label passLabel;

	@FXML
	void onSubmit(ActionEvent event) {

	}

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("Dashboard");
	}

}
