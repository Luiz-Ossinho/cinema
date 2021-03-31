package prova3bi.Cinema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import prova3bi.Cinema.Domain.Entidades.Login;
import prova3bi.Cinema.Singletons.LoginHolder;

public class DashboardController implements Initializable {

	private Login signedUser;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		signedUser = LoginHolder.getInstance().getLogin();
	}

}
