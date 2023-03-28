 package br.com.zupedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.zupedu.cdd.Resources;
import br.com.zupedu.cdd.shared.UserDefinitionForTesting;
import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class MethodProcessorTest {
    @Test
    @DisplayName("Counting all methods")
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("UploadFileService.java"));

        StoreMetrics context = new StoreMetrics();
        MethodProcessor processor = new MethodProcessor(UserDefinitionForTesting.load(), context);

        l.addProcessor(processor);
        l.run();

        assertEquals(3, processor.total());
    }

    @Test
    @DisplayName("Counting all methods, except equals and hashcode")
    public void testName2() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("AlunoSimples.java"));

        StoreMetrics context = new StoreMetrics();
        MethodProcessor processor = new MethodProcessor(UserDefinitionForTesting.load(), context);

        l.addProcessor(processor);
        l.run();

        assertEquals(5, processor.total());
    }
}