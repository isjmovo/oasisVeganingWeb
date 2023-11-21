package com.ll.exam.oasisVeganingWeb.user;

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

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
  @Autowired
  private final UserService userService;
  private final UserRepository userRepository;

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
      bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");

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
}