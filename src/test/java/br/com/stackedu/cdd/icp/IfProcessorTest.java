package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.StoreMetrics;
import br.com.stackedu.cdd.shared.UserDefinitionForTesting;
import spoon.Launcher;

public class IfProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("ValidaConteudoDasAjudas.java"));

        StoreMetrics context = new StoreMetrics();
        IfProcessor ifprocessor = new IfProcessor(UserDefinitionForTesting.load(), context);

        l.addProcessor(ifprocessor);
        l.run();

        assertEquals(5, ifprocessor.total());
    }

    @Test
    @DisplayName("Checking several chained ifs")
    public void testName2() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("IfsEncadeados.java"));

        StoreMetrics context = new StoreMetrics();
        IfProcessor ifprocessor = new IfProcessor(UserDefinitionForTesting.load(), context);

        l.addProcessor(ifprocessor);
        l.run();

        assertEquals(6, ifprocessor.total());
    }

    @Test
    @DisplayName("Checking if inside an equals()")
    public void testName3() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("Ajuda.java"));

        StoreMetrics context = new StoreMetrics();
        IfProcessor ifprocessor = new IfProcessor(UserDefinitionForTesting.load(), context);

        l.addProcessor(ifprocessor);
        l.run();

        assertEquals(0, ifprocessor.total());
    }
}