package prova3bi.Cinema.Domain.Entities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

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

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Builder.Is;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;
import prova3bi.Cinema.Domain.Validations.ErrorList;

@Table(nome = "Tickets", fks = { "poltrona;Poltronas" })
public class Ticket extends Entity {
	private final static String pathQR = "qr.jpg";
	private final static String pathPDF = "tickets.pdf";

	@Builder(Is.Read)
	public Ticket(int TicketsID, TicketStatus status, int chair) {
		super(TicketsID);
		this.status = status;
		this.poltrona = new Chair(chair);
	}

	@Builder(Is.Insert)
	public Ticket(Chair chair) {
		super(-1);
		this.poltrona = chair;
	}

	@Column(nome = "status", tipoSql = "INTEGER")
	public TicketStatus status;
	@Column(nome = "poltrona", tipoSql = "INTEGER", isFk = true)
	public Chair poltrona;

	public void Quitar() {
		this.status = TicketStatus.Quitado;
	}

	@Override
	public ErrorList isValid() {
		return null;
	}

	private static Image createQR(int id) throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(Integer.toString(id), BarcodeFormat.QR_CODE, 500, 500);

		MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(pathQR));
		Image qr = Image.getInstance(pathQR);
		return qr;
	}

	public static void createPDF(ArrayList<Ticket> ticket) {
		Rectangle rect = new Rectangle(500, 550);
		Document document = new Document(rect, 0, 0, 0, 0);
		try {

			PdfWriter.getInstance(document, new FileOutputStream(pathPDF));

			Image logo = Image.getInstance("cinenow.png");

			logo.setAlignment(Image.ALIGN_CENTER);
			document.open();

			for (int i = 0; i < ticket.size(); i++) {
				document.add(logo);
				document.add(Ticket.createQR(ticket.get(i).getId()));
				if (i > 1) {
					document.newPage();
				}
			}

		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} catch (WriterException e) {
			e.printStackTrace();
		}

		document.close();
	}
}
