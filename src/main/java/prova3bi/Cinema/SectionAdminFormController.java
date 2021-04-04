package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import prova3bi.Cinema.Domain.Entidades.Filme;
import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Domain.Entidades.Sessao;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IRoomRepository;
import prova3bi.Cinema.Domain.Validations.Error;
import prova3bi.Cinema.Domain.Validations.ErrorList;
import prova3bi.Cinema.Services.UnitFactory;
import prova3bi.Cinema.Singletons.RoomHolder;
import prova3bi.Cinema.Singletons.SessionHolder;

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
	private Label cbRoomLabel;

	@FXML
	private Label cbMovieLabel;

	private IRoomRepository roomRepo;
	
	@FXML
	void onSubmit(ActionEvent event) {
		var errors = SaveSession();
		if(!errors.isEmpty())
			setErrorMessages(errors);
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

//	private Sessao getFormData() {
//		// Sessao section = new Sessao();
//		ValidateException exception = new ValidateException("Validation error");
//
//		if (txtInitialTime.getText() == null || txtInitialTime.getText().trim().equals("")) {
//			exception.addError("itime", "!");
//		}
//		// section.setDescricao(txtDescription.getText());
//
//		if (txtFinalTime.getText() == null || txtFinalTime.getText().trim().equals("")) {
//			exception.addError("ftime", "!");
//		}
//		// section.setDescricao(txtDescription.getText());
//
//		if (txtPrice.getText() == null || txtPrice.getText().trim().equals("")) {
//			exception.addError("price", "!");
//		}
//		// section.setDescricao(txtDescription.getText());
//
//		if (cbClass.getValue() == null) {
//			exception.addError("class", "!");
//		} else {
//			// section.setPacienteid(cbPatient.getValue());
//		}
//
//		if (cbMovie.getValue() == null) {
//			exception.addError("movie", "!");
//		} else {
//			// section.setPacienteid(cbPatient.getValue());
//		}
//
//		return null;
//	}
//
//	private void setErrorMessages(Map<String, String> errors) {
//		Set<String> fields = errors.keySet();
//		initialTimeLabel.setText((fields.contains("itime") ? errors.get("itime") : ""));
//		finalTimeLabel.setText((fields.contains("ftime") ? errors.get("ftime") : ""));
//		priceLabel.setText((fields.contains("price") ? errors.get("price") : ""));
//		cbClassLabel.setText((fields.contains("class") ? errors.get("class") : ""));
//		cbMovieLabel.setText((fields.contains("movie") ? errors.get("movie") : ""));
//	}
	
	private ErrorList SaveSession() {
		var errors = new ErrorList();
		var initialTimeStr = txtInitialTime.getText();
		var finalTimeStr = txtFinalTime.getText();
		var priceStr = txtFinalTime.getText();
		var selectedRoom = cbClass.getValue();
		var selectedMovie = cbMovie.getValue();
		double price = -1d;
		LocalDateTime initialTime = null;
		LocalDateTime finalTime = null;
		try {
			price = Double.parseDouble(priceStr);
		} catch (NumberFormatException formatException) {
			errors.add(new Error("price", "Incorrect format"));
		}
		try {
			initialTime = LocalDateTime.parse(initialTimeStr, Sessao.formatter);
		} catch (DateTimeParseException formatException) {
			errors.add(new Error("itime", "Incorrect format"));
		}
		try {
			finalTime = LocalDateTime.parse(finalTimeStr, Sessao.formatter);
		} catch (DateTimeParseException formatException) {
			errors.add(new Error("ftime", "Incorrect format"));
		}
		
		var session = new Sessao(initialTime, finalTime, selectedRoom, selectedMovie, price);
		
		SessionHolder.getInstance().setSession(session);
		
		return errors.addAll(session.isValid());
	}

	private void setErrorMessages(ErrorList errors) {
		SessionHolder.getInstance().ResetSession();
		initialTimeLabel.setText(errors.GetErrorLabel("itime"));
		finalTimeLabel.setText(errors.GetErrorLabel("ftime"));
		priceLabel.setText(errors.GetErrorLabel("price"));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roomRepo = UnitFactory.getRoomRepo();
		initializeRoomComboBox();
	}

	private void initializeRoomComboBox() {
		cbClass.setCellFactory(factory);
		cbClass.setButtonCell(factory.call(null));
		if(!RoomHolder.getInstance().IsEmpty())
			cbClass.setValue(RoomHolder.getInstance().getRoom());
		
		var roomObsList = FXCollections.observableArrayList(roomRepo.GetAll());
		cbClass.setItems(roomObsList);
	}
	
	private static Callback<ListView<Sala>, ListCell<Sala>> factory = lv -> new ListCell<Sala>() {
		@Override
		protected void updateItem(Sala item, boolean empty) {
			super.updateItem(item, empty);
			setText(empty ? "" : (item.numeroSala+""));
		}
	};
}
