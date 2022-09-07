package com.academia.export;

import com.academia.entities.Equipment;
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

public class EquipmentExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Equipment> listEquipment;

    public EquipmentExcelExporter(List<Equipment> listEquipment) {
        this.listEquipment = listEquipment;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Equipamentos");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Nome", style);
        createCell(row, 2, "Descrição", style);
        createCell(row, 3, "Categoria", style);
        createCell(row, 4, "Peso", style);
        createCell(row, 5, "Dimenções", style);
        createCell(row, 6, "Preço", style);
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
        font.setFontHeight(14);
        style.setFont(font);

        for (Equipment equipment : listEquipment) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, Long.toString(equipment.getId()), style);
            createCell(row, columnCount++, equipment.getName(), style);
            createCell(row, columnCount++, equipment.getDescription(), style);
            createCell(row, columnCount++, equipment.getCategory(), style);
            createCell(row, columnCount++, Double.toString(equipment.getWeight()), style);
            createCell(row, columnCount++, equipment.getDimensions(), style);
            createCell(row, columnCount++, Double.toString(equipment.getPrice()), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
