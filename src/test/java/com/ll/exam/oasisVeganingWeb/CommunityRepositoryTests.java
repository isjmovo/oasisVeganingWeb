package com.ll.exam.oasisVeganingWeb;

import com.ll.exam.oasisVeganingWeb.Community.Community;
import com.ll.exam.oasisVeganingWeb.Community.CommunityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommunityRepositoryTests {
  @Autowired
  private CommunityRepository communityRepository;
  private static long lastSampleDataId;

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
    this.communityRepository.save(c1);

    Community c2 = new Community();
    c2.setSubject("비건 도시락 쉽네요");
    c2.setContent("비건 도시락");
    c2.setCreateDate(LocalDateTime.now());
    this.communityRepository.save(c2);
    
    lastSampleDataId = c2.getId();
  }

  private void clearData() {
    communityRepository.disableForeignKeyCheck();
    communityRepository.truncate();
    communityRepository.enableForeignKeyCheck();
  }

  @Test
  void 저장() {
    Community c1 = new Community();
    c1.setSubject("비건 햄버거 먹어봤습니다!");
    c1.setContent("비건 햄버거");
    c1.setCreateDate(LocalDateTime.now());
    this.communityRepository.save(c1);

    Community c2 = new Community();
    c2.setSubject("비건 도시락 쉽네요");
    c2.setContent("비건 도시락");
    c2.setCreateDate(LocalDateTime.now());
    this.communityRepository.save(c2);

    assertThat(c1.getId()).isEqualTo(lastSampleDataId + 1);
    assertThat(c2.getId()).isEqualTo(lastSampleDataId + 2);
  }

  @Test
  void 삭제() {
    assertThat(communityRepository.count()).isEqualTo(lastSampleDataId);
    Community q = communityRepository.findById(1).get();
    communityRepository.delete(q);

    assertThat(communityRepository.count()).isEqualTo(lastSampleDataId - 1);
  }
}