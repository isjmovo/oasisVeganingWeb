package com.ll.exam.oasisVeganingWeb.check;

import com.ll.exam.oasisVeganingWeb.base.RepositoryUtil;
import com.ll.exam.oasisVeganingWeb.check.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long>, RepositoryUtil {
  Product findByName(String name);

  @Transactional
  @Modifying
  @Query(value = "ALTER TABLE product AUTO_INCREMENT = 1", nativeQuery = true)
  void truncate();
}