package com.ll.exam.oasisVeganingWeb.img;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//@RequiredArgsConstructor
@RequestMapping("/img")
@Controller
public class ImageController {
  @Autowired
  ImageService imageService;

  @GetMapping("/upload")
  public String showUploadForm() {
    return "check_img";
  }

  @PostMapping("/upload")
  public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file) {
    ModelAndView modelAndView = new ModelAndView();

    if (!file.isEmpty()) {
      try {
        imageService.saveImage(file); // 이미지 저장 서비스 호출

        modelAndView.addObject("message", "이미지 업로드 성공");
      } catch (Exception e) {
        modelAndView.addObject("message", "이미지 업로드 실패: " + e.getMessage());
      }
    } else {
      modelAndView.addObject("message", "이미지를 선택하세요.");
    }

    modelAndView.setViewName("check_img");
    return modelAndView;
  }
}