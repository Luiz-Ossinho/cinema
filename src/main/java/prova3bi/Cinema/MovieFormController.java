package prova3bi.Cinema;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import prova3bi.Cinema.Domain.Entidades.Filme;
import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Domain.Entidades.TrilhaSonora;
import prova3bi.Cinema.Domain.Interfaces.Services.IMovieService;
import prova3bi.Cinema.Domain.Validations.Error;
import prova3bi.Cinema.Domain.Validations.ErrorList;
import prova3bi.Cinema.Services.UnitFactory;
import prova3bi.Cinema.Singletons.MovieHolder;
import prova3bi.Cinema.Singletons.RoomHolder;
import prova3bi.Cinema.Util.Utils;

public class MovieFormController implements Initializable {

	@FXML
	private ComboBox<TrilhaSonora> cbSoundTrack;

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
		
		var movie = new Filme(title, "", synopsis, audioTrack);
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
		cbSoundTrack.getItems().setAll(TrilhaSonora.values());
	}

}
