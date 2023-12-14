package com.ll.exam.oasisVeganingWeb.Community.myPost;

import com.ll.exam.oasisVeganingWeb.exception.DataNotFoundException;
import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import com.ll.exam.oasisVeganingWeb.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;
  private final UserRepository userRepository;

//  @Autowired
//  private AmazonS3 amazonS3; // 이미지를 S3에 업로드하기 위한 클라이언트

  public Page<MyPost> getList(int page) {
    List<Sort.Order> sorts = new ArrayList<>();
    sorts.add(Sort.Order.desc("createDate"));

    Pageable pageable = PageRequest.of(page, 9, Sort.by(sorts));

    return postRepository.findAll(pageable);
  }

  public MyPost getMyPost(long id) {
    return postRepository.findById(id).orElseThrow(() -> new DataNotFoundException("no %d my_post not found".formatted(id)));
  }

  public void create(String subject, String content, SiteUser author) throws IOException {
    MyPost p = new MyPost();
    p.setSubject(subject);
    p.setContent(content);
    p.setAuthor(author);
    p.setCreateDate(LocalDateTime.now());
    postRepository.save(p);
  }

  public void modify(MyPost myPost, String subject, String content) {
    myPost.setSubject(subject);
    myPost.setContent(content);
    myPost.setModifyDate(LocalDateTime.now());
    postRepository.save(myPost);
  }

  public void delete (MyPost myPost) {
    this.postRepository.delete(myPost);
  }

  public List<MyPost> getMyPostsByUsername(String username) {
    SiteUser user = userRepository.findByUsername(username)
        .orElseThrow(() -> new DataNotFoundException("사용자를 찾을 수 없습니다."));

    return user.getMyPosts();
  }
}