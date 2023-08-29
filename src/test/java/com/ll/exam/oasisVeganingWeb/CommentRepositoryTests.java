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
    Community c1 = new Community();
    c1.setSubject("비건 햄버거 먹어봤습니다!");
    c1.setContent("비건 햄버거");
    c1.setCreateDate(LocalDateTime.now());
    communityRepository.save(c1);

    Community c2 = new Community();
    c2.setSubject("비건 도시락 쉽네요");
    c2.setContent("비건 도시락");
    c2.setCreateDate(LocalDateTime.now());
    communityRepository.save(c2);
  }

  private void clearData() {
    communityRepository.disableForeignKeyCheck();
    communityRepository.truncate();
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