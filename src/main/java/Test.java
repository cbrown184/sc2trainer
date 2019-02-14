import io.GlobalKeyListener;
import io.KeyListener;
import trainer.TrainerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import tts.SpeechModule;

import java.io.FileNotFoundException;

public class Test
{

  public static void main(String[] args) throws FileNotFoundException {
    Injector injector = Guice.createInjector(
        new SpeechModule()
    , new TrainerModule());

    injector.getInstance(GlobalKeyListener.class).init();
    injector.getInstance(KeyListener.class).init();

  }

}