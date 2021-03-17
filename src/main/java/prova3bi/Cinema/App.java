package prova3bi.Cinema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("primary"), 640, 480);
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
//		var DH = LocalDateTime.now().plusHours(2);
//		var DHLater = DH.plusHours(2);
//		var room = new Sala(TipoSala.Prime, 15, 13);
//		var movie = new Filme("Filme Generico", "", "Sinopse braba", TrilhaSonora.Legendado);
//		var session = new Sessao(DH, DHLater, room, movie, 12d);
//		var sessionService = UnitFactory.getSessionService();
//		var list = sessionService.GetNext();
// CODIGO USADO PARA TESTAR
// NO MEU FUNCIONOU KKKKKKKK
		launch();
	}

}