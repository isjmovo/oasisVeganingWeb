package com.ll.exam.oasisVeganingWeb;

import com.ll.exam.oasisVeganingWeb.Community.Community;
import com.ll.exam.oasisVeganingWeb.Community.CommunityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VeganingWebApplicationTests {
	@Autowired
	private CommunityRepository communityRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa0() {
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

		communityRepository.truncate();
	}

	@Test
	void testJpa1() {
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

	@Test
	void testJpa4() {
		Community c = communityRepository.findBySubjectAndContent("비건 햄버거 먹어봤습니다!", "비건 햄버거");
		assertEquals(1, c.getId());
	}

	void testJpa5() {
		List<Community> cList = communityRepository.findBySubjectLike("비건%");
		Community c = cList.get(0);
		assertEquals("비건 햄버거 먹어봤습니다!", c.getSubject());
	}

	void testJpa6() {
		Optional<Community> oc = communityRepository.findById(1);
		assertTrue(oc.isPresent());
		Community c = oc.orElse(null);
		c.setSubject("수정된 제목");
		this.communityRepository.save(c);
	}

	@Test
	void testJpa7() {
		assertEquals(2, communityRepository.count());
		Optional<Community> oc = communityRepository.findById(1);
		assertTrue(oc.isPresent());
		Community c = oc.get();
		this.communityRepository.delete(c);
		assertEquals(1, communityRepository.count());
	}
}