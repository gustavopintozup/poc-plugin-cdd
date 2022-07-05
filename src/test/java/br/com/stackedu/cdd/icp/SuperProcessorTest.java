package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import spoon.Launcher;

public class SuperProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/admin/partials/validaEstruturaTaskClassVisitor/ValidaConteudoDasAjudas.java");

        SuperProcessor ifprocessor = new SuperProcessor();

        l.addProcessor(ifprocessor);
        l.run();

        assertEquals(5, ifprocessor.total());
    }
}