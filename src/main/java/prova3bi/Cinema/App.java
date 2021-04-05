package prova3bi.Cinema;

import java.io.IOException;
import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prova3bi.Cinema.Domain.Entities.Movie;
import prova3bi.Cinema.Domain.Entities.Room;
//import api iText
import prova3bi.Cinema.Domain.Entities.RoomType;
import prova3bi.Cinema.Domain.Entities.Session;
import prova3bi.Cinema.Domain.Entities.Soundtrack;
import prova3bi.Cinema.Services.UnitFactory;
import prova3bi.Cinema.Util.Utils;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("InitialPage"), 1200, 675);
		stage.setScene(scene);
		stage.show();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}



	public static void main(String[] args) {
		launch();
	}
}