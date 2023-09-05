package com.ll.exam.oasisVeganingWeb;

import com.ll.exam.oasisVeganingWeb.Community.comment.CommentRepository;
import com.ll.exam.oasisVeganingWeb.Community.myPost.MyPost;
import com.ll.exam.oasisVeganingWeb.Community.myPost.PostRepository;
import com.ll.exam.oasisVeganingWeb.recipe.RecipeRepository;
import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import com.ll.exam.oasisVeganingWeb.user.UserRepository;
import com.ll.exam.oasisVeganingWeb.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostRepositoryTests {
  @Autowired
  private UserService userService;
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RecipeRepository recipeRepository;

  private static long lastSampleDataId;

  @BeforeEach
  void beforeEach() {
    clearData();
    createSampleData();
  }

  public static long createSampleData(UserService userService, PostRepository postRepository) {
    UserServiceTests.createSampleData(userService);

    MyPost p1 = new MyPost();
    p1.setSubject("비건 햄버거 먹어봤습니다!");
    p1.setContent("비건 햄버거");
    p1.setAuthor(new SiteUser(1L));
    p1.setCreateDate(LocalDateTime.now());
    postRepository.save(p1);

    MyPost p2 = new MyPost();
    p2.setSubject("비건 도시락 쉽네요");
    p2.setContent("비건 도시락");
    p2.setAuthor(new SiteUser(2L));
    p2.setCreateDate(LocalDateTime.now());
    postRepository.save(p2);
    
    return p2.getId();
  }

  private void createSampleData() {
    lastSampleDataId = createSampleData(userService, postRepository);
  }

  public static void clearData(UserRepository userRepository, CommentRepository commentRepository, PostRepository postRepository, RecipeRepository recipeRepository) {
    UserServiceTests.clearData(userRepository, commentRepository, postRepository, recipeRepository);
  }

  private void clearData() {
    clearData(userRepository, commentRepository, postRepository, recipeRepository);
  }

  @Test
  void 저장() {
    MyPost p1 = new MyPost();
    p1.setSubject("비건 햄버거 먹어봤습니다!");
    p1.setContent("비건 햄버거");
    p1.setAuthor(new SiteUser(1L));
    p1.setCreateDate(LocalDateTime.now());
    this.postRepository.save(p1);

    MyPost p2 = new MyPost();
    p2.setSubject("비건 도시락 쉽네요");
    p2.setContent("비건 도시락");
    p2.setAuthor(new SiteUser(2L));
    p2.setCreateDate(LocalDateTime.now());
    this.postRepository.save(p2);

    assertThat(p1.getId()).isEqualTo(lastSampleDataId + 1);
    assertThat(p2.getId()).isEqualTo(lastSampleDataId + 2);
  }

  @Test
  void 삭제() {
    assertThat(postRepository.count()).isEqualTo(lastSampleDataId);
    MyPost p = postRepository.findById(1L).get();
    postRepository.delete(p);

    assertThat(postRepository.count()).isEqualTo(lastSampleDataId - 1);
  }

  @Test
  void 수정() {
    assertThat(postRepository.count()).isEqualTo(lastSampleDataId);

    MyPost p = postRepository.findById(1L).get();
    p.setSubject("수정된 제목");
    postRepository.save(p);

    p = postRepository.findById(1L).get();
    assertThat(p.getSubject()).isEqualTo("수정된 제목");
  }

  @Test
  void findAll() {
    List<MyPost> all = postRepository.findAll();
    assertThat(all.size()).isEqualTo(lastSampleDataId);

    MyPost p = all.get(0);
    assertThat(p.getSubject()).isEqualTo("비건 햄버거 먹어봤습니다!");
  }

  @Test
  void pageable() {
    Pageable pageable = PageRequest.of(0, (int) lastSampleDataId);
    Page<MyPost> page = postRepository.findAll(pageable);

    assertThat(page.getTotalPages()).isEqualTo(1);

    System.out.println(page.getNumber());
  }

  @Test
  void findBySubject() {
    MyPost p = postRepository.findBySubject("비건 햄버거 먹어봤습니다!");
    assertThat(p.getId()).isEqualTo(1);
  }

  @Test
  void findBySubjectAndContent() {
    MyPost p = postRepository.findBySubjectAndContent("비건 햄버거 먹어봤습니다!", "비건 햄버거");
    assertThat(p.getId()).isEqualTo(1);
  }

  @Test
  void findBySubjectLike() {
    List<MyPost> pList = postRepository.findBySubjectLike("비건%");
    MyPost p = pList.get(0);
    assertThat(p.getSubject()).isEqualTo("비건 햄버거 먹어봤습니다!");
  }

  @Test
  void createManySampleData() {
    boolean run = false;

    if (run == false) return;

    IntStream.rangeClosed(3, 300).forEach(id -> {
      MyPost p = new MyPost();
      p.setSubject("%d번 게시물".formatted(id));
      p.setContent("%d번 게시물의 내용".formatted(id));
      p.setAuthor(new SiteUser(2L));
      p.setCreateDate(LocalDateTime.now());

      postRepository.save(p);
    });
  }
}