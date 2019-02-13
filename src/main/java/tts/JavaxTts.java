package tts;

import com.google.inject.Inject;

import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;


public class JavaxTts implements Tts {
  private Synthesizer synthesizer;
    @Inject
  public JavaxTts() throws EngineException {
    System.setProperty("freetts.voices",
        "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    Central.registerEngineCentral
        ("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
    synthesizer =
        Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
      synthesizer.allocate();

    }
  public void textToSpeech(String speech) {
    try
    {
      synthesizer.resume();
      synthesizer.speakPlainText(speech, null);
      synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void shutdown() {
    try {
      synthesizer.deallocate();
    } catch (EngineException e) {
      e.printStackTrace();
    }
  }
}
