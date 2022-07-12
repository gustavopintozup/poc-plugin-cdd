package br.com.stackedu.cdd.icp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.stackedu.cdd.Metricas;
import br.com.stackedu.cdd.Resources;
import spoon.Launcher;

public class AnotacaoProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                new Resources().buscaArquivo("Aluno.java"));

        AnotacaoProcessor processor = new AnotacaoProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(28, processor.total());
    }

    @Test
    public void testName2() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                new Resources().buscaArquivo("seguranca"));

        AnotacaoProcessor processor = new AnotacaoProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals(11, processor.total());
    }

    @Test
    public void testName3() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                new Resources().buscaArquivo("Aluno.java"));
        l.addInputResource(
                new Resources().buscaArquivo("Certificado.java"));
        l.addInputResource(
                new Resources().buscaArquivo("Ajuda.java"));

        AnotacaoProcessor processor = new AnotacaoProcessor();

        l.addProcessor(processor);
        l.run();

        assertEquals("br.com.zup.lms.alunos.Certificado.anotação=30\n" +
                "br.com.zup.lms.alunos.Aluno.anotação=28\n" +
                "br.com.zup.lms.admin.Ajuda.anotação=28",
                Metricas.prettyprint());
    }
}