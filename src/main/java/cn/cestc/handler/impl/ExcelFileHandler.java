package cn.cestc.handler.impl;

import cn.cestc.handler.FileHandler;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

public class ExcelFileHandler implements FileHandler {
    @Override
    public String handleFile(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        Workbook workbook = new XSSFWorkbook(inputStream);
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            sb.append(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            sb.append(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            sb.append(cell.getBooleanCellValue());
                            break;
                        case BLANK:
                            sb.append("[BLANK]");
                            break;
                        default:
                            sb.append("[UNKNOWN]");
                            break;
                    }
                    sb.append("\t");
                }
                sb.append("\n");
            }
        }
        workbook.close();
        return sb.toString();
    }
}
