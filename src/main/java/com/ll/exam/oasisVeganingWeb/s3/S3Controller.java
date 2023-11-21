package com.ll.exam.oasisVeganingWeb.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/img")
public class S3Controller {
  @Autowired
  private final S3Upload s3Upload;

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/up")
  public String imgUp() {
    return "s3img_form";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/up")
  public String imgUp(@RequestParam(value ="data", required=false) MultipartFile multipartFile) throws IOException {
    s3Upload.upload(multipartFile, "oasisvegan");

    return "s3img_form";
  }
}
