package prova3bi.Cinema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import prova3bi.Cinema.Domain.Entities.Chair;
import prova3bi.Cinema.Singletons.ChairHolder;

public class ChairController implements Initializable {

	@FXML
	private VBox chairContainer;

	@FXML
	private Label chairPosition;

	private Chair chair;
	
	private boolean IsSelected = false;

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
			if(IsSelected) {
				SetAsDisselected();
				obsList.remove(chair);
			} else {
				SetAsSeleceted();
				obsList.add(chair);
			}
		}
	}

	private void SetAsOccupied() {
		chairContainer.setStyle("-fx-background-color:  #ff0657; -fx-background-radius: 8;");
		chairPosition.setStyle("-fx-text-fill: #ffffff;");
	}

	// Implementar pendente
	private void SetAsPending() {
		chairContainer.setStyle("-fx-background-color:  #ff0657; -fx-background-radius: 8;");
		chairPosition.setStyle("-fx-text-fill: #ffffff;");
	}
	
	// Implementa ae guilherme, precisa ser clicavel
	private void SetAsDisselected() {
		this.IsSelected = false;
		chairContainer.setStyle("");
		chairPosition.setStyle("");
	}
	
	// Implementa ae guilherme, precisa ser clicavel
	private void SetAsSeleceted() {
		this.IsSelected = true;
		chairContainer.setStyle("-fx-background-color:  #ff0657; -fx-background-radius: 8;");
		chairPosition.setStyle("-fx-text-fill: #ffffff;");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
