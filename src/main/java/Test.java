import buildorder.BuildOrder;
import buildorder.BuildOrderFileReader;
import buildorder.Event;
import trainer.Trainer;
import trainer.TrainerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import tts.SpeechModule;

import java.io.File;
import java.io.FileNotFoundException;

public class Test
{

  public static void main(String[] args) throws FileNotFoundException {
    Injector injector = Guice.createInjector(
        new SpeechModule()
    , new TrainerModule());

    BuildOrder twoOneOne = BuildOrderFileReader.readBuildOrderFile(new File("/Users/chris/sc2trainer/src/main/resources/PvX/easyBuild"));
    Trainer trainer = injector.getInstance(Trainer.class);
    trainer.trainBuildOrder(twoOneOne);

  }

} 