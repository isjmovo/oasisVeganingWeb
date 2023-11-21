package com.ll.exam.oasisVeganingWeb.check;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  private String imageUrl;

  @ManyToMany
  @JoinTable(name = "product_ingredient",
      joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  private Set<Ingredient> ingredients;
}