package com.ll.exam.oasisVeganingWeb.ocr;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class OcrService {
  public String performOcr(MultipartFile imageFile) throws IOException {
    if (!imageFile.isEmpty()) {
      try {
        File image = File.createTempFile("temp", imageFile.getOriginalFilename());
        imageFile.transferTo(image);

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C://Program Files//Tesseract-OCR//tessdata"); // Tesseract 실행 파일의 경로 설정
        String text = tesseract.doOCR(image);

        return text;
      } catch (Exception e) {
        throw new RuntimeException("OCR 처리 실패: " + e.getMessage());
      }
    } else {
      throw new IllegalArgumentException("이미지를 선택하세요.");
    }
  }
}