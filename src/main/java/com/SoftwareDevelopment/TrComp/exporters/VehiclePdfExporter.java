package com.SoftwareDevelopment.TrComp.exporters;

import com.SoftwareDevelopment.TrComp.models.Driver;
import com.SoftwareDevelopment.TrComp.models.Vehicle;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class VehiclePdfExporter {
    private List<Vehicle> vehicleList;

    public VehiclePdfExporter(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    private void fillTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(4);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Марка"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Модель"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Номер"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Имя водителя"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Фамилия водителя"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Отчество водителя"));
        table.addCell(cell);
    }

    private void fillTableData(PdfPTable table) {
        for (Vehicle vehicle : vehicleList) {
            table.addCell(vehicle.getId().toString());
            table.addCell(vehicle.getMark().getName());
            table.addCell(vehicle.getModel());
            table.addCell(vehicle.getNumber());

            Driver driver = vehicle.getDriver();

            table.addCell(driver.getFirstName());
            table.addCell(driver.getLastName());
            table.addCell(driver.getPatronymic());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A3);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        PdfPTable table = new PdfPTable(7);

        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1f, 3f, 3f, 3f, 3f, 3f, 3f });
        table.setSpacingBefore(10);

        fillTableHeader(table);
        fillTableData(table);

        document.add(table);

        document.close();

    }

}
