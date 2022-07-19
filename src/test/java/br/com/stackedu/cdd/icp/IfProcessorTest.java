package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Resources;
import spoon.Launcher;

public class IfProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().buscaArquivo("ValidaConteudoDasAjudas.java"));

        IfProcessor ifprocessor = new IfProcessor();

        l.addProcessor(ifprocessor);
        l.run();

        assertEquals(5, ifprocessor.total());
    }

    @Test
    @DisplayName("Testando vários ifs encadeados")
    public void testName2() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().buscaArquivo("IfsEncadeados.java"));

        IfProcessor ifprocessor = new IfProcessor();

        l.addProcessor(ifprocessor);
        l.run();

        assertEquals(6, ifprocessor.total());
    }
}