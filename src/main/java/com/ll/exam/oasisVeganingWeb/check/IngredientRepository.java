package com.ll.exam.oasisVeganingWeb.check;

import com.ll.exam.oasisVeganingWeb.base.RepositoryUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>, RepositoryUtil {
  List<Ingredient> findByNameIn(List<String> names);

  @Transactional
  @Modifying
  @Query(value = "ALTER TABLE ingredient AUTO_INCREMENT = 1", nativeQuery = true)
  void truncate();
}