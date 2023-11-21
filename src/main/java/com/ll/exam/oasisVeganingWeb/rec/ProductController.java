//package com.ll.exam.oasisVeganingWeb.rec;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.ArrayList;
//
//@Controller
//public class ProductController {
//
//  @Autowired
//  private ProductService productService;
//
//  @GetMapping("/recommendation")
//  public String recommendationForm(Model model) {
//    model.addAttribute("recommendedProducts", new ArrayList<>());
//    return "recommend";
//  }
//
////  @PostMapping("/api/products/recommend")
////  public String recommendProducts(@RequestParam String csvData, Model model) {
////    List<Product> recommendedProducts = productService.recommendProducts(csvData);
////    model.addAttribute("recommendedProducts", recommendedProducts);
////    return "recommend";
////  }
//}