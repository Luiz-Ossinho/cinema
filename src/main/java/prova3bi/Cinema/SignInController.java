package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class SignInController implements Initializable {
	
	@FXML
	private void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("InitialPage");
	}
	
	@FXML
	private void switchLogin() throws IOException {
		App.setRoot("Dashboard");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
