package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import spoon.Launcher;

public class ForEachProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/admin/partials/validaEstruturaTaskClassVisitor/ListaValidacoesEstrutura.java");

        ForEachProcessor processor = new ForEachProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(1, processor.total());
    }
}