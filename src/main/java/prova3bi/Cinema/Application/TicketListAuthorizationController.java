package prova3bi.Cinema.Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import prova3bi.Cinema.Domain.Entities.Ticket;
import prova3bi.Cinema.Domain.Interfaces.Services.ITicketService;
import prova3bi.Cinema.Domain.Validations.ErrorList;
import prova3bi.Cinema.Services.UnitFactory;
import prova3bi.Cinema.Util.Alerts;
import prova3bi.Cinema.Util.Utils;

public class TicketListAuthorizationController implements Initializable {

	@FXML
	private TextField txtTicket;

	@FXML
	private Label ticketLabel;

	@FXML
	private Label successTitle;

	@FXML
	private Label successChair;

	@FXML
	private Label successMovie;

	@FXML
	private Label successRoom;
	
	@FXML
	private Label successMessage;

	private ITicketService ticketService;

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("Dashboard");
	}

	@FXML
	void onSubmit(ActionEvent event) {
		var errors = new ErrorList();
		var strTicketId = txtTicket.getText();
		var ticketId = Utils.TryParseValue(Utils.integerParser, strTicketId, errors, "TicketID");
		var ticket = ticketService.Get(ticketId);
		if (ticket.status == Ticket.Status.Quitado)
			WarnPayed();
		else {
			ticket = ticketService.QuitarTicket(ticketId);
			WarnSuccess(ticket);
		}
		ResetFields();
	}

	private void WarnPayed() {
		Alerts.showAlert("Error", "Ticket invalido", "Um ticket para essa poltrona ja foi quitado por alguem antes",
				AlertType.ERROR);
	}

	private void WarnSuccess(Ticket ticket) {
		var chair = ticket.poltrona;
		var session = chair.sessao;
		var room = session.sala;
		var movie = session.filme;
		
		successTitle.setText("Ticket gerado! Id: " + ticket.getId());
		successMovie.setText("Filme: " + movie.title);
		successRoom.setText("Sala: " + room.numeroSala + "");
		successChair.setText("Poltrona: " + chair.column + " " + " " + chair.row);
		successMessage.setText("Se prepara que o filme ja vai começar!");

	}
	
	public void ResetFields() {
		txtTicket.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.ticketService = UnitFactory.getTicketService();

	}
}
