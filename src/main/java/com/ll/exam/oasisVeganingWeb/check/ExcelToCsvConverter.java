package com.ll.exam.oasisVeganingWeb.check;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class ExcelToCsvConverter {

  public void convertExcelToCsv(String excelFilePath, String csvFilePath) {
    try {
      // Excel 파일 읽기
      FileInputStream excelFile = new FileInputStream(new File(excelFilePath));
      Workbook workbook = new XSSFWorkbook(excelFile);
      Sheet sheet = workbook.getSheetAt(0);

      // CSV 파일로 쓰기
      FileWriter csvWriter = new FileWriter(csvFilePath);

      for (Row row : sheet) {
        for (Cell cell : row) {
          csvWriter.append(cell.toString());
          csvWriter.append(",");
        }
        csvWriter.append("\n");
      }

      csvWriter.flush();
      csvWriter.close();
      workbook.close();

      System.out.println("Conversion successful. CSV file saved at: " + csvFilePath);
    } catch (IOException e) {
      System.out.println("Error during conversion: " + e.getMessage());
    }
  }
}