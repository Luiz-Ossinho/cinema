package prova3bi.Cinema.Application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
//import api iText

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("InitialPage"), 1200, 675);
		stage.setScene(scene);
		var icon = new Image("/TaskBarIcon.png");
		stage.getIcons().add(icon);
		stage.show();
	}

	static void setRoot(String fxml){
		try {
			scene.setRoot(loadFXML(fxml));
		} catch (IOException e) {
			System.err.println("Erro ao carregar arquivo fxml");
		}
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}
}