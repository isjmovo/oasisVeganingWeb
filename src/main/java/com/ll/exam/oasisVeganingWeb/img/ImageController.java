package com.ll.exam.oasisVeganingWeb.img;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
  public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
    if (!file.isEmpty()) {
      try {
        // 이미지 저장 서비스 호출
        imageService.saveImage(file);

        // OCR 수행
        String extractedText = imageService.performOCR(file);

        // 모델에 결과 추가
        model.addAttribute("message", "이미지 업로드 및 OCR 성공");
        model.addAttribute("extractedText", extractedText);
      } catch (Exception e) {
        model.addAttribute("message", "이미지 업로드 또는 OCR 실패: " + e.getMessage());
      }
    } else {
      model.addAttribute("message", "이미지를 선택하세요.");
    }

    // 뷰 이름 반환
    return "check_img";
  }
}