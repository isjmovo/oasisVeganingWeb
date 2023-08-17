package com.ll.exam.oasisVeganingWeb.lens;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lens")
public class LensController {
  @GetMapping
  public String lens() {
    return "smartLens";
  }
}