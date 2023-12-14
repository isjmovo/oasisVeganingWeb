package com.ll.exam.oasisVeganingWeb.type;

import com.ll.exam.oasisVeganingWeb.user.UserRepository;
import com.ll.exam.oasisVeganingWeb.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TypeService {
  private UserService userService;
  private UserRepository userRepository;

  // 질문 목록
  private static final List<String> questions = Arrays.asList(
      "최근 채소 섭취를 하신 적이 있습니까?",
      "최근 우유, 요거트, 치즈 등의 유제품을 하신 적이 있습니까?",
      "최근 달걀 섭취를 하신 적이 있습니까?",
      "최근 생선, 조개, 새우 등 어패류를 섭취하신 적이 있습니까?",
      "최근 닭, 오리 등 품종 개량을 하여 육성한 조류를 섭취하신 적이 있습니까?",
      "최근 육류를 섭취하신 적이 있습니까?"
  );

  // 각 질문에 대한 답을 저장하는 리스트
  private List<Integer> answers = new ArrayList<>();

  // 질문 목록 반환
  public List<String> getQuestions() {
    return questions;
  }

  // 현재 질문 번호에 해당하는 질문 반환
  public String getQuestion(int index) {
    if (index < questions.size()) {
      return questions.get(index);
    }
    return "";
  }

  // 사용자의 답을 처리
  public void processAnswer(int questionIndex, int answer) {
    // 각 질문에 대한 답을 저장
    answers.add(answer);
  }

  // 총 질문 개수 반환
  public int getTotalQuestions() {
    return questions.size();
  }

  public String calculateResult() {
    // 기본 결과는 "플렉시테리언"
    String result = "플렉시테리언";

    // 각 유형에 대한 설명을 저장하는 배열
    String[] type = new String[]{"비건", "락토", "오보", "페스코", "폴로", "플렉시테리언"};

    // 배열 answers를 순회하며 각 유형에 대한 조건을 검사
    for (int i = 0; i < 6; i++) {
      if (answers.get(i) == 1) {
        // 현재 인덱스가 1이고 다음 인덱스(2)도 1인 경우 "락토오보"로 결과 설정
        if (i == 1 && answers.get(2) == 1) {
          result = "락토오보";
        } else {
          // 그 외의 경우에는 현재 인덱스에 해당하는 유형으로 결과 설정
          result = type[i];
        }
      }
    }

    // 최종 결과 반환
    return result;
  }
}