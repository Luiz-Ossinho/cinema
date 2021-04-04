package prova3bi.Cinema;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Domain.Entidades.TipoSala;
import prova3bi.Cinema.Domain.Validations.Error;
import prova3bi.Cinema.Domain.Validations.ErrorList;
import prova3bi.Cinema.Singletons.RoomHolder;
import prova3bi.Cinema.Util.Utils;

public class RoomFormController implements Initializable{

	@FXML
	private HBox RoomForm;
	
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
	private Label txtRoomLabel;

	@FXML
	void switchSubmit(ActionEvent event) {
			var errors = SaveRoom();
			if(!errors.isEmpty())
				setErrorMessages(errors);
			else
				Utils.currentStage(event).close();
	}

	private ErrorList SaveRoom() {
		var errors = new ErrorList();
		var tipo = cbType.getValue();
		int RoomNumber = -1;
		int NumberSeats = -1;
		
		try {
			RoomNumber = Integer.parseInt(txtRoomNumber.getText());
		} catch (NumberFormatException formatException) {
			errors.add(new Error("RoomNumber", "Incorrect format"));
		}
		try {
			NumberSeats = Integer.parseInt(txtNumberSeats.getText());
		} catch (NumberFormatException formatException) {
			errors.add(new Error("SeatsNumber", "Incorrect format"));
		}
		
		var room = new Sala(tipo, NumberSeats, RoomNumber);
		
		RoomHolder.getInstance().setRoom(room);
		
		return errors.addAll(room.isValid());
	}

	private void setErrorMessages(ErrorList errors) {
		RoomHolder.getInstance().ResetRoom();
		txtTypeLabel.setText(errors.GetErrorLabel("Type"));
		txtSeatsLabel.setText(errors.GetErrorLabel("SeatsNumber"));
		txtRoomLabel.setText(errors.GetErrorLabel("RoomNumber"));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbType.getItems().setAll(TipoSala.values());
	}

}
