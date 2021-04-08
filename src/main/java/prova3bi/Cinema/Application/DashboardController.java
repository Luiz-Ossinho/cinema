package prova3bi.Cinema.Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import prova3bi.Cinema.Application.Singletons.LoginHolder;
import prova3bi.Cinema.Domain.Entities.Login;

public class DashboardController implements Initializable {

	@FXML
	void switchAddEmployee(MouseEvent event) throws IOException {
		App.setRoot("EmployeeAdminForm");
	}

	@FXML
	void switchAddSection(MouseEvent event) throws IOException {
		App.setRoot("SessionAdminForm");
	}

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		LoginHolder.getInstance().Reset();
		App.setRoot("InitialPage");
	}

	@FXML
	void switchTickets(MouseEvent event) throws IOException {
		App.setRoot("TicketListAuthorization");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		signedUser = LoginHolder.getInstance().getLogin();

		if (signedUser.nivelPermissao.equals(Login.Level.Atendente)) {
			buttonEmp.setStyle("-fx-opacity: 0.9;");
			buttonEmp.setDisable(true);

			buttonSess.setStyle("-fx-opacity: 0.9;");
			buttonSess.setDisable(true);
		}
	}

	private Login signedUser;

	@FXML
	private Label levelAuthorization;

	@FXML
	private Pane buttonEmp;

	@FXML
	private Pane buttonSess;

}
