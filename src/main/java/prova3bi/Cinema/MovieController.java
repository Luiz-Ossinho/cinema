package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;

public class MovieController implements Initializable {

	private Integer idMovie;
	
	@FXML
	private ImageView posterMovie;

	@FXML
	private Label descriptionMovie;

	@FXML
	void switchGoToSession(MouseEvent event) throws IOException {
		
		GoToSelectedSession();
	}

	private void GoToSelectedSession() {
		try {
			App.setRoot("SelectedSession");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setData(Movie movie) {
		Image image = new Image(getClass().getResourceAsStream(movie.getImageMovie()));
		posterMovie.setImage(image);
		descriptionMovie.setText(movie.getAbout());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		descriptionMovie.setWrapText(true);
		descriptionMovie.setTextAlignment(TextAlignment.JUSTIFY);
	}

}
