package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.config.DefaultUserDefinitionFactory;
import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class CondicionalProcessorTest {
    @Test
    public void testName1() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                new Resources().findFile("IfComUmaCondicao.java"));

        StoreMetrics context = new StoreMetrics();
        CondicionalProcessor processor = new CondicionalProcessor(DefaultUserDefinitionFactory.load("cdd.json"), context);

        l.addProcessor(processor);
        l.run();

        assertEquals(1, processor.total());
    }

    @Test
    public void testName2() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                new Resources().findFile("IfComDuasCondicoes.java"));

        StoreMetrics context = new StoreMetrics();
        CondicionalProcessor processor = new CondicionalProcessor(DefaultUserDefinitionFactory.load("cdd.json"), context);

        l.addProcessor(processor);
        l.run();

        assertEquals(2, processor.total());
    }

    @Test
    public void testName3() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                new Resources().findFile("IfComTresCondicoes.java"));

        StoreMetrics context = new StoreMetrics();
        CondicionalProcessor processor = new CondicionalProcessor(DefaultUserDefinitionFactory.load("cdd.json"), context);

        l.addProcessor(processor);
        l.run();

        assertEquals(3, processor.total());
    }

    @Test
    public void testName4() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                new Resources().findFile("IfComUmaNegacao.java"));

        StoreMetrics context = new StoreMetrics();
        CondicionalProcessor processor = new CondicionalProcessor(DefaultUserDefinitionFactory.load("cdd.json"), context);

        l.addProcessor(processor);
        l.run();

        assertEquals(1, processor.total());
    }
}