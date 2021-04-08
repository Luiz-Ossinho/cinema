package prova3bi.Cinema.Application;

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
import prova3bi.Cinema.Application.Singletons.SessionHolder;
import prova3bi.Cinema.Domain.Entities.Session;

public class MovieController implements Initializable {

	@FXML
	void switchGoToSession(MouseEvent event) throws IOException {
		GoToSelectedSession();
	}

	private void GoToSelectedSession() {
		SessionHolder.getInstance().setSession(this.session);
		App.setRoot("SelectedSession");
	}

	public void setData(Session session) {
		this.session = session;
		loadMovieInfo();
	}

	private void loadMovieInfo() {
		var image = new Image(session.filme.getPosterStream());
		posterMovie.setImage(image);
		descriptionMovie.setText(session.filme.title + " - " + session.filme.audioTrack.name());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		descriptionMovie.setWrapText(true);
		descriptionMovie.setTextAlignment(TextAlignment.JUSTIFY);
	}

	@FXML
	private ImageView posterMovie;

	@FXML
	private Label descriptionMovie;

	private Session session;
}
