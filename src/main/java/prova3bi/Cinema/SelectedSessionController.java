package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import prova3bi.Cinema.Domain.Entities.Session;
import prova3bi.Cinema.Singletons.SessionHolder;

public class SelectedSessionController implements Initializable {

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
	private GridPane panel;

	private Session sessionSelected;

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("SessionList");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		sessionSelected = SessionHolder.getInstance().getSession();

		// AQUI EU VOU PEGAR A LISTA DE CADEIRAS
		List<String> listSeat = new ArrayList<>();

		// INICIALIZAR A QUANTIDADE DE COLUNAS E LINHAS
		int column = 0;
		int row = 1;

		// ONDE VAI ACONTECER A LISTAGEM NA TELA DE CADEIRAS
		for (String seat : listSeat) {
			if (column == 30) {
				column = 0;
				row++;
			}
		}
	}

}
