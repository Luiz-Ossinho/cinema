package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prova3bi.Cinema.Domain.Entidades.Filme;
import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Domain.Entidades.Sessao;
import prova3bi.Cinema.Exception.ValidateException;

public class SectionAdminFormController implements Initializable {

	@FXML
	private HBox sectionContainer;

	@FXML
	private TextField txtInitialTime;

	@FXML
	private TextField txtFinalTime;

	@FXML
	private TextField txtPrice;

	@FXML
	private ComboBox<Sala> cbClass;

	@FXML
	private ComboBox<Filme> cbMovie;

	@FXML
	private Label initialTimeLabel;

	@FXML
	private Label finalTimeLabel;

	@FXML
	private Label priceLabel;

	@FXML
	private Label cbClassLabel;

	@FXML
	private Label cbMovieLabel;

	private Sessao entity;

	@FXML
	void onSubmit(ActionEvent event) {
		try {
			entity = getFormData();
		} catch (ValidateException e) {
			setErrorMessages(e.getErrors());
		} /*
			 * catch (BDExeption e) { Alerts.showAlert("Error saving object", null,
			 * e.getMessage(), AlertType.ERROR); }
			 */
	}

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("Dashboard");
	}

	@FXML
	void switchMovieModal(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(SectionAdminFormController.class.getResource("MovieForm.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Um novo filme ?!");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setResizable(false);
		stage.initOwner(((Node) event.getSource()).getScene().getWindow());
		stage.showAndWait();
	}

	@FXML
	void switchRoomModal(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(SectionAdminFormController.class.getResource("RoomForm.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Uma nova sala ?!");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setResizable(false);
		stage.initOwner(((Node) event.getSource()).getScene().getWindow());
		stage.showAndWait();
	}

	private Sessao getFormData() {
		// Sessao section = new Sessao();
		ValidateException exception = new ValidateException("Validation error");

		if (txtInitialTime.getText() == null || txtInitialTime.getText().trim().equals("")) {
			exception.addError("itime", "Field can't be empty");
		}
		// section.setDescricao(txtDescription.getText());

		if (txtFinalTime.getText() == null || txtFinalTime.getText().trim().equals("")) {
			exception.addError("ftime", "Field can't be empty");
		}
		// section.setDescricao(txtDescription.getText());

		if (txtPrice.getText() == null || txtPrice.getText().trim().equals("")) {
			exception.addError("price", "Field can't be empty");
		}
		// section.setDescricao(txtDescription.getText());

		if (cbClass.getValue() == null) {
			exception.addError("class", "Field can't be empty");
		} else {
			// section.setPacienteid(cbPatient.getValue());
		}

		if (cbMovie.getValue() == null) {
			exception.addError("movie", "Field can't be empty");
		} else {
			// section.setPacienteid(cbPatient.getValue());
		}

		return null;
	}

	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		initialTimeLabel.setText((fields.contains("itime") ? errors.get("itime") : ""));
		finalTimeLabel.setText((fields.contains("ftime") ? errors.get("ftime") : ""));
		priceLabel.setText((fields.contains("price") ? errors.get("price") : ""));
		cbClassLabel.setText((fields.contains("class") ? errors.get("class") : ""));
		cbMovieLabel.setText((fields.contains("movie") ? errors.get("movie") : ""));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
