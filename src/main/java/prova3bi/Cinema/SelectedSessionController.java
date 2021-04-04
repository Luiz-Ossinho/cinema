package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

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
	
	@FXML
	private HBox chairSession;

	private Session sessionSelected;

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("SessionList");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sessionSelected = SessionHolder.getInstance().getSession();
		
		// AQUI EU VOU PEGAR A LISTA DE CADEIRAS
		var listSeat = sessionSelected.chair;
		
		txtTicketValue.setText(sessionSelected.preco+" R$");

		// INICIALIZAR A QUANTIDADE DE COLUNAS E LINHAS
		int column = 0;
		int row = 1;

		// ONDE VAI ACONTECER A LISTAGEM NA TELA DE CADEIRAS
		var maxWidth = getWidth(listSeat)+1;
		 try {
				for (var seat : listSeat) {
					var fxml = new FXMLLoader();
					fxml.setLocation(getClass().getResource("Chair.fxml"));
					VBox vBox = fxml.load();
					ChairController chairController = fxml.getController();
					chairController.setChair(seat);
					
					chairSession.getChildren().add(vBox);
	                
	                ChairController controller = fxml.getController();
	                controller.setChair(seat);	   
	                
					if (column == maxWidth) {
						column = 0;
						row++;
					}
					
					panel.add(vBox, column++, row);
					
					panel.setMinWidth(Region.USE_COMPUTED_SIZE);
					panel.setPrefWidth(Region.USE_COMPUTED_SIZE);
					panel.setMaxWidth(Region.USE_PREF_SIZE);
	                
					panel.setMinHeight(Region.USE_COMPUTED_SIZE);
	                panel.setPrefHeight(Region.USE_COMPUTED_SIZE);
	                panel.setMaxHeight(Region.USE_PREF_SIZE);
	                
	                GridPane.setMargin(vBox, new Insets(10));
				}
	        }catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private static List<String> letters = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" ));
	
	public int getWidth(List<Chair> list) {
		int largestLetter = Integer.MIN_VALUE;
		for (var chair : list) {
			var letter = chair.column;
			var numLetter = letters.indexOf(letter);
			if(largestLetter < numLetter)
				largestLetter = numLetter+1;
		}
		
		return largestLetter;
	}

	public int getLenght(List<Chair> list) {
		int largest = Integer.MAX_VALUE;
		for (var chair : list) {
			if(largest < chair.row)
				largest = chair.row;
		}
		return largest;
	}
}
