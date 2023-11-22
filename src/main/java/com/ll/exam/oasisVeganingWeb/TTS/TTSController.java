package com.ll.exam.oasisVeganingWeb.TTS;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TTSController {

  private final TTSService ttsService;

  public TTSController(TTSService ttsService) {
    this.ttsService = ttsService;
  }

  @GetMapping("/tts")
  public ResponseEntity<byte[]> generateTTS(@RequestParam("text") String text) {
    byte[] audioData = ttsService.convertTextToSpeech(text);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentDispositionFormData("attachment", "audio.wav");
    return new ResponseEntity<>(audioData, headers, HttpStatus.OK);
  }
}