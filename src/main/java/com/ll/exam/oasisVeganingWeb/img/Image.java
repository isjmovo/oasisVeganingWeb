package com.ll.exam.oasisVeganingWeb.img;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="origin_name")
  private String originName;
  @Column(name="stored_image_path")
  private String storedImagePath;

//  @OneToOne(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
//  private Recipe recipe;

//  @OneToOne(mappedBy = "image", cascade = CascadeType.ALL)
//  private Recipe recipe;

//  @OneToMany(mappedBy = "my_post", cascade = CascadeType.ALL)
//  private List<Comment> commentList = new ArrayList<>();
}

//  @ManyToOne(fetch= FetchType.LAZY)
//  @JoinColumn(name="my_post_id")
//  private MyPost myPost;