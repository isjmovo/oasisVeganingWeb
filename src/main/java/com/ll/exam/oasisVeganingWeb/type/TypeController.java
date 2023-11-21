package com.ll.exam.oasisVeganingWeb.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/type")
public class TypeController {
  @Autowired
  private TypeService typeService;

  @GetMapping("/test")
  public String showQuiz(Model model) {
    Question currentQuestion = typeService.getCurrentQuestion();

    if (currentQuestion == null) {
      // 모든 질문이 끝났을 때 결과 페이지로 이동
      return navigateToResultPage(model);
    }

    model.addAttribute("question", currentQuestion);
    return "test_form";
  }

  @PostMapping("/test")
  public String processAnswer(@RequestParam("userAnswer") int userAnswer, Model model) {
    typeService.recordUserAnswer(userAnswer);

    // 모든 질문이 끝났을 때 결과 페이지로 이동
    if (typeService.getCurrentQuestion() == null) {
      return navigateToResultPage(model);
    }

    return "redirect:/type/test"; // 다음 질문으로 이동
  }

  @GetMapping("/result")
  public String showResult(Model model) {
    Type result = typeService.getResult();

    // 결과 및 결과 설명을 모델에 추가
    model.addAttribute("result", result);
    model.addAttribute("resultDescription", result.getDescription());

    return "typeResult"; // 결과 페이지 템플릿으로 이동
  }

  private String navigateToResultPage(Model model) {
    // 결과 페이지로 이동하는 로직 추가
    return "redirect:/type/result";
  }
}