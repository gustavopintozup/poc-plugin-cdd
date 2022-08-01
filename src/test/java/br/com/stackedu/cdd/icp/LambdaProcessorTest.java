package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.ArmazenarMetricas;
import br.com.stackedu.cdd.Resources;
import spoon.Launcher;

public class LambdaProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().buscaArquivo("CacheRespostasDeDeterminadoAluno.java"));

        ArmazenarMetricas context = new ArmazenarMetricas();
        LambdaProcessor processor = new LambdaProcessor(context);

        l.addProcessor(processor);
        l.run();

        assertEquals(3, processor.total());
    }
}