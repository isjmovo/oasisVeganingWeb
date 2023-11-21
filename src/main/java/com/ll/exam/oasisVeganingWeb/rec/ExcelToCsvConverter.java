//package com.ll.exam.oasisVeganingWeb.rec;
//
//import org.apache.poi.ss.usermodel.*;
//
//import java.io.*;
//
//public class ExcelToCsvConverter {
//
//  public static void main(String[] args) {
//    String excelFilePath = "C://work//data//data.xls";
//    String csvFilePath = "C://work//data.csv";
//
//    try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath));
//         BufferedWriter csvWriter = new BufferedWriter(new FileWriter(csvFilePath))) {
//
//      // 엑셀 파일에서 첫 번째 시트를 가져옴
//      Sheet sheet = workbook.getSheetAt(0);
//
//      for (Row row : sheet) {
//        for (Cell cell : row) {
//          // CSV 파일로 데이터를 쓰고 각 셀을 쉼표로 구분
//          csvWriter.write(formatCell(cell));
//          csvWriter.write(",");
//        }
//        csvWriter.newLine();
//      }
//
//      System.out.println("Excel 파일을 CSV 파일로 변환했습니다: " + csvFilePath);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  private static String formatCell(Cell cell) {
//    if (cell.getCellType() == CellType.STRING) {
//      return cell.getStringCellValue();
//    } else if (cell.getCellType() == CellType.NUMERIC) {
//      return String.valueOf(cell.getNumericCellValue());
//    } else if (cell.getCellType() == CellType.BOOLEAN) {
//      return String.valueOf(cell.getBooleanCellValue());
//    } else {
//      return "";
//    }
//  }
//}