package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class InitialPageController implements Initializable {

	@FXML
	private HBox backgroundScene;

	@FXML
	private void switchMovieList() throws IOException {
		App.setRoot("SectionList");
	}
	
	@FXML
	private void switchSignIn() throws IOException {
		App.setRoot("SignIn");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
