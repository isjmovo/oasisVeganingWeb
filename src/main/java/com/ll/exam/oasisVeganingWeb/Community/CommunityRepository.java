package com.ll.exam.oasisVeganingWeb.Community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
  Community findBySubject(String subject);

  Community findBySubjectAndContent(String subject, String content);

  List<Community> findBySubjectLike(String subject);

  @Transactional
  @Modifying
  @Query(value = "truncate community", nativeQuery = true)
  void truncate();

  @Transactional
  @Modifying
  @Query(value = "SET FOREIGN_KEY_CHECKS = 0", nativeQuery = true)
  void disableForeignKeyCheck();

  @Transactional
  @Modifying
  @Query(value = "SET FOREIGN_KEY_CHECKS = 1", nativeQuery = true)
  void enableForeignKeyCheck();
}