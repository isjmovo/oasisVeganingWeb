package com.ll.exam.oasisVeganingWeb.recipe;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Recipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 200)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;
}