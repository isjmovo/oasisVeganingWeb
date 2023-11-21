package com.ll.exam.oasisVeganingWeb;

import com.ll.exam.oasisVeganingWeb.Community.comment.CommentRepository;
import com.ll.exam.oasisVeganingWeb.Community.myPost.PostRepository;
import com.ll.exam.oasisVeganingWeb.recipe.RecipeRepository;
import com.ll.exam.oasisVeganingWeb.user.UserRepository;
import com.ll.exam.oasisVeganingWeb.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {
  @Autowired
  private UserService userService;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private RecipeRepository recipeRepository;

  @BeforeEach
  void beforeEach() {
    clearData();
    createSampleData();
  }

  public static void createSampleData(UserService userService) {
    userService.create("관리자", "admin", "1234");
    userService.create("유저1", "user1", "1234");
  }

  private void createSampleData() {
    createSampleData(userService);
  }

  public static void clearData(UserRepository userRepository, CommentRepository commentRepository, PostRepository postRepository, RecipeRepository recipeRepository) {
    recipeRepository.deleteAll();
    recipeRepository.truncateTable();

    commentRepository.deleteAll();
    commentRepository.truncateTable();

    postRepository.deleteAll();
    postRepository.truncateTable();

    userRepository.deleteAll();
    userRepository.truncateTable();
  }

  private void clearData() {
    clearData(userRepository, commentRepository, postRepository, recipeRepository);
  }


  @Test
  @DisplayName("회원가입이 가능하다.")
  public void t1() {
    userService.create("유저2", "user2", "1234");
  }
}