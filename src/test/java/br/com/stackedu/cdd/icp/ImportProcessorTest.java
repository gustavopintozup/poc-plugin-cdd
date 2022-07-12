package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.stackedu.cdd.Resources;
import spoon.Launcher;

public class ImportProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.getEnvironment().setAutoImports(true);
        l.addInputResource(
                new Resources().buscaArquivo("Treinamento.java"));


        ImportProcessor processor = new ImportProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(5, processor.total());
    }
}