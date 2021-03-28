package prova3bi.Cinema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

public class MovieController implements Initializable {

	@FXML
	private ImageView posterMovie;

	@FXML
	private Label descriptionMovie;

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
