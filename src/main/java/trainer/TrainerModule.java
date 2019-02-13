package trainer;

import com.google.inject.AbstractModule;


public class TrainerModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Trainer.class).asEagerSingleton();
  }
}
