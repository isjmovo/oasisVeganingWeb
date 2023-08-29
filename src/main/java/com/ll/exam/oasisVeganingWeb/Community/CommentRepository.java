package com.ll.exam.oasisVeganingWeb.Community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
  @Transactional
  @Modifying
  @Query(value = "truncate comment", nativeQuery = true)
  void truncate();
}