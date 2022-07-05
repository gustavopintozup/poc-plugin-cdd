package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import spoon.Launcher;

public class ThrowProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/admin/ValidadorTitulosAlteradosAoAtualizar.java");

        ThrowProcessor processor = new ThrowProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(3, processor.total());
    }
}