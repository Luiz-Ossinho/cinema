package prova3bi.Cinema.Application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import prova3bi.Cinema.Domain.Entities.Session;
import prova3bi.Cinema.Domain.Interfaces.Services.ISessionService;
import prova3bi.Cinema.Services.UnitFactory;

// TODO popular
public class SessionListController implements Initializable {

	@FXML
	private void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("InitialPage");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GetDependencies();
		sessions = sessionService.GetNext();
		try {
			for (var session : sessions) {
				var fxml = new FXMLLoader();
				fxml.setLocation(getClass().getResource("movie.fxml"));

				VBox vBox = fxml.load();
				MovieController movieController = fxml.getController();
				movieController.setData(session);

				recentlyMovieContainer.getChildren().add(vBox);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void GetDependencies() {
		sessionService = UnitFactory.getSessionService();
	}

	@FXML
	private HBox backgroundScene;

	@FXML
	private HBox recentlyMovieContainer;

	private List<Session> sessions;
	private ISessionService sessionService;

}