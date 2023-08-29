package com.ll.exam.oasisVeganingWeb;

import com.ll.exam.oasisVeganingWeb.Community.Comment;
import com.ll.exam.oasisVeganingWeb.Community.CommentRepository;
import com.ll.exam.oasisVeganingWeb.Community.Community;
import com.ll.exam.oasisVeganingWeb.Community.CommunityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CommentRepositoryTests {
  @Autowired
  private CommunityRepository communityRepository;
  @Autowired
  private CommentRepository commentRepository;
  private long lastSampleData;

  @BeforeEach
  void beforeEach() {
    clearData();
    createSampleData();
  }

  private void createSampleData() {
  }

  private void clearData() {
    communityRepository.disableForeignKeyCheck();
    commentRepository.truncate();
    communityRepository.enableForeignKeyCheck();
  }

  @Test
  void 저장() {
    Community c = communityRepository.findById(2).get();
    Comment m = new Comment();
    m.setContent("맛있을 것 같아요!");
    m.setCommunity(c);
    m.setCreateDate(LocalDateTime.now());
    commentRepository.save(m);
  }
}