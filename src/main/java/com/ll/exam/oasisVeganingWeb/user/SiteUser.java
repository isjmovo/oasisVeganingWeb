package com.ll.exam.oasisVeganingWeb.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class SiteUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(unique = true)
  private String username;

  private String password;

  private String email;

  @Column(unique = true)
  private String phone;

  private String address;

  private String allergy;

  public SiteUser(long id) {
    this.id = id;
  }
}