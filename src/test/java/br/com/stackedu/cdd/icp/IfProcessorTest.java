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
    @DisplayName("One if with one negation")
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("IfComUmaNegacao.java"));

        StoreMetrics context = new StoreMetrics();
        IfProcessor ifprocessor = new IfProcessor(UserDefinitionForTesting.load(), context);

        l.addProcessor(ifprocessor);
        l.run();

        assertEquals(1, ifprocessor.total());
    }

    @Test
    @DisplayName("One if, two else if, and one else")
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
    @DisplayName("One if with one condition")
    public void testName3() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("IfComUmaCondicao.java"));

        StoreMetrics context = new StoreMetrics();
        IfProcessor ifprocessor = new IfProcessor(UserDefinitionForTesting.load(), context);

        l.addProcessor(ifprocessor);
        l.run();

        assertEquals(1, ifprocessor.total());
    }
}