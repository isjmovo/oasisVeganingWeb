package com.ll.exam.oasisVeganingWeb.Community.comment;

import com.ll.exam.oasisVeganingWeb.Community.comment.Comment;
import com.ll.exam.oasisVeganingWeb.base.RepositoryUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long>, RepositoryUtil {
  @Transactional
  @Modifying
  @Query(value = "ALTER TABLE comment AUTO_INCREMENT = 1", nativeQuery = true)
  void truncate();
}