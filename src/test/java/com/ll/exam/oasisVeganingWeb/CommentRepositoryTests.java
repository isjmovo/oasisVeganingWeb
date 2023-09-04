package com.ll.exam.oasisVeganingWeb;

import com.ll.exam.oasisVeganingWeb.Community.comment.Comment;
import com.ll.exam.oasisVeganingWeb.Community.comment.CommentRepository;
import com.ll.exam.oasisVeganingWeb.Community.myPost.MyPost;
import com.ll.exam.oasisVeganingWeb.Community.myPost.PostRepository;
import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import com.ll.exam.oasisVeganingWeb.user.UserRepository;
import com.ll.exam.oasisVeganingWeb.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommentRepositoryTests {
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserService userService;

  @BeforeEach
  void beforeEach() {
    clearData();
    createSampleData();
  }

  public static void clearData(UserRepository userRepository, CommentRepository commentRepository, PostRepository postRepository) {
    UserServiceTests.clearData(userRepository, commentRepository, postRepository);
  }

  private void clearData() {
    clearData(userRepository, commentRepository, postRepository);
  }

  private void createSampleData() {
    PostRepositoryTests.createSampleData(userService, postRepository);

    MyPost p = postRepository.findById(1L).get();

    Comment m1 = new Comment();
    m1.setContent("맛있을 것 같아요!");
    m1.setAuthor(new SiteUser(1L));
    m1.setCreateDate(LocalDateTime.now());
    p.addComment(m1);

    Comment m2 = new Comment();
    m2.setContent("저도 한 번 해봐야겠어요!");
    m2.setAuthor(new SiteUser(2L));
    m2.setCreateDate(LocalDateTime.now());
    p.addComment(m2);

    postRepository.save(p);
  }

  @Test
  @Transactional
  @Rollback(false)
  void 저장() {
    MyPost p = postRepository.findById(2L).get();

    Comment m = new Comment();
    m.setContent("좋아요 누르고 갑니다~");
    m.setMy_post(p);
    m.setCreateDate(LocalDateTime.now());
    commentRepository.save(m);
  }

//  @Test
//  @Transactional
//  @Rollback(false)
//  void 조회() {
//    Comment m = commentRepository.findById(1L).get();
//    assertThat(m.getContent()).isEqualTo("맛있을 것 같아요!");
//  }

  @Test
  @Transactional
  @Rollback(false)
  void question으로부터_관련된_질문들_조회() {
    MyPost p = postRepository.findById(1L).get();

    List<Comment> commentList = p.getCommentList();
    assertThat(commentList.size()).isEqualTo(2);
    assertThat(commentList.get(0).getContent()).isEqualTo("맛있을 것 같아요!");
  }
}