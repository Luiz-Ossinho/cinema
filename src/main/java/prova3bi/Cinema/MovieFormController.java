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
import prova3bi.Cinema.Domain.Entidades.TrilhaSonora;

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

	private Filme entity;

	@FXML
	void switchSubmit(ActionEvent event) {
//		try {
//			entity = getFormData();
//		} catch (ValidateException e) {
//			setErrorMessages(e.getErrors());
//		}
	}

//	private Filme getFormData() {
//		// Filme movie = new Filme();
//		ValidateException exception = new ValidateException("Validation error");
//
//		if (txtTitle.getText() == null || txtTitle.getText().trim().equals("")) {
//			exception.addError("title", "!");
//		}
//		// movie.setDescricao(txtTitle.getText());
//
//		if (txtSynopsis.getText() == null || txtSynopsis.getText().trim().equals("")) {
//			exception.addError("synopsis", "!");
//		}
//		// movie.setDescricao(txtSynopsis.getText());
//
//		if (cbSoundTrack.getValue() == null) {
//			exception.addError("soundtrack", "!");
//		} else {
//			// movie.setPacienteid(cbPatient.cbSoundTrack());
//		}
//
//		return null;
//	}

	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		cbSoundTrackLabel.setText((fields.contains("soundtrack") ? errors.get("soundtrack") : ""));
		titleLabel.setText((fields.contains("title") ? errors.get("title") : ""));
		synopsisLabel.setText((fields.contains("synopsis") ? errors.get("synopsis") : ""));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbSoundTrack.getItems().setAll(TrilhaSonora.values());
	}

}
