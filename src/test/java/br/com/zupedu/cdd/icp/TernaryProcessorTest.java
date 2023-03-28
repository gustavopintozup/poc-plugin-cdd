 package br.com.zupedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.zupedu.cdd.Resources;
import br.com.zupedu.cdd.shared.UserDefinitionForTesting;
import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class TernaryProcessorTest {
    @Test
    @DisplayName("Checking two ternary operators in different methods")
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(new Resources().findFile("TernarioEmUmDoisMetodos.java"));

        StoreMetrics context = new StoreMetrics();
        TernaryProcessor processor = new TernaryProcessor(UserDefinitionForTesting.load(), context);

        l.addProcessor(processor);
        l.run();

        assertEquals(2, processor.total());
    }

    @Test
    @DisplayName("Checking two ternary operators within the same method")
    public void testName2() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(new Resources().findFile("TernarioEmUmUnicoMetodo.java"));

        StoreMetrics context = new StoreMetrics();
        TernaryProcessor processor = new TernaryProcessor(UserDefinitionForTesting.load(), context);

        l.addProcessor(processor);
        l.run();

        assertEquals(2, processor.total());
    }
}