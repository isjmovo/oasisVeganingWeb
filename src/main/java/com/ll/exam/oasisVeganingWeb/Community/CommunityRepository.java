package com.ll.exam.oasisVeganingWeb.Community;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
  Community findBySubject(String subject);

  Community findBySubjectAndContent(String subject, String content);

  List<Community> findBySubjectLike(String subject);
}