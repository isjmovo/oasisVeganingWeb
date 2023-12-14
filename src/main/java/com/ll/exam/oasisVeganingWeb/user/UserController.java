package com.ll.exam.oasisVeganingWeb.user;

import com.ll.exam.oasisVeganingWeb.Community.myPost.MyPost;
import com.ll.exam.oasisVeganingWeb.Community.myPost.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
  @Autowired
  private final UserService userService;
  private final UserRepository userRepository;
  private final PostService postService;

  @GetMapping("/signup")
  public String signup(UserCreateForm userCreateForm) {
    return "signup_form";
  }

  @PostMapping("/signup")
  public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "signup_form";
    }

    if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
      bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다.");

      return "signup_form";
    }

    try {
      userService.create(userCreateForm.getName(), userCreateForm.getUsername(),
//          userCreateForm.getPassword1(), userCreateForm.getAllergy());
          userCreateForm.getPassword1());
    } catch (SignupUsernameDuplicatedException e) {
      bindingResult.reject("SignupUsernameDuplicated", e.getMessage());

      return "signup_form";
    }

//    return "test_form";
    return "redirect:/";
  }

  @GetMapping("/login")
  public String login() {
    return "login_form";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/mypage")
  public String myPage(Principal principal, Model model) {

    SiteUser user = userService.getUser(principal.getName());

    model.addAttribute("user", user);

    return "mypage";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/archive")
  public String archive(Principal principal, Model model, @RequestParam(defaultValue = "0") int page) {
    SiteUser user = userService.getUser(principal.getName());

    // 수정된 부분: 사용자가 작성한 글 가져와서 모델에 추가
    List<MyPost> userPosts = postService.getMyPostsByUsername(principal.getName());

    model.addAttribute("user", user);
    model.addAttribute("userPosts", userPosts);

    return "my_archive";
  }
}