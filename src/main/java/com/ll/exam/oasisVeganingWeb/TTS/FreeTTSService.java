package com.ll.exam.oasisVeganingWeb.TTS;

import com.sun.speech.freetts.VoiceManager;
import org.springframework.stereotype.Service;

@Service
public class FreeTTSService implements TTSService {

  @Override
  public byte[] convertTextToSpeech(String text) {
    VoiceManager voiceManager = VoiceManager.getInstance();
    Voice voice = voiceManager.getVoice("kevin16");
    voice.allocate();

    // Text to Speech
    return voice.speak(text);
  }
}