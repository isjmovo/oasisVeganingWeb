package com.ll.exam.oasisVeganingWeb;

import com.ll.exam.oasisVeganingWeb.Community.Community;
import com.ll.exam.oasisVeganingWeb.Community.CommunityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VeganingWebApplicationTests {
	@Autowired
	private CommunityRepository communityRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa() {
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

		assertThat(c1.getId()).isGreaterThan(0);
		assertThat(c2.getId()).isGreaterThan(c1.getId());
	}

	@Test
	void testJpa2() {
		List<Community> all = communityRepository.findAll();
		assertEquals(2, all.size());

		Community c = all.get(0);
		assertEquals("비건 햄버거 먹어봤습니다!", c.getSubject());
	}

	@Test
	void testJpa3() {
		Community c = communityRepository.findBySubject("비건 햄버거 먹어봤습니다!");
		assertEquals(1, c.getId());
	}
}