package com.ll.exam.oasisVeganingWeb.recipe;

import com.ll.exam.oasisVeganingWeb.exception.DataNotFoundException;
import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
  private final RecipeRepository recipeRepository;

  public Recipe getRecipe(long id) {
    return recipeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("no %d recipe not found".formatted(id)));
  }

  public Page<Recipe> getList(int page) {
    List<Sort.Order> sorts = new ArrayList<>();
    sorts.add(Sort.Order.desc("createDate"));

    Pageable pageable = PageRequest.of(page, 9, Sort.by(sorts));

    return recipeRepository.findAll(pageable);
  }

  public void create(String subject, String content, SiteUser author) {
    Recipe r = new Recipe();
    r.setSubject(subject);
    r.setContent(content);
    r.setAuthor(author);
    r.setCreateDate(LocalDateTime.now());
    recipeRepository.save(r);
  }

  public void modify(Recipe recipe, String subject, String content) {
    recipe.setSubject(subject);
    recipe.setContent(content);
    recipe.setModifyDate(LocalDateTime.now());
    recipeRepository.save(recipe);
  }

  public void delete (Recipe recipe) {
    this.recipeRepository.delete(recipe);
  }
}