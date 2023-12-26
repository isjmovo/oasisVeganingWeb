package com.ll.exam.oasisVeganingWeb.check;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
  @Autowired
  private IngredientRepository ingredientRepository;

  @Value("${csv.file.path}")
  private String csvFilePath;

  @Value("${excel.file.path}")
  private String excelFilePath;

  public String readCSVData() throws IOException, CsvException {
    List<String[]> rows = null;

    try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFilePath)).withSkipLines(1).build()) {
      rows = csvReader.readAll();

    } catch (IOException e) {
      e.printStackTrace();

      // CSV 파일이 없을 경우 ExcelToCsvConverter를 실행하여 CSV 생성
      System.out.println("CSV 파일을 찾을 수 없습니다. ExcelToCsvConverter를 실행합니다...");
      ExcelToCsvConverter converter = new ExcelToCsvConverter();
      converter.convertExcelToCsv(excelFilePath, csvFilePath);

      // 재시도: 생성된 CSV 파일 읽기
      try (CSVReader csvReaderRetry = new CSVReaderBuilder(new FileReader(csvFilePath)).withSkipLines(1).build()) {
        rows = csvReaderRetry.readAll();
      } catch (IOException ex) {
        ex.printStackTrace();
        throw ex; // 예외 처리 필요
      }
    }

    // CSV 데이터를 읽어와서 문자열로 변환
    StringBuilder csvData = new StringBuilder();
    for (String[] row : rows) {
      if (row.length >= 1) {
        csvData.append(row[0]).append(",");
      }
    }

    return csvData.toString();
  }

  // ExcelToCsvConverter 실행 메서드 추가
  public void convertExcelToCSV() {
    ExcelToCsvConverter converter = new ExcelToCsvConverter();
    converter.convertExcelToCsv(excelFilePath, csvFilePath);
  }
}