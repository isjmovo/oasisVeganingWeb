package com.ll.exam.oasisVeganingWeb;

import com.ll.exam.oasisVeganingWeb.Community.myPost.MyPost;
import com.ll.exam.oasisVeganingWeb.Community.myPost.PostRepository;
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
	private PostRepository postRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa0() {
		MyPost p1 = new MyPost();
		p1.setSubject("비건 햄버거 먹어봤습니다!");
		p1.setContent("비건 햄버거");
		p1.setCreateDate(LocalDateTime.now());
		postRepository.save(p1);

		MyPost p2 = new MyPost();
		p2.setSubject("비건 도시락 쉽네요");
		p2.setContent("비건 도시락");
		p2.setCreateDate(LocalDateTime.now());
		postRepository.save(p2);

		postRepository.disableForeignKeyCheck();
		postRepository.truncate();
		postRepository.enableForeignKeyCheck();
	}

	@Test
	void testJpa1() {
		MyPost p1 = new MyPost();
		p1.setSubject("비건 햄버거 먹어봤습니다!");
		p1.setContent("비건 햄버거");
		p1.setCreateDate(LocalDateTime.now());
		postRepository.save(p1);

		MyPost p2 = new MyPost();
		p2.setSubject("비건 도시락 쉽네요");
		p2.setContent("비건 도시락");
		p2.setCreateDate(LocalDateTime.now());
		postRepository.save(p2);

		assertThat(p1.getId()).isGreaterThan(0);
		assertThat(p2.getId()).isGreaterThan(p2.getId());
	}

	@Test
	void testJpa2() {
		List<MyPost> all = postRepository.findAll();
		assertEquals(2, all.size());

		MyPost p = all.get(0);
		assertEquals("비건 햄버거 먹어봤습니다!", p.getSubject());
	}

	@Test
	void testJpa3() {
		MyPost p = postRepository.findBySubject("비건 햄버거 먹어봤습니다!");
		assertEquals(1, p.getId());
	}

	@Test
	void testJpa4() {
		MyPost p = postRepository.findBySubjectAndContent("비건 햄버거 먹어봤습니다!", "비건 햄버거");
		assertEquals(1, p.getId());
	}

	void testJpa5() {
		List<MyPost> pList = postRepository.findBySubjectLike("비건%");
		MyPost p = pList.get(0);
		assertEquals("비건 햄버거 먹어봤습니다!", p.getSubject());
	}

	void testJpa6() {
		Optional<MyPost> op = postRepository.findById(1L);
		assertTrue(op.isPresent());
		MyPost p = op.orElse(null);
		p.setSubject("수정된 제목");
		this.postRepository.save(p);
	}

	@Test
	void testJpa7() {
		assertEquals(2, postRepository.count());
		Optional<MyPost> op = postRepository.findById(1L);
		assertTrue(op.isPresent());
		MyPost p = op.get();
		this.postRepository.delete(p);
		assertEquals(1, postRepository.count());
	}
}