package trainer;

import buildorder.BuildOrder;
import buildorder.Event;
import com.google.inject.Inject;
import tts.Tts;

public class Trainer {
  @Inject
  public Tts tts;
  public Trainer(){}
  public long startTime, elapsedTime;
  public void trainBuildOrder(BuildOrder buildOrder){
    startTime = System.currentTimeMillis();
    tts.textToSpeech(buildOrder.getBuildOrderName());
    buildOrder.getEventList().forEach(this::processEvent);
    tts.shutdown();
  }

  private void processEvent(Event event){
    elapsedTime = (System.currentTimeMillis() - startTime);
    while (! (elapsedTime > (event.getTime()))){
      elapsedTime = (System.currentTimeMillis() - startTime);
      sleep(100);
    }
    tts.textToSpeech(event.getMessage());
  }

  public static void sleep(long millis){
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
