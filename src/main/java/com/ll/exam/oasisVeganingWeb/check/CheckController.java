package com.ll.exam.oasisVeganingWeb.check;

import com.ll.exam.oasisVeganingWeb.img.ImageService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/vegan")
public class CheckController {
  @Autowired
  private RecommendService recommendService;
  @Autowired
  private ResultService resultService;
  @Autowired
  private CSVService csvService;
  @Autowired
  private ImageService imageService;
  @Autowired
  private SearchService searchService;

  @GetMapping("/manual")
  public String manual(){
    return "check_manual";
  }

  @GetMapping("/scan")
  public String checkVegan() {
    return "check_csv";
  }

  @GetMapping("/result")
  public String checkVeganForm(Model model) {
    try {
      // CSV 데이터를 읽어옴
      String csvData = resultService.csv();
      // 결과를 처리
      boolean isVegan = resultService.result(csvData);
      model.addAttribute("isVegan", isVegan);
      model.addAttribute("ingredientNames", csvData.split(","));

    } catch (IOException | CsvException e) {
      e.printStackTrace();
      // 오류 처리 로직 추가
    }
    return "check_result"; // 결과를 표시하는 뷰 이름
  }

  @PostMapping("/result")
  public String checkVegan(Model model) {
    try {
      // CSV 데이터를 읽어옴
      String csvData = resultService.csv();
      // 결과를 처리
      boolean isVegan = resultService.result(csvData);
      model.addAttribute("isVegan", isVegan);

    } catch (IOException | CsvException e) {
      e.printStackTrace();
      // 오류 처리 로직 추가
    }
    return "check_result"; // 결과를 표시하는 뷰 이름
  }

  @GetMapping("/recommend")
  public String recommendProductsForm(Model model) {
    try {
      // CSV 데이터를 읽어옴
      String csvData = recommendService.csv();
      // 추천 제품 목록을 가져옴
      List<Product> recommendedProducts = recommendService.recommendProducts(csvData);
      model.addAttribute("recommendedProducts", recommendedProducts);
    } catch (IOException | CsvException e) {
      e.printStackTrace();
      // 오류 처리 로직 추가
    }
    return "recommend"; // 추천 제품 목록을 표시하는 뷰 이름
  }

  @GetMapping("/list")
  public String list(String kw, Model model, @RequestParam(defaultValue = "0") int page) {
    Page<Product> paging = searchService.getList(kw, page);

    model.addAttribute("paging", paging);
    model.addAttribute("kw", kw);

    return "check_product_list";
  }
}