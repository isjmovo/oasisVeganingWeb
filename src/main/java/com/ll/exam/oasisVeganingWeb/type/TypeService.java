package com.ll.exam.oasisVeganingWeb.type;

import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import com.ll.exam.oasisVeganingWeb.user.UserRepository;
import com.ll.exam.oasisVeganingWeb.user.UserService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeService {
  private List<Question> questions;
  private Map<Integer, Type> userAnswers;
  private int currentQuestionIndex;
  private UserService userService;
  private UserRepository userRepository;

  public TypeService() {
    questions = new ArrayList<>();
    questions.add(new Question("최근 채소 섭취를 하신 적이 있습니까?", null));
    questions.add(new Question("최근 우유, 요거트, 치즈 등의 유제품을 하신 적이 있습니까?", null));
    questions.add(new Question("최근 달걀 섭취를 하신 적이 있습니까?", null));
    questions.add(new Question("최근 생선, 조개, 새우 등 어패류를 섭취하신 적이 있습니까?", null));
    questions.add(new Question("최근 닭, 오리 등 품종 개량을 하여 육성한 조류를 섭취하신 적이 있습니까?", null));
    questions.add(new Question("최근 육류를 섭취하신 적이 있습니까?", null));

    currentQuestionIndex = 0;
    userAnswers = new HashMap<>();
  }

  public Type getResult() {
    return userAnswers.get(questions.size() - 1);  // 가장 마지막에 대답한 질문에 대한 결과
  }

  public void recordUserAnswer(Integer userAnswer) {
    Type result = calculateResult(userAnswer);
    userAnswers.put(currentQuestionIndex, result);

    // 현재 질문 인덱스 증가
    currentQuestionIndex++;

    // 현재 질문 인덱스가 질문의 총 개수와 동일하다면 모든 질문에 답했으므로 결과 페이지로 이동
    if (currentQuestionIndex == questions.size()) {
      navigateToResultPage();
    }
  }

  private Type calculateResult(Integer userAnswer) {
    Type type;

    if (userAnswer == 1) {
      type = Type.A; // 기본적으로 A 타입으로 초기화

      // 각 질문에 대한 로직 추가
      if (currentQuestionIndex >= 2) {
        type = Type.B;
      }
      else if (currentQuestionIndex >= 3) {
        type = Type.C;
      }
      else if (currentQuestionIndex >= 4) {
        type = Type.E;
      }
      else if (currentQuestionIndex >= 5) {
        type = Type.F;
      }
      else if (currentQuestionIndex >= 6) {
        type = Type.UNDEFINED;
      }
    } else {
      type = Type.A;
    }

    return type;
  }

  public Question getCurrentQuestion() {
    if (currentQuestionIndex < questions.size()) {
      return questions.get(currentQuestionIndex);
    }

    return null;
  }

  private void navigateToResultPage() {
    // 여기에서 결과 페이지로 이동하는 로직을 추가하면 됩니다.
    // 예: Spring MVC에서는 Controller를 호출하여 결과 페이지로 이동할 수 있습니다.
    // ModelAndView mav = new ModelAndView("resultPage");
    // mav.addObject("result", getResult());
    // return mav;
  }

  public void saveQuizResultType(Principal principal, String quizResultType) {
    SiteUser user = userService.getUser(principal.getName());

    if (user != null) {
      user.setQuizResultType(quizResultType);
      userRepository.save(user);
    }
  }
}