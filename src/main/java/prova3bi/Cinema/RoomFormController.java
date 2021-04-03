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
import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Domain.Entidades.TipoSala;
import prova3bi.Cinema.Exception.ValidateException;

public class RoomFormController implements Initializable{

	@FXML
	private ComboBox<TipoSala> cbType;

	@FXML
	private TextField txtRoomNumber;

	@FXML
	private TextField txtNumberSeats;

	@FXML
	private Label txtTypeLabel;

	@FXML
	private Label txtSeatsLabel;

	@FXML
	private Label txtClassLabel;

	private Sala entity;

	@FXML
	void switchSubmit(ActionEvent event) {
		try {
			entity = getFormData();
		} catch (ValidateException e) {
			setErrorMessages(e.getErrors());
		}
	}

	private Sala getFormData() {
		// Sala class = new Sala();
		ValidateException exception = new ValidateException("Validation error");
		
		if (cbType.getValue() == null) {
			exception.addError("type", "!");
		} else {
			// section.setPacienteid(cbPatient.getValue());
		}

		if (txtRoomNumber.getText() == null || txtRoomNumber.getText().trim().equals("")) {
			exception.addError("seats", "!");
		}
		// class.setDescricao(txtNumverClass.getText());

		if (txtNumberSeats.getText() == null || txtNumberSeats.getText().trim().equals("")) {
			exception.addError("class", "!");
		}
		// class.setDescricao(txtNumberSeats.getText());

		return null;
	}

	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		txtTypeLabel.setText((fields.contains("type") ? errors.get("type") : ""));
		txtSeatsLabel.setText((fields.contains("seats") ? errors.get("seats") : ""));
		txtClassLabel.setText((fields.contains("class") ? errors.get("class") : ""));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbType.getItems().setAll(TipoSala.values());
	}

}
