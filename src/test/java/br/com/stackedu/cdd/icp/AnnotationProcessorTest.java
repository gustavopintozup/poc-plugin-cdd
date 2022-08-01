package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.PrintMetrics;
import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.StoreMetrics;
import br.com.stackedu.cdd.config.DefaultUserDefinitionFactory;
import spoon.Launcher;

public class AnnotationProcessorTest {
        @Test
        public void testName() throws Exception {
                Launcher l = new Launcher();
                l.getEnvironment().setNoClasspath(true);
                l.addInputResource(
                                new Resources().findFile("Aluno.java"));

                StoreMetrics context = new StoreMetrics();
                AnnotationProcessor processor = new AnnotationProcessor(context);

                l.addProcessor(processor);
                l.run();

                assertEquals(28, processor.total());
        }

        @Test
        public void testName2() throws Exception {
                Launcher l = new Launcher();
                l.getEnvironment().setNoClasspath(true);
                l.addInputResource(
                                new Resources().findFile("seguranca"));

                StoreMetrics context = new StoreMetrics();
                AnnotationProcessor processor = new AnnotationProcessor(context);

                l.addProcessor(processor);
                l.run();

                assertEquals(11, processor.total());
        }

        @Test
        public void testName3() throws Exception {
                Launcher l = new Launcher();
                l.getEnvironment().setNoClasspath(true);
                l.addInputResource(
                                new Resources().findFile("Aluno.java"));
                l.addInputResource(
                                new Resources().findFile("Certificado.java"));
                l.addInputResource(
                                new Resources().findFile("Ajuda.java"));

                StoreMetrics context = new StoreMetrics();
                AnnotationProcessor processor = new AnnotationProcessor(context);

                l.addProcessor(processor);
                l.run();

                assertEquals("br.com.zup.lms.alunos.Certificado[ANNOTATION=30,ICP=30]\n" +
                                "br.com.zup.lms.admin.Ajuda[ANNOTATION=28,ICP=28]\n" +
                                "br.com.zup.lms.alunos.Aluno[ANNOTATION=28,ICP=28]\n",
                                new PrintMetrics(DefaultUserDefinitionFactory.load("cdd.json"), context).console());
        }
}