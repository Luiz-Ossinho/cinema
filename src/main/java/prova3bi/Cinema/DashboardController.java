package prova3bi.Cinema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import prova3bi.Cinema.Domain.Entidades.Login;

public class DashboardController implements Initializable {

	private Login userSigned;
	
	public DashboardController(Login log) {
		this.userSigned = log;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(userSigned.toString());
	}

}
