package com.ll.exam.oasisVeganingWeb.recipe;

import com.ll.exam.oasisVeganingWeb.base.RepositoryUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, RepositoryUtil {
  Recipe findByTitle(String title);

  Recipe findByTitleAndContent(String title, String content);

  List<Recipe> findByTitleLike(String title);

  @Transactional
  @Modifying
  @Query(value = "ALTER TABLE recipe AUTO_INCREMENT = 1", nativeQuery = true)
  void truncate();
}