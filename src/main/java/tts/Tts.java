package tts;

public interface Tts {
  void textToSpeech(String speech);
  void shutdown();
}
