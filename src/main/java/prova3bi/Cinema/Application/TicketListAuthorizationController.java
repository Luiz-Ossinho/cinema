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
		var srtBody = "Ticket id: " + ticket.getId() + " \n";
		srtBody += "Poltrona: " + chair.column + " " + " " + chair.row + " \n";
		srtBody += "Na sala: " + room.numeroSala + " \n";
		srtBody += "Para o filme: " + movie.title + " \n";

		Alerts.showAlert("Sucesso!", "Ticket quitado", srtBody, AlertType.INFORMATION);

	}
	
	public void ResetFields() {
		txtTicket.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.ticketService = UnitFactory.getTicketService();

	}
}
