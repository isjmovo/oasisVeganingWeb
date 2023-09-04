package com.ll.exam.oasisVeganingWeb.Community.myPost;

import com.ll.exam.oasisVeganingWeb.base.RepositoryUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<MyPost, Long>, RepositoryUtil {
  MyPost findBySubject(String subject);

  MyPost findBySubjectAndContent(String subject, String content);

  List<MyPost> findBySubjectLike(String subject);

  @Transactional
  @Modifying
  @Query(value = "ALTER TABLE my_post AUTO_INCREMENT = 1", nativeQuery = true)
  void truncate();
}