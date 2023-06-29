package com.abc.demo.utility;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.demo.dto.BookingDTO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.Setter;
@Setter
public class PDFGeneratorVehicle {

	private BookingDTO b;

	

	public void generate(HttpServletResponse response) throws DocumentException, IOException {
		// Create the Object of Document
		Document document = new Document(PageSize.A4);
		// get the document and write the response to output stream
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		// Add Font

		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_BOLD);
		fontTiltle.setSize(20);

		Paragraph paragraph = new Paragraph("Car Rental System", fontTiltle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Add to the document
		document.add(paragraph);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 4, 2, 3, 2, 4, 3 });
		table.setSpacingBefore(5);
		// Create Table Header
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.darkGray);
		cell.setPadding(6);

		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Booking ID", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Vehicle Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Model Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Brand", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Pick up", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);

		
			table.addCell(String.valueOf(b.getBookingNo()));
			table.addCell(b.getVehicle().getVehicleName());
			table.addCell(b.getVehicle().getModelName());
			table.addCell(b.getVehicle().getBrand().getCarBrand());
			table.addCell(b.getBookingPickupTime());
			table.addCell(b.getUser().getFirstName());
		
		document.add(table);
		Paragraph paragraph2 = new Paragraph("ABC Company", fontTiltle);
		paragraph2.setAlignment(Paragraph.ALIGN_RIGHT);
		
		Paragraph paragraph3 = new Paragraph("Near ABC Street, India", FontFactory.getFont(FontFactory.TIMES));
		paragraph3.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(paragraph2);
		document.add(paragraph3);
		document.close();
	}
}
