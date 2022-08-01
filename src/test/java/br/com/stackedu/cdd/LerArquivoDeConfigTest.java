package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.config.RegraSuportada;
import br.com.stackedu.cdd.shared.UserDefinitionForTesting;

public class LerArquivoDeConfigTest {

    @Test
    @DisplayName("Faz a leitura do arquivo json com configurações esperadas")
    public void testName() throws Exception {
    	Configuracoes testConfig = UserDefinitionForTesting.load();
        assertEquals(testConfig.limite(), 10);

        assertEquals(testConfig.get(RegraSuportada.IF_STATEMENT).getName(), "IF_STATEMENT");
        assertEquals(testConfig.get(RegraSuportada.IF_STATEMENT).getCost(), "1");

        assertEquals(testConfig.get(RegraSuportada.TRY_CATCH_STATEMENT).getName(), "TRY_CATCH_STATEMENT");
        assertEquals(testConfig.get(RegraSuportada.TRY_CATCH_STATEMENT).getCost(), "1");

        assertEquals(testConfig.get(RegraSuportada.SWITCH_STATEMENT).getName(), "SWITCH_STATEMENT");
        assertEquals(testConfig.get(RegraSuportada.SWITCH_STATEMENT).getCost(), "1");

        assertEquals(testConfig.get(RegraSuportada.CONTEXT_COUPLING).getName(), "CONTEXT_COUPLING");
        assertEquals(testConfig.get(RegraSuportada.CONTEXT_COUPLING).getCost(), "1");
        assertEquals(testConfig.get(RegraSuportada.CONTEXT_COUPLING).getParameters(), "br.com.zup.lms");
    }

    @Test
    @DisplayName("Verifica se uma configuração não foi passada para o cdd.json")
    public void testName2() throws Exception {

    	Configuracoes testConfig = UserDefinitionForTesting.load();
        Exception exception = assertThrows(PluginCDDException.class, () -> {
        	testConfig.get(RegraSuportada.METHODS_AUTOGEN);
        });

        assertEquals(exception.getMessage(), "A regra METHODS_AUTOGEN não está sendo utilizada!");
    }
}
