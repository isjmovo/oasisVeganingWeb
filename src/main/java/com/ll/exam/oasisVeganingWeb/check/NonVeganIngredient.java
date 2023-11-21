package com.ll.exam.oasisVeganingWeb.check;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NonVeganIngredient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String ingredientName;
//  private boolean isAnimalDerived; // 동물성 성분 여부를 나타내는 플래그
}