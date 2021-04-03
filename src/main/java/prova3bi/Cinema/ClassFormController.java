package prova3bi.Cinema;

import java.util.Map;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Exception.ValidateException;

public class ClassFormController {

	@FXML
	private TextField txtType;

	@FXML
	private TextField txtNumverClass;

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

		if (txtType.getText() == null || txtType.getText().trim().equals("")) {
			exception.addError("type", "!");
		}
		// class.setDescricao(txtType.getText());

		if (txtNumverClass.getText() == null || txtNumverClass.getText().trim().equals("")) {
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

}
