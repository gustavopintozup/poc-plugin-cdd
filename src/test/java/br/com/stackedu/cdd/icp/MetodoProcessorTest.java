package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Resources;
import spoon.Launcher;

public class MetodoProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().buscaArquivo("Aluno.java"));

        MetodoProcessor processor = new MetodoProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(11, processor.total());
    }

    @Test
    public void testName2() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().buscaArquivo("AlunoSimples.java"));

        MetodoProcessor processor = new MetodoProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(5, processor.total());
    }
}