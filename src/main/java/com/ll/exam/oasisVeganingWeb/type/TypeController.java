package com.ll.exam.oasisVeganingWeb.type;

import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import com.ll.exam.oasisVeganingWeb.user.UserRepository;
import com.ll.exam.oasisVeganingWeb.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/type")
public class TypeController {

  @Autowired
  private TypeService typeService;
  @Autowired
  private UserService userService;
  private UserRepository userRepository;

  // 현재 진행 중인 질문 번호를 저장하는 변수
  private int currentQuestionIndex = 0;

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/test")
  public String showTypeQuiz(Model model) {
    String currentQuestion = typeService.getQuestion(currentQuestionIndex);
    model.addAttribute("currentQuestion", currentQuestion);
    return "test_form";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/test")
  public String processTypeQuiz(@RequestParam("answer") int answer, Model model, Principal principal) {
    // 사용자의 답을 처리
    typeService.processAnswer(currentQuestionIndex, answer);

    // 모든 질문에 대한 답을 받았으면 결과를 보여줌
    if (currentQuestionIndex + 1 == typeService.getTotalQuestions()) {
      String result = typeService.calculateResult();
      model.addAttribute("result", result);

      // 사용자 엔티티에 결과 저장
      SiteUser user = userService.getUser(principal.getName());
      user.setType(result);

      return "typeResult";
    }

    // 다음 질문으로 진행
    currentQuestionIndex++;

    return "redirect:/type/test";
  }
}