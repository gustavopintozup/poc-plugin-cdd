package br.com.stackedu.cdd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LerArquivoDeConfigTest {

    @Test
    public void testName() throws Exception {
        assertEquals(ArquivoDeConfig.limite(), 10);
        
        assertEquals(ArquivoDeConfig.regras().get(0).name, "IF_STATEMENT");
        assertEquals(ArquivoDeConfig.regras().get(0).cost, "1");

        assertEquals(ArquivoDeConfig.regras().get(1).name, "ANNOTATION");
        assertEquals(ArquivoDeConfig.regras().get(1).cost, "2");
        
        assertEquals(ArquivoDeConfig.regras().get(2).name, "FOR_STATEMENT");
        assertEquals(ArquivoDeConfig.regras().get(2).cost, "1");
        
        assertEquals(ArquivoDeConfig.regras().get(3).name, "CONTEXT_COUPLING");
        assertEquals(ArquivoDeConfig.regras().get(3).cost, "1");
        assertEquals(ArquivoDeConfig.regras().get(3).parameters.get(0), "spring");
    }
}
