package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import prova3bi.Cinema.Domain.Entidades.Login;
import prova3bi.Cinema.Singletons.LoginHolder;

public class DashboardController implements Initializable {

	private Login signedUser;

	@FXML
	private Label levelAuthorization;

	@FXML
	void switchAddEmployee(MouseEvent event) throws IOException {
		App.setRoot("EmployeeAdminForm");
	}

	@FXML
	void switchAddSection(MouseEvent event) throws IOException {
		App.setRoot("SectionAdminForm");
	}

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		LoginHolder.getInstance().ResetLogin();
		App.setRoot("InitialPage");
	}

	@FXML
	void switchTickets(MouseEvent event) throws IOException {
		App.setRoot("TicketListAuthorization");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		signedUser = LoginHolder.getInstance().getLogin();
	}

}
