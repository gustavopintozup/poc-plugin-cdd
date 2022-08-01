package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.StoreMetrics;
import spoon.Launcher;

public class ThrowProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("ValidadorTitulosAlteradosAoAtualizar.java"));

        StoreMetrics context = new StoreMetrics();
        ThrowProcessor processor = new ThrowProcessor(context);

        l.addProcessor(processor);
        l.run();

        assertEquals(3, processor.total());
    }
}