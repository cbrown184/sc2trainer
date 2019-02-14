package trainer;

import com.google.inject.AbstractModule;
import io.GlobalKeyListener;
import io.KeyListener;


public class TrainerModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(KeyListener.class).to(Trainer.class).asEagerSingleton();
    bind(GlobalKeyListener.class).asEagerSingleton();
  }
}
