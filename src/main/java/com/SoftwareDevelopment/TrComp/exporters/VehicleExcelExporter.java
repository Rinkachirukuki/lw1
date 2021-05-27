package com.SoftwareDevelopment.TrComp.exporters;

import com.SoftwareDevelopment.TrComp.models.Driver;
import com.SoftwareDevelopment.TrComp.models.Vehicle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class VehicleExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Vehicle> vehicleList;

    public VehicleExcelExporter(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Автомобили");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        font.setFontHeight(14);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Марка", style);
        createCell(row, 2, "Модель", style);
        createCell(row, 3, "Номер", style);
        createCell(row, 4, "Имя водителя", style);
        createCell(row, 5, "Фамилия водителя", style);
        createCell(row, 6, "Отчество водителя", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        for (Vehicle vehicle : vehicleList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, vehicle.getId(), style);
            createCell(row, columnCount++, vehicle.getMark().getName(), style);
            createCell(row, columnCount++, vehicle.getModel(), style);
            createCell(row, columnCount++, vehicle.getNumber(), style);

            Driver driver = vehicle.getDriver();

            createCell(row, columnCount++, driver.getFirstName(), style);
            createCell(row, columnCount++, driver.getLastName(), style);
            createCell(row, columnCount++, driver.getPatronymic(), style);

        }

    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeader();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
