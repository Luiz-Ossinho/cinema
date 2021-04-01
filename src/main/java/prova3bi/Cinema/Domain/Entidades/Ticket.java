package prova3bi.Cinema.Domain.Entidades;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Builder.Is;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

@Table(nome = "Tickets", fks = { "poltrona;Poltronas"})
public class Ticket extends Entidade {
	private final static String pathQR = "qr.jpg";
	private final static String pathPDF = "tickets.pdf";
	@Builder(Is.Read)
	public Ticket(int TicketsID, TicketStatus status, int poltrona) {
		super(TicketsID);
		this.status = status;
		this.poltrona = new Poltrona(poltrona);
	}
	
	@Builder(Is.Insert)
	public Ticket(Poltrona poltrona) {
		super(-1);
		this.poltrona = poltrona;
	}
	
	@Column(nome = "status", tipoSql = "INTEGER")
	public TicketStatus status;
	@Column(nome = "poltrona", tipoSql = "INTEGER", isFk = true)
	public Poltrona poltrona;

	public void Quitar() {
		this.status = TicketStatus.Quitado;
	}
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
	//metodo recebe um 
	private static Image createQR(int id)
			throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter()
				.encode(Integer.toString(id), BarcodeFormat.QR_CODE, 500, 500);
		
		MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(pathQR));
		Image qr = Image.getInstance(pathQR);
		return qr;
	}
	//metodo recebe uma lista de tickets e salva em um pdf os qr codes dos ids de todos os kits
	public static void createPDF(ArrayList<Ticket> ticket) {
		Rectangle rect = new Rectangle(500, 550);
		// step 1: creation of a document-object
		Document document = new Document(rect, 0,0,0,0);
		try {

			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file
			PdfWriter.getInstance(document, new FileOutputStream(pathPDF));
			
		     
			Image logo = Image.getInstance("cinenow.png");
			// step 3: we open the document
			logo.setAlignment(Image.ALIGN_CENTER);
			document.open();

			// step 4: we add a paragraph to the document
			for (int i = 0; i < ticket.size(); i++) {
				document.add(logo);
				document.add(Ticket.createQR(ticket.get(i).getId()));
				if(i > 1) {
					document.newPage();
				}
			}
			
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// step 5: we close the document
		document.close();
	}
}
