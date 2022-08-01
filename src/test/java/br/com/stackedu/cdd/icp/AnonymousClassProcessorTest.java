package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.StoreMetrics;
import spoon.Launcher;

public class AnonymousClassProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(new Resources().findFile("ValidaConteudoDasAjudas.java"));

        StoreMetrics context = new StoreMetrics();
        AnonymousClassProcessor processor = new AnonymousClassProcessor(context);

        l.addProcessor(processor);
        l.run();

        assertEquals(0, processor.total());
    }
}