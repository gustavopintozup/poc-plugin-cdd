package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import spoon.Launcher;

public class WhileProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/admin/HeadingWrapper.java");

        WhileProcessor processor = new WhileProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(1, processor.total());
    }
}