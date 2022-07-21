package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.config.JSONParser.RegrasDefinidas;

public class LerArquivoDeConfigTest {

    @Test
    @DisplayName("Faz a leitura do arquivo json com configurações esperadas")
    public void testName() throws Exception {
        assertEquals(Configuracoes.limite(), 10);

        assertEquals(Configuracoes.get(RegrasDefinidas.IF_STATEMENT).getName(), "IF_STATEMENT");
        assertEquals(Configuracoes.get(RegrasDefinidas.IF_STATEMENT).getCost(), "1");

        assertEquals(Configuracoes.get(RegrasDefinidas.TRY_CATCH_STATEMENT).getName(), "TRY_CATCH_STATEMENT");
        assertEquals(Configuracoes.get(RegrasDefinidas.TRY_CATCH_STATEMENT).getCost(), "1");

        assertEquals(Configuracoes.get(RegrasDefinidas.SWITCH_STATEMENT).getName(), "SWITCH_STATEMENT");
        assertEquals(Configuracoes.get(RegrasDefinidas.SWITCH_STATEMENT).getCost(), "1");

        assertEquals(Configuracoes.get(RegrasDefinidas.CONTEXT_COUPLING).getName(), "CONTEXT_COUPLING");
        assertEquals(Configuracoes.get(RegrasDefinidas.CONTEXT_COUPLING).getCost(), "1");
        assertEquals(Configuracoes.get(RegrasDefinidas.CONTEXT_COUPLING).getParameters(), "br.com.zup.lms");
    }

    @Test
    @DisplayName("Verifica se uma configuração não foi passada para o cdd.json")
    public void testName2() throws Exception {

        Exception exception = assertThrows(PluginCDDException.class, () -> {
            Configuracoes.get(RegrasDefinidas.METHODS_AUTOGEN);
        });

        assertEquals(exception.getMessage(), "A regra METHODS_AUTOGEN não está sendo utilizada!");
    }
}
