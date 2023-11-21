package com.ll.exam.oasisVeganingWeb.recipe;

import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import com.ll.exam.oasisVeganingWeb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
  private final UserService userService;
  private final RecipeService recipeService;

  @GetMapping("/list")
  public String list(Model model, @RequestParam(defaultValue = "0") int page) {
    Page<Recipe> paging = recipeService.getList(page);

    model.addAttribute("paging", paging);

    return "recipe_list";
  }

  @GetMapping("/detail/{id}")
  public String detail(Model model, @PathVariable long id) {
    Recipe recipe = recipeService.getRecipe(id);

    model.addAttribute("recipe", recipe);

    return "recipe_detail";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/modify/{id}")
  public String recipeModify(RecipeForm recipeForm, @PathVariable("id") Integer id, Principal principal) {
    Recipe recipe = this.recipeService.getRecipe(id);

    recipeForm.setSubject(recipe.getSubject());
    recipeForm.setContent(recipe.getContent());

    return "recipe_form";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/modify/{id}")
  public String recipeModify(@Valid RecipeForm recipeForm, BindingResult bindingResult, Principal principal, @PathVariable("id") Integer id) {
    if (bindingResult.hasErrors()) {
      return "post_form";
    }

    Recipe recipe = this.recipeService.getRecipe(id);

    if (!recipe.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
    }

    recipeService.modify(recipe, recipeForm.getSubject(), recipeForm.getContent());

    return String.format("redirect:/recipe/detail/%s", id);
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/delete/{id}")
  public String recipeDelete(Principal principal, @PathVariable("id") Integer id) {
    Recipe recipe = recipeService.getRecipe(id);

    if (!recipe.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
    }

    recipeService.delete(recipe);

    return "redirect:/recipe/list";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/create")
  public String recipeCreate(RecipeForm recipeForm) {
    return "recipe_form";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping(value = "/create")
  public String recipeCreate(Principal principal, Model model, @Valid RecipeForm recipeForm, BindingResult bindingResult) throws IOException {
    if (bindingResult.hasErrors()) {
      return "recipe_form";
    }

    SiteUser siteUser = userService.getUser(principal.getName());

    recipeService.create(recipeForm.getSubject(), recipeForm.getContent(), siteUser);

    return "redirect:/recipe/list";
  }
}