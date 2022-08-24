package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class CatchProcessorTest {
  @Test
  @DisplayName("Simple try/catch")
  public void testName() throws Exception {
    Launcher l = new Launcher();
    l.getEnvironment().setNoClasspath(true);

    l.addInputResource(new Resources().findFile("TrySimples.java"));

    StoreMetrics context = new StoreMetrics();
    CatchProcessor processor = new CatchProcessor(context);

    l.addProcessor(processor);
    l.run();

    assertEquals(1, processor.total());
  }

  @Test
  @DisplayName("Try with 3 catches")
  public void testName3() throws Exception {
    Launcher l = new Launcher();
    l.getEnvironment().setNoClasspath(true);

    l.addInputResource(new Resources().findFile("TryComTresCatches.java"));

    StoreMetrics context = new StoreMetrics();
    CatchProcessor processor = new CatchProcessor(context);

    l.addProcessor(processor);
    l.run();

    assertEquals(3, processor.total());
  }


  @Test
  @DisplayName("Try without a catch")
  public void testName2() throws Exception {
    Launcher l = new Launcher();
    l.getEnvironment().setNoClasspath(true);

    l.addInputResource(new Resources().findFile("TryComFinally.java"));

    StoreMetrics context = new StoreMetrics();
    CatchProcessor processor = new CatchProcessor(context);

    l.addProcessor(processor);
    l.run();

    assertEquals(0, processor.total());
  }
}