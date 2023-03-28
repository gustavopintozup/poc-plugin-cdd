package br.com.zupedu.cdd.icp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.zupedu.cdd.Resources;
import br.com.zupedu.cdd.shared.UserDefinitionForTesting;
import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class MethodProcessorSizeTest {
    @Test
    @DisplayName("Counting all methods")
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("UploadFileService.java"));

        StoreMetrics context = new StoreMetrics();
        MethodSizeProcessor processor = new MethodSizeProcessor(UserDefinitionForTesting.load(), context);

        l.addProcessor(processor);
        l.run();

        // assertEquals(3, processor.total());
    }
}