package com.ll.exam.oasisVeganingWeb.img;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {
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
}