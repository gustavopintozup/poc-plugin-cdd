package br.com.stackedu.cdd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LerArquivoDeConfigTest {

    @Test
    public void testName() throws Exception {
        assertEquals(Configuracoes.limite(), 10);
        
        assertEquals(Configuracoes.regras().get(0).name, "IF_STATEMENT");
        assertEquals(Configuracoes.regras().get(0).cost, "1");

        assertEquals(Configuracoes.regras().get(1).name, "ANNOTATION");
        assertEquals(Configuracoes.regras().get(1).cost, "2");
        
        assertEquals(Configuracoes.regras().get(2).name, "FOR_STATEMENT");
        assertEquals(Configuracoes.regras().get(2).cost, "1");
        
        assertEquals(Configuracoes.regras().get(3).name, "CONTEXT_COUPLING");
        assertEquals(Configuracoes.regras().get(3).cost, "1");
        assertEquals(Configuracoes.regras().get(3).parameters.get(0), "spring");
    }
}
