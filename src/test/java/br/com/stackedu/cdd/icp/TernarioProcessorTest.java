package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.ArmazenarMetricas;
import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.shared.UserDefinitionForTesting;
import spoon.Launcher;

public class TernarioProcessorTest {
    @Test
    @DisplayName("Teste com dois operadores ternarios em métodos diferentes")
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(new Resources().buscaArquivo("Ternario.java"));

        ArmazenarMetricas context = new ArmazenarMetricas();
        TernarioProcessor processor = new TernarioProcessor(UserDefinitionForTesting.load(), context);

        l.addProcessor(processor);
        l.run();

        assertEquals(2, processor.total());
    }

    @Test
    @DisplayName("Teste com dois operadores ternarios no mesmo método")
    public void testName2() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(new Resources().buscaArquivo("Ternario2.java"));

        ArmazenarMetricas context = new ArmazenarMetricas();
        TernarioProcessor processor = new TernarioProcessor(UserDefinitionForTesting.load(), context);

        l.addProcessor(processor);
        l.run();

        assertEquals(2, processor.total());
    }
}