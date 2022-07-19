package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LerArquivoDeConfigTest {

    @Test
    public void testName() throws Exception {
        assertEquals(Configuracoes.limite(), 10);

        assertEquals(Configuracoes.regras().get(0).getName(), "IF_STATEMENT");
        assertEquals(Configuracoes.regras().get(0).getCost(), "1");

        assertEquals(Configuracoes.regras().get(1).getName(), "ANNOTATION");
        assertEquals(Configuracoes.regras().get(1).getCost(), "2");

        assertEquals(Configuracoes.regras().get(2).getName(), "FOR_STATEMENT");
        assertEquals(Configuracoes.regras().get(2).getCost(), "1");

        assertEquals(Configuracoes.regras().get(3).getName(), "CONTEXT_COUPLING");
        assertEquals(Configuracoes.regras().get(3).getCost(), "1");
        assertEquals(Configuracoes.regras().get(3).getParameters().get(0), "spring");
    }
}
