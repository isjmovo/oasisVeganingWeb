package com.ll.exam.oasisVeganingWeb.ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/check")
public class OcrController {

  @GetMapping("/perform-ocr")
  public String showOcrForm(Model model) {
    model.addAttribute("resultText", ""); // 초기 로드 시 resultText를 비움
    return "ocr_form"; // Thymeleaf 템플릿 파일 이름
  }

  @PostMapping("/perform-ocr")
  public String performOcr(@RequestParam("file") MultipartFile file, Model model) {
    String resultText = "";

    // 파일 정보 출력 (디버깅 목적)
    System.out.println("Original File Name: " + file.getOriginalFilename());
    System.out.println("File Size: " + file.getSize() + " bytes");
    System.out.println("Content Type: " + file.getContentType());

    if (!file.isEmpty()) {
      try {
        File imageFile = convertMultipartFileToFile(file);

        if (imageFile.exists() && isImageFile(imageFile)) {
          Tesseract tesseract = new Tesseract();

          BufferedImage bufferedImage = ImageIO.read(imageFile);
          resultText = tesseract.doOCR(bufferedImage);

          imageFile.delete();
        } else {
          resultText = "올바른 이미지 파일이 아닙니다.";
        }
      } catch (IOException | TesseractException e) {
        e.printStackTrace();
        resultText = "OCR 처리 중 오류가 발생했습니다.";
      }
    }

    model.addAttribute("resultText", resultText);
    return "ocr_form"; // Thymeleaf 템플릿 파일 이름
  }

  private boolean isImageFile(File file) {
    try {
      BufferedImage bufferedImage = ImageIO.read(file);
      return bufferedImage != null;
    } catch (IOException e) {
      return false;
    }
  }

  private File convertMultipartFileToFile(MultipartFile file) throws IOException {
    File convertedFile = new File(file.getOriginalFilename());
    file.transferTo(convertedFile);
    return convertedFile;
  }
}