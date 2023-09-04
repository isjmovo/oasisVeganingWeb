package com.ll.exam.oasisVeganingWeb;

import com.ll.exam.oasisVeganingWeb.Community.comment.Comment;
import com.ll.exam.oasisVeganingWeb.Community.comment.CommentRepository;
import com.ll.exam.oasisVeganingWeb.Community.myPost.PostRepository;
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

  @BeforeEach
  void beforeEach() {
    clearData();
    createSampleData();
  }

  public static void createSampleData(UserService userService) {
    userService.create("관리자", "admin", "admin@test.com", "1234", "01012341234", "대한민국", "");
    userService.create("유저1", "user1", "user1@test.com", "1234", "01012345678", "대한민국", "");
  }

  private void createSampleData() {
    createSampleData(userService);
  }

  public static void clearData(UserRepository userRepository, CommentRepository commentRepository, PostRepository postRepository) {
    commentRepository.deleteAll();
    commentRepository.truncateTable();

    postRepository.deleteAll();
    postRepository.truncateTable();

    userRepository.deleteAll();
    userRepository.truncateTable();
  }

  private void clearData() {
    clearData(userRepository, commentRepository, postRepository);
  }


  @Test
  @DisplayName("회원가입이 가능하다.")
  public void t1() {
    userService.create("유저2", "user2", "user2@email.com", "1234", "01056785678", "대한민국", "");
  }
}