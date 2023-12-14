package com.ll.exam.oasisVeganingWeb.img;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

//@RequiredArgsConstructor
@RequestMapping("/img")
@Controller
public class ImageController {
  @Autowired
  ImageService imageService;

//  @GetMapping("/upload")
//  public String showUploadForm() {
//    return "check_img";
//  }

//  @PostMapping("/upload")
//  public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file) {
//    ModelAndView modelAndView = new ModelAndView("check_img");
//
//    if (!file.isEmpty()) {
//      try {
//        imageService.saveImage(file); // 이미지 저장 서비스 호출
//
//        String extractedText = performOCR(file); // OCR 수행
//        modelAndView.addObject("message", "이미지 업로드 및 OCR 성공");
//        modelAndView.addObject("extractedText", extractedText);
//      } catch (Exception e) {
//        modelAndView.addObject("message", "이미지 업로드 또는 OCR 실패: " + e.getMessage());
//      }
//    } else {
//      modelAndView.addObject("message", "이미지를 선택하세요.");
//    }
//
//    return modelAndView;
//  }

  private String performOCR(MultipartFile file) {
    ITesseract tesseract = new Tesseract();
    tesseract.setDatapath("path/to/tessdata"); // Tesseract의 데이터 경로 지정

    try {
      File imageFile = File.createTempFile("temp", null);
      file.transferTo(imageFile);

      return tesseract.doOCR(imageFile);
    } catch (Exception e) {
      e.printStackTrace();
      return "Error extracting text from image.";
    }
  }

  @GetMapping("/upload")
  public String showUploadForm() {
    return "check_img";
  }

  @PostMapping("/upload")
  public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
    ModelAndView modelAndView = new ModelAndView("check_img");

    if (!file.isEmpty()) {
      try {
        String extractedText = imageService.performOCR(file); // OCR 수행
        modelAndView.addObject("message", "이미지 업로드 및 OCR 성공");
        modelAndView.addObject("extractedText", extractedText);
      } catch (Exception e) {
        modelAndView.addObject("message", "이미지 업로드 또는 OCR 실패: " + e.getMessage());
      }
    } else {
      modelAndView.addObject("message", "이미지를 선택하세요.");
    }

    return modelAndView;
  }
}