package prova3bi.Cinema.Application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import prova3bi.Cinema.Application.Singletons.MovieHolder;
import prova3bi.Cinema.Application.Singletons.RoomHolder;
import prova3bi.Cinema.Domain.Entities.Movie;
import prova3bi.Cinema.Domain.Interfaces.Services.IMovieService;
import prova3bi.Cinema.Domain.Validations.ErrorList;
import prova3bi.Cinema.Services.UnitFactory;
import prova3bi.Cinema.Util.Utils;

public class MovieFormController implements Initializable {

	@FXML
	void switchSubmit(ActionEvent event) {
		var errors = CreateMovie();
		if (!errors.isEmpty())
			setErrorMessages(errors);
		else {
			PersistMovie();
			Utils.currentStage(event).close();
		}
	}

	private ErrorList CreateMovie() {
		var errors = new ErrorList();
		var audioTrack = cbSoundTrack.getValue();
		var title = txtTitle.getText();
		var synopsis = txtSynopsis.getText();

		var movie = new Movie(title, "", synopsis, audioTrack);
		MovieHolder.getInstance().setMovie(movie);

		return errors.addAll(movie.isValid());
	}

	private void PersistMovie() {
		var holderInstance = MovieHolder.getInstance();
		var movie = holderInstance.getMovie();
		var updatedMovie = movieService.Add(movie);
		holderInstance.getObsList().add(updatedMovie);
		holderInstance.setMovie(updatedMovie);
	}

	private void setErrorMessages(ErrorList errors) {
		RoomHolder.getInstance().ResetRoom();
		cbSoundTrackLabel.setText(errors.GetErrorLabel("audioTrack"));
		titleLabel.setText(errors.GetErrorLabel("title"));
		synopsisLabel.setText(errors.GetErrorLabel("synopsis"));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		movieService = UnitFactory.getMovieService();
		cbSoundTrack.getItems().setAll(Movie.Soundtrack.values());
	}

	@FXML
	private ComboBox<Movie.Soundtrack> cbSoundTrack;

	@FXML
	private TextField txtSynopsis;

	@FXML
	private TextField txtTitle;

	@FXML
	private Label cbSoundTrackLabel;

	@FXML
	private Label titleLabel;

	@FXML
	private Label synopsisLabel;

	private IMovieService movieService;
}
