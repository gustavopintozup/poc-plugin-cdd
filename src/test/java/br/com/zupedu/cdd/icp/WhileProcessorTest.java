package br.com.zupedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.zupedu.cdd.Resources;
import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class WhileProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("LoopsSimples.java"));

        StoreMetrics context = new StoreMetrics();
        WhileProcessor p1 = new WhileProcessor(context);
        DoWhileProcessor p2 = new DoWhileProcessor(context);

        l.addProcessor(p1);
        l.addProcessor(p2);
        l.run();

        assertEquals(2, p1.total() + p2.total());
    }
}