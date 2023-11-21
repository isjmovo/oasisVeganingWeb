package com.ll.exam.oasisVeganingWeb.check;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ResultService {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private IngredientRepository ingredientRepository;
  @Autowired
  private NonVeganIngredientRepository nonVeganIngredientRepository;
  @Autowired
  private CSVService csvService;

  public boolean result(String csvData) {
    boolean vegan = true;

    String[] ingredientNames = csvData.split(",");
    //      성분 이름을 통해 성분 엔티티를 검색
    List<Ingredient> ingredients = ingredientRepository.findByNameIn(Arrays.asList(ingredientNames));

    for (String ingredientName : ingredientNames) {
      NonVeganIngredient nonVeganIngredient = nonVeganIngredientRepository.findByIngredientName(ingredientName);

      if (nonVeganIngredient != null) {
        System.out.println(ingredientName + " is not vegan"); // 비건이 아님

        vegan = false; // 비건이 아님

        break;
      }
    }

    if (vegan) {
      System.out.println("All ingredients are vegan"); // 모든 성분이 비건
    }

    return vegan;
  }

  public String csv() throws IOException, CsvException {
    // CSV 데이터를 읽어옴
    String csvData = csvService.readCSVData();

    return csvData;
  }
}