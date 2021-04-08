package prova3bi.Cinema.Application;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import prova3bi.Cinema.Application.Singletons.ChairHolder;
import prova3bi.Cinema.Application.Singletons.SessionHolder;
import prova3bi.Cinema.Application.Singletons.TicketHolder;
import prova3bi.Cinema.Domain.Entities.Chair;
import prova3bi.Cinema.Domain.Entities.Session;
import prova3bi.Cinema.Domain.Entities.Ticket;
import prova3bi.Cinema.Domain.Interfaces.Services.IChairService;
import prova3bi.Cinema.Domain.Interfaces.Services.ITicketService;
import prova3bi.Cinema.Services.UnitFactory;
import prova3bi.Cinema.Util.Alerts;

public class SelectedSessionController implements Initializable {

	private ObservableList<Chair> obsList = FXCollections.observableArrayList();

	private ITicketService ticketService;

	private IChairService chairService;

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		SessionHolder.getInstance().Reset();
		App.setRoot("SessionList");
	}

	@FXML
	void switchFinalizedTicket(MouseEvent event) throws IOException {
		if (obsList.isEmpty())
			WarnEmpty();
		else {
			PersistPending(obsList);
			WarnSucess();
			App.setRoot("FinalizedTicket");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GetDependencies();
		session = SessionHolder.getInstance().getSession();
		txtTicketValue.setText(session.preco + " R$");
		txtTitle.setText(session.filme.title);
		txtTitle.setWrapText(true);
		txtSynopsis.setText(session.filme.synopsis);
		txtSynopsis.setWrapText(true);
		txtQuantity.setText("Cadeiras disponiveis na sessão " + session.numPoltronasVagas());

		RenderChairs(session.chairs);
		ChairHolder.GetInstance().setObsList(obsList);
	}

	private void WarnSucess() {
		Alerts.showAlert("Sucesso", "Tickets gerados com sucesso",
				"Ate que seu ticket esteja quitado a cadeira ainda ser listada como vaga.", AlertType.INFORMATION);
	}

	private void WarnEmpty() {
		Alerts.showAlert("Sem poltronas", "Nenhuma poltrona foi selecionada",
				"Selecione pelo menos uma cadeira para continuar.", AlertType.ERROR);
	}

	private void PersistPending(List<Chair> chairs) {
		this.chairService.SetAsPending(obsList);
		var tickets = this.ticketService.CreateAll(obsList);
		TicketHolder.getInstance().SetTickets(tickets);
		var stream = Ticket.createPDF(tickets);
		var tempdir = System.getProperty("java.io.tmpdir");
		try(var fileStream = new FileOutputStream(tempdir+"\\Tickets.pdf")){
			((ByteArrayOutputStream)stream).writeTo(fileStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void GetDependencies() {
		this.chairService = UnitFactory.getChairService();
		this.ticketService = UnitFactory.getTicketService();
	}

	private void RenderChairs(Chair[][] chairs) {
		for (int i = 0; i < chairs.length; i++) {
			for (int j = 0; j < chairs[0].length; j++) {
				var chair = chairs[i][j];
				if (chair != null) {
					try {
						var fxml = GetChairLoader();
						VBox vBox = fxml.load();
						LoadChair(chair, vBox, fxml);

						panel.add(vBox, i, (j + 1));

						ConfigurePanel(panel);
						GridPane.setMargin(vBox, new Insets(10));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private FXMLLoader GetChairLoader() {
		var fxml = new FXMLLoader();
		fxml.setLocation(getClass().getResource("Chair.fxml"));
		return fxml;
	}

	private void LoadChair(Chair chair, VBox vBox, FXMLLoader fxml) {
		ChairController chairController = fxml.getController();
		chairController.setChair(chair);
		chairSession.getChildren().add(vBox);
	}

	private void ConfigurePanel(GridPane panel) {
		panel.setMinWidth(Region.USE_COMPUTED_SIZE);
		panel.setPrefWidth(Region.USE_COMPUTED_SIZE);
		panel.setMaxWidth(Region.USE_PREF_SIZE);
		panel.setMinHeight(Region.USE_COMPUTED_SIZE);
		panel.setPrefHeight(Region.USE_COMPUTED_SIZE);
		panel.setMaxHeight(Region.USE_PREF_SIZE);
	}
	
	@FXML
	private TextArea txtSynopsis;

	@FXML
	private Label txtDirector;

	@FXML
	private Label txtGender;

	@FXML
	private Label txtTicketValue;

	@FXML
	private Label txtQuantity;

	@FXML
	private Label txtTitle;

	@FXML
	private GridPane panel;

	@FXML
	private HBox chairSession;

	private Session session;
}
