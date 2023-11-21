package com.ll.exam.oasisVeganingWeb.img;

import com.ll.exam.oasisVeganingWeb.base.RepositoryUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ImageRepository extends JpaRepository<Image, Long>, RepositoryUtil {
  @Transactional
  @Modifying
  @Query(value = "ALTER TABLE image AUTO_INCREMENT = 1", nativeQuery = true)
  void truncate();
}