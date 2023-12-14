package com.ll.exam.oasisVeganingWeb.user;

import com.ll.exam.oasisVeganingWeb.Community.myPost.MyPost;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class SiteUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  @NotNull
  @Column(unique = true)
  private String username;

  @NotNull
  private String password;

  @ElementCollection
  private List<String> allergies;

  private String quizResultType;

  public SiteUser(long id) {
    this.id = id;
  }

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private List<MyPost> myPosts;
}