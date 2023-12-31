package com.ll.exam.oasisVeganingWeb.img;

import com.ll.exam.oasisVeganingWeb.check.IngredientRepository;
import com.ll.exam.oasisVeganingWeb.check.NonVeganIngredientRepository;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ImageService {
  private IngredientRepository ingredientRepository;
  private NonVeganIngredientRepository nonVeganIngredientRepository;

  @Value("${upload.dir}") // application.properties에서 설정한 디렉토리 경로를 주입
  private String uploadDir;

  public void saveImage(MultipartFile file) throws IOException {
    if (!file.isEmpty()) {
      try {
        String fileName = file.getOriginalFilename();
        String filePath = uploadDir + File.separator + fileName;

        // 디렉토리가 없으면 생성
        File uploadDirectory = new File(uploadDir);
        if (!uploadDirectory.exists()) {
          uploadDirectory.mkdirs();
        }

        // 파일 저장
        File dest = new File(filePath);
        file.transferTo(dest);
      } catch (Exception e) {
        throw new RuntimeException("이미지 저장 실패: " + e.getMessage());
      }
    } else {
      throw new IllegalArgumentException("이미지를 선택하세요.");
    }
  }

  public String uploadImage(MultipartFile file) throws IOException {
    String fileName = System.currentTimeMillis() + Objects.requireNonNull(file.getOriginalFilename());
    Path destinationPath = Path.of(uploadDir, fileName);

    Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

    return fileName;
  }

  public String performOCR(MultipartFile file) {
    String csvData;

    try {
      ImageIO.scanForPlugins(); // 이미지 I/O 구성

      ITesseract tesseract = new Tesseract();
      tesseract.setDatapath("../../../resources/tessdata"); // Tesseract의 데이터 경로 지정
      tesseract.setLanguage("kor");

      File imageFile = File.createTempFile("temp", null);
      file.transferTo(imageFile);

      // OCR 수행 및 결과 반환
      return csvData = tesseract.doOCR(imageFile);
    } catch (Exception e) {
      e.printStackTrace();
      return csvData = "Error extracting text from image.";
    }
  }
}