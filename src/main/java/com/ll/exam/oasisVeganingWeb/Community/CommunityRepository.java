package com.ll.exam.oasisVeganingWeb.Community;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
  Community findBySubject(String subject);
}
