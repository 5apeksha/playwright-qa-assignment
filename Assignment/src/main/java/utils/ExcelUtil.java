package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcelUtil {

    private Sheet sheet;
    private Workbook workbook;
    private String filePath;
    private static final Logger logger = Logger.getLogger(ExcelUtil.class.getName());

    public ExcelUtil(String filePath, String sheetName) throws IOException {
        this.filePath = filePath;
        FileInputStream file = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(sheetName);
    }

    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            System.err.println("Row " + rowNum + " does not exist in the sheet.");
            return null;
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            System.err.println("Cell at row " + rowNum + " and column " + colNum + " does not exist.");
            return null;
        }

        // Handle different cell types
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                System.err.println("Unsupported cell type at row " + rowNum + " and column " + colNum);
                return null;
        }
    }

    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public void setCellData(int rowNum, int colNum, String data) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }
        cell.setCellValue(data);
    }

    public void save() {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            logger.info("Excel file saved successfully: " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save Excel file: " + filePath, e);
        }
    }

    public void close() throws IOException {
        workbook.close();
    }
}