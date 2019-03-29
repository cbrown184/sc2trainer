package trainer;

import buildorder.BuildOrder;
import buildorder.BuildOrderFileReader;
import buildorder.Event;
import com.google.inject.Inject;
import io.KeyListener;
import tts.Tts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Trainer implements KeyListener {

  @Inject
  public Tts tts;

  boolean started = false;
  // x = random, z = zerg, p = protoss, t = terran
  BuildOrder x;
  BuildOrder z;
  BuildOrder p;
  BuildOrder t;
  BuildOrder q;

  public void init() {
    try {
      x = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/PvX/easyBuild"));
      z = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/PvZ/oracleZealotAllIn"));
      p = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/PvP/AdeptAllIn"));
//      p = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/TVP"));
//      t = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/TvT"));
//      z = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/TvZ"));



      //t = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/PvT/collossusPheonixVoidRay"));
      t = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/PvT/collossusPheonixVoidRay"));
      q = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/PvX/zealotImmoArchon"));
      z = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/PvX/zealotImmoArchon"));
//      z = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/ZvZ/droneAllIn"));
//      p = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/ZvP/safeBroodRush"));
//      t = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/ZvT/economic"));

//      p = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/TVP"));
//      t = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/TvT"));
//      z = BuildOrderFileReader.readBuildOrderFile(new File("/Users/crez/Desktop/sc2trainer/src/main/resources/TvZ"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }



  }


  public long startTime, elapsedTime;

  public void trainBuildOrder(BuildOrder buildOrder){
    startTime = System.currentTimeMillis();
    tts.textToSpeech(buildOrder.getBuildOrderName());
    buildOrder.getEventList().forEach(this::processEvent);
    tts.textToSpeech("Build order complete. Free build.");
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
        trainBuildOrder(x);
        started = true;
      }

      if( key.equals("T")){
        trainBuildOrder(t);
        started = true;
      }

      if( key.equals("P")){
        trainBuildOrder(p);
        started = true;
      }

      if( key.equals("Z")){
        trainBuildOrder(z);
        started = true;
      }

      if( key.equals("Q")){
        trainBuildOrder(q);
        started = true;
      }
    }
  }
}
