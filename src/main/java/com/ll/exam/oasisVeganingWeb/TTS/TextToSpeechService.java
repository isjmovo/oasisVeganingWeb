//package com.ll.exam.oasisVeganingWeb.TTS;
//
//import okhttp3.*;
//
//public class TextToSpeechService {
//
//  private static final String KAKAO_API_KEY = "5f294046a1e5f9dcc61df24e096ce6ec";
//  private static final String KAKAO_API_URL = "https://kakaoi-newtone-openapi.kakao.com/v1/synthesize";
//
//  private final OkHttpClient client;
//
//  public TextToSpeechService() {
//    this.client = new OkHttpClient();
//  }
//
//  public void speak(String text) {
//    try {
//      MediaType mediaType = MediaType.parse("application/xml");
//      String requestBody = "<speak>" + text + "</speak>";
//
//      Request request = new Request.Builder()
//          .url(KAKAO_API_URL)
//          .addHeader("Content-Type", "application/xml")
//          .addHeader("Authorization", "KakaoAK " + KAKAO_API_KEY)
//          .post(RequestBody.create(requestBody, mediaType))
//          .build();
//
//      try (Response response = client.newCall(request).execute()) {
//        if (response.isSuccessful()) {
//          // Handle the TTS audio response (save to file, play, etc.)
//          byte[] audioData = response.body().bytes();
//          // Implement your logic to handle the audio data as needed.
//        } else {
//          // Handle error
//          System.out.println("Error: " + response.code() + " - " + response.message());
//        }
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//}