//package com.ll.exam.oasisVeganingWeb.rec;
//
//import com.ll.exam.oasisVeganingWeb.check.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class ProductService {
//  @Autowired
//  private ProductRepository productRepository;
//  @Autowired
//  private IngredientRepository ingredientRepository;
//  @Autowired
//  private NonVeganIngredientRepository nonVeganIngredientRepository;
//
//  public boolean result(String csvData) {
//    String[] ingredientNames = csvData.split(",");
//
//    // 성분 이름을 통해 성분 엔티티를 검색
//    List<Ingredient> ingredients = ingredientRepository.findByNameIn(Arrays.asList(ingredientNames));
//
//    List<Product> recommendedProducts = new ArrayList<>();
//
//    // isVegan 메서드 호출
//    boolean isVeganResult = isVegan(ingredientNames);
//
//    return isVeganResult;
//  }
//
//  public boolean isVegan(String[] ingredientNames) {
//    for (String ingredientName : ingredientNames) {
//      NonVeganIngredient nonVeganIngredient = nonVeganIngredientRepository.findByIngredientName(ingredientName);
//      if (nonVeganIngredient != null) {
//        System.out.println(ingredientName + " is not vegan"); // 비건이 아님
//
//        return false; // 비건이 아님
//      }
//    }
//    System.out.println("All ingredients are vegan"); // 모든 성분이 비건
//
//    return true; // 비건
//  }
//}