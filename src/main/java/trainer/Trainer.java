package trainer;

import buildorder.BuildOrder;
import buildorder.BuildOrderFileReader;
import buildorder.Event;
import com.google.inject.Inject;
import io.KeyListener;
import tts.Tts;

import java.io.File;
import java.io.FileNotFoundException;

public class Trainer implements KeyListener {

  @Inject
  public Tts tts;

  boolean started = false;
  BuildOrder pvx;
  BuildOrder pvz;
  BuildOrder pvp;
  BuildOrder pvt;

  public void init() {
    try {
      pvx = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/PvX/easyBuild"));
      pvz = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/PvZ/oracleZealotAllIn"));
      pvp = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/PvP/blinkAllIn"));
      pvt = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/PvT/collossusPheonixVoidRay"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }


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

  @Override
  public void keyPress(String key) {
    System.out.println(key);
    if(!started){
      if( key.equals("X")){
        trainBuildOrder(pvx);
        started = true;
      }

      if( key.equals("T")){
        trainBuildOrder(pvt);
        started = true;
      }

      if( key.equals("P")){
        trainBuildOrder(pvp);
        started = true;
      }

      if( key.equals("Z")){
        trainBuildOrder(pvz);
        started = true;
      }
    }
  }
}
