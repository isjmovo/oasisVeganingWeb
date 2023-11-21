package com.ll.exam.oasisVeganingWeb.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
  private String questionText;
  private Integer answer;

  public Question(String questionText, Integer answer) {
    this.questionText = questionText;
    this.answer = answer;
  }
}
