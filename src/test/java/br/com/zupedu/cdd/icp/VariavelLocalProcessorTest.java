package br.com.zupedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.zupedu.cdd.Resources;
import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class VariavelLocalProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("AlunoSimples.java"));

        StoreMetrics context = new StoreMetrics();
        LocalVarProcessor processor = new LocalVarProcessor(context);

        l.addProcessor(processor);
        l.run();

        assertEquals(3, processor.total());
    }
}