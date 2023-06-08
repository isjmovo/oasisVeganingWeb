package com.ll.exam.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
  @GetMapping("/main")
  @ResponseBody
  public String index() {
    return "안녕하세요. UserService입니다.";
  }
}