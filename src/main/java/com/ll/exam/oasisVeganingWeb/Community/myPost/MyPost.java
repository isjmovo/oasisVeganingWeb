package com.ll.exam.oasisVeganingWeb.Community.myPost;

import com.ll.exam.oasisVeganingWeb.Community.comment.Comment;
import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class MyPost {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
  private Long id;

  @Column(length = 200)
  private String subject;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;
  private LocalDateTime modifyDate;

  @ManyToOne
  private SiteUser author;

  @OneToMany(mappedBy = "my_post", cascade = CascadeType.ALL)
  private List<Comment> commentList = new ArrayList<>();

  public void addComment(Comment comment) {
    comment.setMy_post(this);
    getCommentList().add(comment);
  }
}