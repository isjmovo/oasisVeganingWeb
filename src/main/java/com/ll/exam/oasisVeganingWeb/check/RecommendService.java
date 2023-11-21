package com.ll.exam.oasisVeganingWeb.check;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class RecommendService {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private IngredientRepository ingredientRepository;
  @Autowired
  private NonVeganIngredientRepository nonVeganIngredientRepository;
  @Autowired
  private CSVService csvService;

  public List<Product> recommendProducts(String csvData) {
    String[] ingredientNames = csvData.split(",");

    // 성분 이름을 통해 성분 엔티티를 검색
    List<Ingredient> ingredients = ingredientRepository.findByNameIn(Arrays.asList(ingredientNames));

    List<Product> recommendedProducts = new ArrayList<>();

    for (Product product : productRepository.findAll()) {
      Set<Ingredient> productIngredients = product.getIngredients();
      int totalIngredients = productIngredients.size();

      if (totalIngredients > 0) {
        int matchingIngredients = 0;

        for (Ingredient ingredient : ingredients) {
          if (productIngredients.contains(ingredient)) {
            matchingIngredients++;
          }
        }

        double similarity = (double) matchingIngredients / (totalIngredients + ingredients.size() - matchingIngredients);

        if (similarity >= 0.1) {
          recommendedProducts.add(product);
        }
      }
    }

    // 추천된 제품들을 콘솔에 출력
    for (Product recommendedProduct : recommendedProducts) {
      System.out.println("Recommended Product: " + recommendedProduct.getName());
    }

    return recommendedProducts;
  }

  public String csv() throws IOException, CsvException {
    // CSV 데이터를 읽어옴
    String csvData = csvService.readCSVData();

    return csvData;
  }
}