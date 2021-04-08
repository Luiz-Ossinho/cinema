package prova3bi.Cinema.Application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import prova3bi.Cinema.Application.Singletons.RoomHolder;
import prova3bi.Cinema.Domain.Entities.Room;
import prova3bi.Cinema.Domain.Interfaces.Services.IRoomService;
import prova3bi.Cinema.Domain.Validations.ErrorList;
import prova3bi.Cinema.Services.UnitFactory;
import prova3bi.Cinema.Util.Utils;

public class RoomFormController implements Initializable {

	@FXML
	void switchSubmit(ActionEvent event) {
		var errors = CreateRoom();
		if (!errors.isEmpty())
			setErrorMessages(errors);
		else {
			PersistRoom();
			Utils.currentStage(event).close();
		}
	}

	private ErrorList CreateRoom() {
		var errors = new ErrorList();
		var tipo = cbType.getValue();
		var RoomNumberStr = txtRoomNumber.getText();
		var NumbeSeatsrStr = txtNumberSeats.getText();
		int RoomNumber = Utils.TryParseValue(Utils.integerParser, RoomNumberStr, errors, "RoomNumber");
		int NumberSeats = Utils.TryParseValue(Utils.integerParser, NumbeSeatsrStr, errors, "SeatsNumber");

		var room = new Room(tipo, NumberSeats, RoomNumber);

		RoomHolder.getInstance().setRoom(room);

		return errors.addAll(room.isValid());
	}

	private void PersistRoom() {
		var holderInstance = RoomHolder.getInstance();
		var room = holderInstance.getRoom();
		var updatedRoom = roomService.Add(room);
		holderInstance.getObsList().add(updatedRoom);
		holderInstance.setRoom(updatedRoom);
	}

	private void setErrorMessages(ErrorList errors) {
		RoomHolder.getInstance().ResetRoom();
		txtTypeLabel.setText(errors.GetErrorLabel("Type"));
		txtSeatsLabel.setText(errors.GetErrorLabel("SeatsNumber"));
		txtRoomLabel.setText(errors.GetErrorLabel("RoomNumber"));
	}

	public void initializeTypeComboBox() {
		cbType.getItems().setAll(Room.Type.values());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeTypeComboBox();
		roomService = UnitFactory.getRoomService();
	}

	@FXML
	private HBox RoomForm;

	@FXML
	private ComboBox<Room.Type> cbType;

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

	private IRoomService roomService;
}
