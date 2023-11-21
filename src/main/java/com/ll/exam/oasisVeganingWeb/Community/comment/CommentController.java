package com.ll.exam.oasisVeganingWeb.Community.comment;

import com.ll.exam.oasisVeganingWeb.Community.myPost.MyPost;
import com.ll.exam.oasisVeganingWeb.Community.myPost.PostService;
import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import com.ll.exam.oasisVeganingWeb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/comment")
@Controller
@RequiredArgsConstructor
public class CommentController {
  private final PostService postService;
  private final CommentService commentService;
  private final UserService userService;

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/create/{id}")
  public String createComment(Principal principal, Model model, @PathVariable("id") long id, @Valid CommentForm commentForm, BindingResult bindingResult) {
    MyPost myPost = postService.getMyPost(id);

    if (bindingResult.hasErrors()) {
      model.addAttribute("myPost", myPost);

      return "post_detail";
    }

    SiteUser siteUser = userService.getUser(principal.getName());

    commentService.create(myPost, commentForm.getContent(), siteUser);

    return "redirect:/community/detail/%d".formatted(id);
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/modify/{id}")
  public String commentModify(CommentForm commentForm, @PathVariable("id") Long id, Principal principal) {
    Comment comment = commentService.getComment(id);

    if (!comment.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
    }

    commentForm.setContent(comment.getContent());

    return "comment_form";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/modify/{id}")
  public String commentModify(@Valid CommentForm commentForm, BindingResult bindingResult, @PathVariable("id") Long id, Principal principal) {
    if (bindingResult.hasErrors()) {
      return "comment_form";
    }

    Comment comment = commentService.getComment(id);

    if (!comment.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
    }

    commentService.modify(comment, commentForm.getContent());

    return "redirect:/community/detail/%d".formatted(comment.getMy_post().getId());
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/delete/{id}")
  public String commentDelete(Principal principal, @PathVariable("id") Long id) {
    Comment comment = commentService.getComment(id);

    if (!comment.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
    }

    commentService.delete(comment);

    return "redirect:/community/detail/%d".formatted(comment.getMy_post().getId());
  }
}