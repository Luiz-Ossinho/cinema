package prova3bi.Cinema;

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
		stage.getIcons().add(new Image(App.class.getResourceAsStream("76.png")));
		stage.show();
	}

	static void setRoot(String fxml){
		try {
			scene.setRoot(loadFXML(fxml));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
//		var sessionService = UnitFactory.getSessionService();
//		var DH = LocalDateTime.now().plusHours(2);
//		var DHLater = DH.plusHours(2);
//		var movie = new Movie("Filme Generico", "", "Sinopse braba", Soundtrack.Legendado);
//		var room = new Room(RoomType.Prime, 14, 13);
//		var session = new Session(DH, DHLater, room, movie, 12d);
//		var returnedSession = sessionService.Add(session);
//		var list = sessionService.GetNext();
// CODIGO USADO PARA TESTAR
// NO MEU FUNCIONOU KKKKKKKK
		/*
		 * ArrayList<Ticket> tickets = new ArrayList<>(); tickets.add(new Ticket(1));
		 * tickets.add(new Ticket(2)); tickets.add(new Ticket(3)); tickets.add(new
		 * Ticket(4)); Ticket.createPDF(tickets);
		 */
		launch();
	}
}