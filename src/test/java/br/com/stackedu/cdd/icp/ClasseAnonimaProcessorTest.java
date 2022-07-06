package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import spoon.Launcher;

public class ClasseAnonimaProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource("/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/admin/partials/validaEstruturaTaskClassVisitor/ValidaConteudoDasAjudas.java");

        ClasseAnonimaProcessor processor = new ClasseAnonimaProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(0, processor.total());
    }
}