package com.ll.exam.oasisVeganingWeb.recipe;

import com.ll.exam.oasisVeganingWeb.exception.DataNotFoundException;
import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RecipeService {
  private final RecipeRepository recipeRepository;

  public Recipe getRecipe(long id) {
    return recipeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("no %d recipe not found".formatted(id)));
  }

  public void create(String title, String content, SiteUser author) {
    Recipe r = new Recipe();
    r.setTitle(title);
    r.setContent(content);
    r.setAuthor(author);
    r.setCreateDate(LocalDateTime.now());
    recipeRepository.save(r);
  }

  public void modify(Recipe recipe, String title, String content) {
    recipe.setTitle(title);
    recipe.setContent(content);
    recipe.setModifyDate(LocalDateTime.now());
    recipeRepository.save(recipe);
  }

  public void delete (Recipe recipe) {
    this.recipeRepository.delete(recipe);
  }
}