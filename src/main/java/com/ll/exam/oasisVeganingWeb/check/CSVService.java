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

  public String readCSVData() throws IOException, CsvException {
    List<String[]> rows = null;

    try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFilePath)).withSkipLines(1).build()) {
      rows = csvReader.readAll();

    } catch (IOException e) {
      e.printStackTrace();
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
}