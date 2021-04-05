package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import prova3bi.Cinema.Domain.Entities.Chair;
import prova3bi.Cinema.Domain.Entities.Session;
import prova3bi.Cinema.Singletons.ChairHolder;
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
	private Label txtTitle;

	@FXML
	private GridPane panel;

	@FXML
	private HBox chairSession;

	private Session session;

	private ObservableList<Chair> obsList = FXCollections.observableArrayList();

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		SessionHolder.getInstance().Reset();
		App.setRoot("SessionList");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		session = SessionHolder.getInstance().getSession();
		txtTicketValue.setText(session.preco + " R$");
		txtTitle.setText(session.filme.title);
		txtSynopsis.setText(session.filme.synopsis);
		txtSynopsis.setWrapText(true);
		txtQuantity.setText("Cadeiras disponiveis na sessão " + session.numPoltronasVagas());
		
		RenderChairs(session.chairs);
		ChairHolder.GetInstance().setObsList(obsList);
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

	private static List<String> letters = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

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
}
