package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import spoon.Launcher;

public class MetodoProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/alunos/Aluno.java");

        MetodoProcessor processor = new MetodoProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(11, processor.total());
    }
}