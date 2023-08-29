package com.ll.exam.oasisVeganingWeb.Community;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Community {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
  private Long id;

  @Column(length = 200)
  private String subject;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;
}