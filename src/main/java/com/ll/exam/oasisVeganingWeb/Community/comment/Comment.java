package com.ll.exam.oasisVeganingWeb.Community.comment;

import com.ll.exam.oasisVeganingWeb.Community.myPost.MyPost;
import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;
  private LocalDateTime modifyDate;

  @ManyToOne
  private MyPost my_post;

  @ManyToOne
  private SiteUser author;
}