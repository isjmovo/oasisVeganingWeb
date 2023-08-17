package com.ll.exam.oasisVeganingWeb.user;

import com.ll.exam.oasisVeganingWeb.base.RepositoryUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long>, RepositoryUtil {
  boolean existsByUsername(String username);
  Optional<SiteUser> findByUsername(String username);

  @Transactional
  @Modifying
  @Query(value = "ALTER TABLE site_user AUTO_INCREMENT = 1", nativeQuery = true)
  void truncate();
}