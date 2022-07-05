package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import spoon.Launcher;

public class SwitchProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/admin/partials/validaEstruturaTaskClassVisitor/ValidaConteudoDasAjudas.java");

        SwitchProcessor processor = new SwitchProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(3, processor.total());
    }
}