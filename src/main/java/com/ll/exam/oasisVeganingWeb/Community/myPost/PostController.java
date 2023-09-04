package com.ll.exam.oasisVeganingWeb.Community.myPost;

import com.ll.exam.oasisVeganingWeb.Community.comment.CommentForm;
import com.ll.exam.oasisVeganingWeb.exception.DataNotFoundException;
import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import com.ll.exam.oasisVeganingWeb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/community")
@Controller
@RequiredArgsConstructor
public class PostController {
  private final PostService postService;
  private final UserService userService;

  @GetMapping("/list")
  public String list(Model model, @RequestParam(defaultValue = "0") int page) {
    Page<MyPost> paging = postService.getList(page);

    model.addAttribute("paging", paging);

    return "post_list";
  }

  @GetMapping("/detail/{id}")
  public String detail(Model model, @PathVariable long id, CommentForm commentForm) {
    MyPost myPost = postService.getMyPost(id);

    model.addAttribute("myPost", myPost);

    return "post_detail";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/modify/{id}")
  public String postModify(PostForm postForm, @PathVariable("id") Integer id, Principal principal) {
    MyPost myPost = this.postService.getMyPost(id);

    postForm.setSubject(myPost.getSubject());
    postForm.setContent(myPost.getContent());

    return "post_form";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/modify/{id}")
  public String postModify(@Valid PostForm postForm, BindingResult bindingResult, Principal principal, @PathVariable("id") Integer id) {
    if (bindingResult.hasErrors()) {
      return "post_form";
    }

    MyPost myPost = this.postService.getMyPost(id);

    if (!myPost.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
    }

    postService.modify(myPost, postForm.getSubject(), postForm.getContent());

    return String.format("redirect:/community/detail/%s", id);
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/delete/{id}")
  public String postDelete(Principal principal, @PathVariable("id") Integer id) {
    MyPost myPost = postService.getMyPost(id);

    if (!myPost.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
    }

    postService.delete(myPost);

    return "redirect:/community/list";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/create")
  public String postCreate(PostForm postForm) {
    return "post_form";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/create")
  public String postCreate(Principal principal, Model model, @Valid PostForm postForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "post_form";
    }

    SiteUser siteUser = userService.getUser(principal.getName());

    postService.create(postForm.getSubject(), postForm.getContent(), siteUser);

    return "redirect:/community/list";
  }
}