package prova3bi.Cinema;

import java.io.IOException;

import javafx.fxml.FXML;

public class FinalizedTicketController {

	@FXML
	private void switchMovieList() throws IOException {
		App.setRoot("InitialPage");
	}

}
