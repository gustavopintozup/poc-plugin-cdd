package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class LambdaProcessorTest {
    @Test
    @DisplayName("Counting all lambdas occurrences")
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("Lambdas.java"));

        StoreMetrics context = new StoreMetrics();
        LambdaProcessor processor = new LambdaProcessor(context);

        l.addProcessor(processor);
        l.run();

        assertEquals(1, processor.total());
    }
}