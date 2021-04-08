package prova3bi.Cinema.Application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import prova3bi.Cinema.Application.Singletons.ChairHolder;
import prova3bi.Cinema.Domain.Entities.Chair;

public class ChairController implements Initializable {

	@FXML
	private VBox chairContainer;

	@FXML
	private Label chairPosition;

	private Chair chair;

	private boolean IsSelected = false;

	private Chair.State initialState;

	public void setChair(Chair chair) {
		this.chair = chair;
		chairPosition.setText(this.chair.column + " " + this.chair.row);

		switch (this.chair.state) {
		case Occupied:
			SetAsOccupied();
			break;
		case Pending:
			SetAsPending();
			break;
		}
	}

	@FXML
	void onSelectChair(MouseEvent event) {
		if (this.chair.state != Chair.State.Occupied) {
			var obsList = ChairHolder.GetInstance().getObsList();
			if (IsSelected) {
				SetAsDisselected();
				obsList.remove(chair);
			} else {
				SetAsSeleceted();
				obsList.add(chair);
			}
		}
	}

	private void SetAsOccupied() {
		initialState = Chair.State.Occupied;
		chairContainer.setStyle("-fx-background-color:  #ff0657; -fx-background-radius: 8;");
		chairPosition.setStyle("-fx-text-fill: #ffffff;");
	}

	// Implementar pendente
	private void SetAsPending() {
		initialState = Chair.State.Pending;
		chairContainer.setStyle("-fx-background-color:  #ff0657; -fx-background-radius: 8;");
		chairPosition.setStyle("-fx-text-fill: #ffffff;");
	}

	private void SetAsDisselected() {
		if (initialState != Chair.State.Occupied) {
			IsSelected = false;
			chairContainer.setStyle("-fx-cursor: hand; -fx-background-color:  #ffffff; -fx-background-radius: 8; ");
			chairPosition.setStyle("-fx-text-fill: #000000;");
		}
	}

	private void SetAsSeleceted() {
		IsSelected = true;
		chairContainer.setStyle("-fx-cursor: hand; -fx-background-color: #af2350c9; -fx-background-radius: 8;");
		chairPosition.setStyle("-fx-text-fill: #ffffff;");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
