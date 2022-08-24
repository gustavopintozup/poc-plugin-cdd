package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

@Disabled
public class YieldProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("SwitchSimples.java"));

        StoreMetrics context = new StoreMetrics();
        YieldProcessor processor = new YieldProcessor(context);

        l.addProcessor(processor);
        l.run();

        assertEquals(2, processor.total());
    }
}