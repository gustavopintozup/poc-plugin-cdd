package br.com.zupedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.zupedu.cdd.Miner.FormatOption.Format;
import br.com.zupedu.cdd.Resources;
import br.com.zupedu.cdd.printer.PrintMetrics;
import br.com.zupedu.cdd.shared.UserDefinitionForTesting;
import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class AnnotationProcessorTest {
        @Test
        @DisplayName("Couting all annotations from a class")
        public void testName() throws Exception {
                Launcher l = new Launcher();
                l.getEnvironment().setNoClasspath(true);
                l.addInputResource(
                                new Resources().findFile("Person.java"));

                StoreMetrics context = new StoreMetrics();
                AnnotationProcessor processor = new AnnotationProcessor(context);

                l.addProcessor(processor);
                l.run();

                assertEquals(7, processor.total());
        }

        @Test
        @DisplayName("Class with annotations above limit")
        public void testName2() throws Exception {
                Launcher l = new Launcher();
                l.getEnvironment().setNoClasspath(true);
                l.addInputResource(
                                new Resources().findFile("UploadFileService.java"));

                StoreMetrics context = new StoreMetrics();
                AnnotationProcessor processor = new AnnotationProcessor(context);

                l.addProcessor(processor);
                l.run();

                assertEquals(12, processor.total());

                assertEquals("com.mkyong.rest.UploadFileService[ANNOTATION=12,ICP=12]\n",
                                new PrintMetrics(UserDefinitionForTesting.load(), context).as(Format.TXT).print());
        }

        @Test
        @DisplayName("Class with annotations below the limit")
        public void testName3() throws Exception {
                Launcher l = new Launcher();
                l.getEnvironment().setNoClasspath(true);
                l.addInputResource(
                                new Resources().findFile("Person.java"));

                StoreMetrics context = new StoreMetrics();
                AnnotationProcessor processor = new AnnotationProcessor(context);

                l.addProcessor(processor);
                l.run();

                assertEquals(7, processor.total());
                assertEquals("", new PrintMetrics(UserDefinitionForTesting.load(), context).as(Format.TXT).print());
        }
}