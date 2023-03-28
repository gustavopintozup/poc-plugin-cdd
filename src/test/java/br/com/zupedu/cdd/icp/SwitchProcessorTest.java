package br.com.zupedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.zupedu.cdd.Resources;
import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class SwitchProcessorTest {
    @Test
    @DisplayName("Counting all ocurrences of cases in a switch")
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("SwitchSimples.java"));

        StoreMetrics context = new StoreMetrics();
        SwitchProcessor processor = new SwitchProcessor(context);

        l.addProcessor(processor);
        l.run();

        assertEquals(4, processor.total());
    }
}