package com.ll.exam.oasisVeganingWeb.Community.comment;

import com.ll.exam.oasisVeganingWeb.Community.myPost.MyPost;
import com.ll.exam.oasisVeganingWeb.exception.DataNotFoundException;
import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;

  public void create(MyPost myPost, String content, SiteUser author) {
    Comment comment = new Comment();
    comment.setContent(content);
    comment.setCreateDate(LocalDateTime.now());
    comment.setAuthor(author);
    myPost.addComment(comment);
    commentRepository.save(comment);
  }

  public Comment getComment(Long id) {
    return commentRepository.findById(id).orElseThrow(() -> new DataNotFoundException("comment not found"));
  }

  public void modify(Comment comment, String content) {
    comment.setContent(content);
    comment.setModifyDate(LocalDateTime.now());
    commentRepository.save(comment);
  }

  public void delete(Comment comment) {
    commentRepository.delete(comment);
  }
}