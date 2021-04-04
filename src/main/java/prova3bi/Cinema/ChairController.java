package prova3bi.Cinema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import prova3bi.Cinema.Domain.Entities.Chair;

public class ChairController implements Initializable {

	@FXML
	private VBox chairContainer;

	@FXML
	private Label chairPosition;

	private Chair chair;

	public void setChair(Chair chair) {
		this.chair = chair;
		chairPosition.setText(chair.column + " " + chair.row);

		if (chair.ocupada) {
			chairContainer.setStyle("-fx-background-color:  #ff0657; -fx-background-radius: 8;");
			chairPosition.setStyle("-fx-text-fill: #ffffff;");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
