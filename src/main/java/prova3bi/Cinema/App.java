package prova3bi.Cinema;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
<<<<<<< HEAD
import prova3bi.Cinema.Domain.Entidades.Ticket;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;

import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfWriter;

//import api iText

=======
>>>>>>> refs/remotes/origin/feature/JisusDepresso
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
//		var DH = LocalDateTime.now().plusHours(2);
//		var DHLater = DH.plusHours(2);
//		var room = new Sala(TipoSala.Prime, 15, 13);
//		var movie = new Filme("Filme Generico", "", "Sinopse braba", TrilhaSonora.Legendado);
//		var session = new Sessao(DH, DHLater, room, movie, 12d);
//		var sessionService = UnitFactory.getSessionService();
//		var list = sessionService.GetNext();
// CODIGO USADO PARA TESTAR
// NO MEU FUNCIONOU KKKKKKKK
/*		ArrayList<Ticket> tickets = new ArrayList<>();
		tickets.add(new Ticket(1));
		tickets.add(new Ticket(2));
		tickets.add(new Ticket(3));
		tickets.add(new Ticket(4));
		Ticket.createPDF(tickets);
		*/
		launch();
	}
}