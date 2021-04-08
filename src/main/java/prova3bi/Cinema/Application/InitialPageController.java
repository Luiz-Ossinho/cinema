package prova3bi.Cinema.Application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class InitialPageController implements Initializable {

	@FXML
	private void switchMovieList() {
		App.setRoot("SessionList");
	}
	
	@FXML
	private void switchSignIn(){
		App.setRoot("SignIn");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	

	@FXML
	private HBox backgroundScene;
}
