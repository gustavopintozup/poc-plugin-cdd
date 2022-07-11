package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.stackedu.cdd.Resources;
import spoon.Launcher;

public class CatchProcessorTest {
  @Test
  public void testName() throws Exception {
    Launcher l = new Launcher();
    l.getEnvironment().setNoClasspath(true);

    l.addInputResource(new Resources().buscaArquivo("ServicoNotas.java"));

    CatchProcessor processor = new CatchProcessor();

    l.addProcessor(processor);
    l.run();

    assertEquals(1, processor.total());
  }
}