package prova3bi.Cinema;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TicketListAuthorizationController {

	@FXML
	private TextField txtTicket;

	@FXML
	private Label ticketLabel;

	@FXML
	private Label successTitle;

	@FXML
	private Label successStatusTicket;

	@FXML
	private Label successMovie;

	@FXML
	private Label successRoom;

	@FXML
	private Label successInitMovie;

	@FXML
	private Label successFinalSection;

	@FXML
	private Label successMessage;

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("Dashboard");
	}

	@FXML
	void onSubmit(ActionEvent event) {
		var ticketId = txtTicket.getText();
		
		ticketLabel.setText("");
		successTitle.setText("");
		successStatusTicket.setText("");
		successMovie.setText("");
		successRoom.setText("");
		successInitMovie.setText("");
		successFinalSection.setText("");
		successMessage.setText("");
	}

}
