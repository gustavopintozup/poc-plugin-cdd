package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.stackedu.cdd.Resources;
import spoon.Launcher;

public class LambdaProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().buscaArquivo("CacheRespostasDeDeterminadoAluno.java"));

        LambdaProcessor processor = new LambdaProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(3, processor.total());
    }
}