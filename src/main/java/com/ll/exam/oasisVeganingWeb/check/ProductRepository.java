package com.ll.exam.oasisVeganingWeb.check;

import com.ll.exam.oasisVeganingWeb.base.RepositoryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long>, RepositoryUtil {
  Product findByName(String name);
  Page<Product> findByNameContains(String kw, Pageable pageable);
//  Page<Product> findByNameContainsOrIngredientList_nameContains(String kw1, String kw2, Pageable pageable);
//  Page<Product> findDistinctByNameContainsOrIngredientList_nameContains(String kw1, String kw2, Pageable pageable);

  @Transactional
  @Modifying
  @Query(value = "ALTER TABLE product AUTO_INCREMENT = 1", nativeQuery = true)
  void truncate();
}