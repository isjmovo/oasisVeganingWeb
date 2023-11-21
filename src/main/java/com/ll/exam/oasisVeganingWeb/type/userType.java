package com.ll.exam.oasisVeganingWeb.type;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class userType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;  // 사용자 이름
  private String resultType;  // 퀴즈 결과 타입 (A, B, C 등)
  private String resultDescription;  // 퀴즈 결과 설명
}
