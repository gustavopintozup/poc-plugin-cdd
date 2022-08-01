package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.ArmazenarMetricas;
import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.config.DefaultUserDefinitionFactory;
import spoon.Launcher;

public class CondicionalProcessorTest {
    @Test
    public void testName1() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                new Resources().buscaArquivo("IfComUmaCondicao.java"));

        ArmazenarMetricas context = new ArmazenarMetricas();
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
                new Resources().buscaArquivo("IfComDuasCondicoes.java"));

        ArmazenarMetricas context = new ArmazenarMetricas();
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
                new Resources().buscaArquivo("IfComTresCondicoes.java"));

        ArmazenarMetricas context = new ArmazenarMetricas();
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
                new Resources().buscaArquivo("IfComUmaNegacao.java"));

        ArmazenarMetricas context = new ArmazenarMetricas();
        CondicionalProcessor processor = new CondicionalProcessor(DefaultUserDefinitionFactory.load("cdd.json"), context);

        l.addProcessor(processor);
        l.run();

        assertEquals(1, processor.total());
    }


    @Test
    public void testName5() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                new Resources().buscaArquivo("AlunoPodeAcessarLearningTask.java"));

        ArmazenarMetricas context = new ArmazenarMetricas();
        CondicionalProcessor processor = new CondicionalProcessor(DefaultUserDefinitionFactory.load("cdd.json"), context);

        l.addProcessor(processor);
        l.run();

        assertEquals(1, processor.total());
    }
}