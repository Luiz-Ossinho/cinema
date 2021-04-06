package prova3bi.Cinema.Domain.Entities;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
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
		this.status = TicketStatus.Pendente;
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

	public static OutputStream createPDF(ArrayList<Ticket> tickets) {
		var rect = new Rectangle(500, 550);
		var document = new Document(rect, 0, 0, 0, 0);
		PdfWriter writer = null;
		var pdfByteArrayStream = new ByteArrayOutputStream();
		try {
			writer = PdfWriter.getInstance(document, pdfByteArrayStream);

			Image logo = Image.getInstance("src/main/resources/prova3bi/Images/cinenowLogo.png");

			logo.setAlignment(Image.ALIGN_CENTER);
			writer.open();

			int pageCount = 0;
			for (var ticket : tickets) {
				Paragraph idCentralizado = new Paragraph(ticket + "");
				idCentralizado.setAlignment(Element.ALIGN_CENTER);
				document.add(logo);
				var qrCodeImg = Ticket.createQR(ticket.getId());
				document.add(qrCodeImg);
				document.add(idCentralizado);
				if (pageCount > 0) {
					document.newPage();
				}
				pageCount++;
			}

		} catch (DocumentException | IOException | WriterException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		writer.close();
		return pdfByteArrayStream;
	}
}
