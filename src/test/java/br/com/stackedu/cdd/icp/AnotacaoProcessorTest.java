package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.stackedu.cdd.Resources;
import spoon.Launcher;

public class AnotacaoProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                new Resources().buscaArquivo("Aluno.java"));

        AnotacaoProcessor processor = new AnotacaoProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(28, processor.total());
    }

    @Test
    public void testName2() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().buscaArquivo("seguranca"));

        AnotacaoProcessor processor = new AnotacaoProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(11, processor.total());
    }
}