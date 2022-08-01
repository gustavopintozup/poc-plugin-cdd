package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.ArmazenarMetricas;
import br.com.stackedu.cdd.Resources;
import spoon.Launcher;

public class ForProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().buscaArquivo("ListaValidacoesEstrutura.java"));

        ArmazenarMetricas context = new ArmazenarMetricas();
        ForProcessor processor = new ForProcessor(context);

        l.addProcessor(processor);
        l.run();

        assertEquals(0, processor.total());
    }
}