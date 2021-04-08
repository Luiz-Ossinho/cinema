package prova3bi.Cinema.Application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import prova3bi.Cinema.Application.Singletons.MovieHolder;
import prova3bi.Cinema.Application.Singletons.RoomHolder;
import prova3bi.Cinema.Application.Singletons.SessionHolder;
import prova3bi.Cinema.Domain.Entities.Movie;
import prova3bi.Cinema.Domain.Entities.Room;
import prova3bi.Cinema.Domain.Entities.Session;
import prova3bi.Cinema.Domain.Interfaces.Services.IMovieService;
import prova3bi.Cinema.Domain.Interfaces.Services.IRoomService;
import prova3bi.Cinema.Domain.Interfaces.Services.ISessionService;
import prova3bi.Cinema.Domain.Validations.ErrorList;
import prova3bi.Cinema.Services.UnitFactory;
import prova3bi.Cinema.Util.Alerts;
import prova3bi.Cinema.Util.Utils;

public class SessionAdminFormController implements Initializable {
	
	@FXML
	void onSubmit(ActionEvent event) {
		ResetErrorMessages();
		var errors = CreateSession();
		if (!errors.isEmpty())
			setErrorMessages(errors);
		else {
			PersistSession();
			Alerts.showAlert("Sucesso", "Cadatrado com sucesso", "Sessao cadastrada com sucesso",
					AlertType.INFORMATION);
		}
	}

	private void PersistSession() {
		var holderInsance = SessionHolder.getInstance();
		var session = holderInsance.getSession();
		var updatedSession = sessionService.Add(session);
		holderInsance.setSession(updatedSession);
		ResetFields();
	}

	private void ResetFields() {
		txtInitialTime.setText("");
		txtFinalTime.setText("");
		txtPrice.setText("");
		cbClass.setValue(null);
		cbMovie.setValue(null);

	}

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		SessionHolder.getInstance().Reset();
		App.setRoot("Dashboard");
	}

	@FXML
	void switchMovieModal(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(SessionAdminFormController.class.getResource("MovieForm.fxml"));
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setResizable(false);
		stage.initOwner(((Node) event.getSource()).getScene().getWindow());
		stage.showAndWait();
	}

	@FXML
	void switchRoomModal(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(SessionAdminFormController.class.getResource("RoomForm.fxml"));
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setResizable(false);
		stage.initOwner(((Node) event.getSource()).getScene().getWindow());
		stage.showAndWait();
	}

	@FXML
	void dateTimekeyPressed(KeyEvent event) {
		var source = (TextField) event.getSource();
		var result = source.getText();

		if (event.getCode() == KeyCode.BACK_SPACE) {
			source.setText("");
			source.positionCaret(0);
			return;
		}

		result = Utils.applyDateTimeMask(result);

		source.setText(result);
		source.positionCaret(result.length());
	}

	@FXML
	void dateTimekeyReleased(KeyEvent event) {
		var source = (TextField) event.getSource();
		var result = source.getText();
		result = Utils.take(result, 0, 16);
		source.setText(result);
		source.positionCaret(result.length());
	}

	private ErrorList CreateSession() {
		var errors = new ErrorList();
		var initialTimeStr = txtInitialTime.getText();
		var finalTimeStr = txtFinalTime.getText();
		var priceStr = txtPrice.getText();
		var selectedRoom = cbClass.getValue();
		var selectedMovie = cbMovie.getValue();
		double price = Utils.TryParseValue(Utils.doubleParser, priceStr, errors, "price");
		LocalDateTime initialTime = Utils.TryParseValue(Utils.dateTimeParser, initialTimeStr, errors, "itime");
		LocalDateTime finalTime = Utils.TryParseValue(Utils.dateTimeParser, finalTimeStr, errors, "ftime");

		var session = new Session(initialTime, finalTime, selectedRoom, selectedMovie, price);

		SessionHolder.getInstance().setSession(session);

		return errors.addAll(session.isValid());
	}

	private void setErrorMessages(ErrorList errors) {
		SessionHolder.getInstance().ResetSession();
		initialTimeLabel.setText(errors.GetErrorLabel("itime"));
		finalTimeLabel.setText(errors.GetErrorLabel("ftime"));
		priceLabel.setText(errors.GetErrorLabel("price"));
	}

	private void ResetErrorMessages() {
		initialTimeLabel.setText("");
		finalTimeLabel.setText("");
		priceLabel.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeDependencies();
		initializeRoomComboBox();
		initializeMovieComboBox();
		// Constraints.dateField(txtInitialTime);
	}

	private void initializeRoomComboBox() {
		var holderInstance = RoomHolder.getInstance();
		cbClass.setCellFactory(roomCellFactory);
		cbClass.setButtonCell(roomCellFactory.call(null));

		var rooms = roomService.GetAll();
		var roomObsList = FXCollections.observableArrayList(rooms);
		holderInstance.setObsList(roomObsList);

		if (!holderInstance.IsEmpty())
			cbClass.setValue(holderInstance.getRoom());

		cbClass.setItems(roomObsList);
	}

	private void initializeMovieComboBox() {
		var holderInstance = MovieHolder.getInstance();
		cbMovie.setCellFactory(movieCellFactory);
		cbMovie.setButtonCell(movieCellFactory.call(null));

		var movies = movieService.GetAll();
		var moviesObsList = FXCollections.observableArrayList(movies);
		holderInstance.setObsList(moviesObsList);

		if (!holderInstance.IsEmpty())
			cbMovie.setValue(holderInstance.getMovie());

		cbMovie.setItems(moviesObsList);
	}

	private void initializeDependencies() {
		roomService = UnitFactory.getRoomService();
		sessionService = UnitFactory.getSessionService();
		movieService = UnitFactory.getMovieService();
	}

	private static Callback<ListView<Room>, ListCell<Room>> roomCellFactory = lv -> new ListCell<Room>() {
		@Override
		protected void updateItem(Room item, boolean empty) {
			super.updateItem(item, empty);
			setText(!empty ? item.numeroSala + "" : "");
		}
	};

	private static Callback<ListView<Movie>, ListCell<Movie>> movieCellFactory = lv -> new ListCell<Movie>() {
		@Override
		protected void updateItem(Movie item, boolean empty) {
			super.updateItem(item, empty);
			setText(!empty ? item.title : "");
		}
	};
	
	@FXML
	private HBox sectionContainer;

	@FXML
	private TextField txtInitialTime;

	@FXML
	private TextField txtFinalTime;

	@FXML
	private TextField txtPrice;

	@FXML
	private ComboBox<Room> cbClass;

	@FXML
	private ComboBox<Movie> cbMovie;

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

	private IRoomService roomService;
	private ISessionService sessionService;
	private IMovieService movieService;
}
