//package com.ll.exam.oasisVeganingWeb;
//
//import com.ll.exam.oasisVeganingWeb.Community.comment.CommentRepository;
//import com.ll.exam.oasisVeganingWeb.Community.myPost.PostRepository;
//import com.ll.exam.oasisVeganingWeb.recipe.Recipe;
//import com.ll.exam.oasisVeganingWeb.recipe.RecipeRepository;
//import com.ll.exam.oasisVeganingWeb.user.SiteUser;
//import com.ll.exam.oasisVeganingWeb.user.UserRepository;
//import com.ll.exam.oasisVeganingWeb.user.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class RecipeRepositoryTests {
//  @Autowired
//  private UserService userService;
//  @Autowired
//  private RecipeRepository recipeRepository;
//  @Autowired
//  private UserRepository userRepository;
//  @Autowired
//  private PostRepository postRepository;
//  @Autowired
//  private CommentRepository commentRepository;
//
//  private static long lastSampleDataId;
//
//  @BeforeEach
//  void beforeEach() {
//    clearData();
//    createSampleData();
//  }
//
//  public static long createSampleData(UserService userService, RecipeRepository recipeRepository) {
//    UserServiceTests.createSampleData(userService);
//
//    Recipe r1 = new Recipe();
//    r1.setSubject("비건 떡국");
//    r1.setContent("캐슈넛 떡국");
//    r1.setAuthor(new SiteUser(1L));
//    r1.setCreateDate(LocalDateTime.now());
//    recipeRepository.save(r1);
//
//    Recipe r2 = new Recipe();
//    r2.setSubject("두부 강된장");
//    r2.setContent("두부를 이용한 강된장");
//    r2.setAuthor(new SiteUser(2L));
//    r2.setCreateDate(LocalDateTime.now());
//    recipeRepository.save(r2);
//
//    return r2.getId();
//  }
//
//  private void createSampleData() {
//    lastSampleDataId = createSampleData(userService, recipeRepository);
//  }
//
//  public static void clearData(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, RecipeRepository recipeRepository) {
//    UserServiceTests.clearData(userRepository, commentRepository, postRepository, recipeRepository);
//  }
//
//  private void clearData() {
//    clearData(userRepository, postRepository, commentRepository, recipeRepository);
//  }
//
//  @Test
//  void 저장() {
//    Recipe r1 = new Recipe();
//    r1.setSubject("비건 떡국");
//    r1.setContent("캐슈넛 떡국");
//    r1.setAuthor(new SiteUser(1L));
//    r1.setCreateDate(LocalDateTime.now());
//    this.recipeRepository.save(r1);
//
//    Recipe r2 = new Recipe();
//    r2.setSubject("두부 강된장");
//    r2.setContent("두부를 이용한 강된장");
//    r2.setAuthor(new SiteUser(2L));
//    r2.setCreateDate(LocalDateTime.now());
//    this.recipeRepository.save(r2);
//
//    assertThat(r1.getId()).isEqualTo(lastSampleDataId + 1);
//    assertThat(r2.getId()).isEqualTo(lastSampleDataId + 2);
//  }
//
//  @Test
//  void 삭제() {
//    assertThat(recipeRepository.count()).isEqualTo(lastSampleDataId);
//    Recipe r = recipeRepository.findById(1L).get();
//    recipeRepository.delete(r);
//
//    assertThat(recipeRepository.count()).isEqualTo(lastSampleDataId - 1);
//  }
//
//  @Test
//  void 수정() {
//    assertThat(recipeRepository.count()).isEqualTo(lastSampleDataId);
//
//    Recipe r = recipeRepository.findById(1L).get();
//    r.setSubject("수정된 제목");
//    recipeRepository.save(r);
//
//    r = recipeRepository.findById(1L).get();
//    assertThat(r.getSubject()).isEqualTo("수정된 제목");
//  }
//
//  @Test
//  void findAll() {
//    List<Recipe> all = recipeRepository.findAll();
//    assertThat(all.size()).isEqualTo(lastSampleDataId);
//
//    Recipe r = all.get(0);
//    assertThat(r.getSubject()).isEqualTo("비건 떡국");
//  }
//
//  @Test
//  void findByTitle() {
//    Recipe r = recipeRepository.findByTitle("비건 떡국");
//    assertThat(r.getId()).isEqualTo(1);
//  }
//
//  @Test
//  void findByTitleAndContent() {
//    Recipe r = recipeRepository.findByTitleAndContent("비건 떡국", "캐슈넛 떡국");
//    assertThat(r.getId()).isEqualTo(1);
//  }
//
//  @Test
//  void findByTitleLike() {
//    List<Recipe> rList = recipeRepository.findByTitleLike("비건%");
//    Recipe r = rList.get(0);
//    assertThat(r.getSubject()).isEqualTo("비건 떡국");
//  }
//}