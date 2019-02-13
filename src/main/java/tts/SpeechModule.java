package tts;

import com.google.inject.AbstractModule;

public class SpeechModule extends AbstractModule {
  protected void configure() {
    bind(Tts.class).to(JavaxTts.class).asEagerSingleton();
  }
}
