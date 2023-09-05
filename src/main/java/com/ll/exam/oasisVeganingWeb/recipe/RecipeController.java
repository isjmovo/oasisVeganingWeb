package com.ll.exam.oasisVeganingWeb.recipe;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
  @GetMapping
  public String recipe() {
    return "recipe_form";
  }
}