package com.ll.exam.oasisVeganingWeb.green;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/green")
public class GreenController {
  @GetMapping
  public String recipe() {
    return "green_form";
  }
}