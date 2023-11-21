package com.ll.exam.oasisVeganingWeb.check;

import com.ll.exam.oasisVeganingWeb.base.RepositoryUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface NonVeganIngredientRepository extends JpaRepository<NonVeganIngredient, Long>, RepositoryUtil {
  // 동물성 성분을 이름으로 조회하는 메서드
  NonVeganIngredient findByIngredientName(String ingredientName);
  // 기타 동물성 성분과 관련된 메서드 추가 가능

  @Transactional
  @Modifying
  @Query(value = "ALTER TABLE non_vegan_ingredient AUTO_INCREMENT = 1", nativeQuery = true)
  void truncate();
}