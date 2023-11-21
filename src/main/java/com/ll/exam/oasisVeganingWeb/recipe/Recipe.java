package com.ll.exam.oasisVeganingWeb.recipe;

import com.ll.exam.oasisVeganingWeb.user.SiteUser;
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
  private String subject;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;
  private LocalDateTime modifyDate;

  @ManyToOne
  private SiteUser author;

//  @OneToOne(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
//  private Image image;
}