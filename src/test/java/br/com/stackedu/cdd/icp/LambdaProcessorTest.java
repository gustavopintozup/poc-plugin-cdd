package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import spoon.Launcher;

public class LambdaProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/alunos/CacheRespostasDeDeterminadoAluno.java");

        LambdaProcessor processor = new LambdaProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(3, processor.total());
    }
}