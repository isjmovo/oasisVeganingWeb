package com.ll.exam.oasisVeganingWeb.ocr;

import com.ll.exam.oasisVeganingWeb.img.ImageService;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/ocr")
public class OcrController {

  @Autowired
  private ImageService imageService;

  @PostMapping("/upload")
  public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file) {
    ModelAndView modelAndView = new ModelAndView("result");

    if (!file.isEmpty()) {
      try {
        String fileName = imageService.uploadImage(file);
        File imageFile = new File(fileName);

        String extractedText = performOCR(imageFile);
        modelAndView.addObject("result", extractedText);
      } catch (IOException e) {
        modelAndView.addObject("error", "Error processing the uploaded file.");
        e.printStackTrace();
      }
    } else {
      modelAndView.addObject("error", "Please upload a file.");
    }

    return modelAndView;
  }

  private String performOCR(File imageFile) {
    ITesseract tesseract = new Tesseract();
    tesseract.setDatapath("path/to/tessdata"); // Tesseract의 데이터 경로 지정

    try {
      return tesseract.doOCR(imageFile);
    } catch (Exception e) {
      e.printStackTrace();
      return "Error extracting text from image.";
    }
  }
}