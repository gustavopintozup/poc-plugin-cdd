package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import spoon.Launcher;

public class ClasseAnonimaProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource("");

        ClasseAnonimaProcessor processor = new ClasseAnonimaProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(1, processor.total());
    }
}