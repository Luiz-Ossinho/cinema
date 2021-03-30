package prova3bi.Cinema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import prova3bi.Cinema.Domain.Entidades.Login;

public class DashboardController implements Initializable {

	private Login userSigned;

	public void setUserSigned(Login userSigned) {
		this.userSigned = userSigned;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(userSigned.toString());
	}

}
