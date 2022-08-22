package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.config.DefaultUserDefinitionFactory;
import br.com.stackedu.cdd.config.SupportedRules;
import br.com.stackedu.cdd.shared.UserDefinitionForTesting;

public class ReadConfigFileTest {

    @Test
    @DisplayName("Reading the cdd.json file with the expected configs")
    public void testName() throws Exception {
    	Config testConfig = UserDefinitionForTesting.load();
        assertEquals(testConfig.limit(), 10);

        assertEquals(testConfig.get(SupportedRules.IF_STATEMENT).getName(), "IF_STATEMENT");
        assertEquals(testConfig.get(SupportedRules.IF_STATEMENT).getCost(), "1");

        assertEquals(testConfig.get(SupportedRules.TRY_CATCH_STATEMENT).getName(), "TRY_CATCH_STATEMENT");
        assertEquals(testConfig.get(SupportedRules.TRY_CATCH_STATEMENT).getCost(), "1");

        assertEquals(testConfig.get(SupportedRules.SWITCH_STATEMENT).getName(), "SWITCH_STATEMENT");
        assertEquals(testConfig.get(SupportedRules.SWITCH_STATEMENT).getCost(), "1");

        assertEquals(testConfig.get(SupportedRules.CONTEXT_COUPLING).getName(), "CONTEXT_COUPLING");
        assertEquals(testConfig.get(SupportedRules.CONTEXT_COUPLING).getCost(), "1");
        assertEquals(testConfig.get(SupportedRules.CONTEXT_COUPLING).getParameters(), "br.com.zup.lms");
    }

    @Test
    @DisplayName("Checking if a config was not set for the cdd.json file")
    public void testName2() throws Exception {

    	Config testConfig = UserDefinitionForTesting.load();
        Exception exception = assertThrows(PluginCDDException.class, () -> {
        	testConfig.get(SupportedRules.METHODS_AUTOGEN);
        });

        assertEquals(exception.getMessage(), "The rule METHODS_AUTOGEN is not being used!");
    }

    @Test
    @DisplayName("Loading a buggy cdd.json file")
    public void testName3() throws Exception {
        String buggy = new Resources().findFile("cdd-buggy.json");

        Exception exception = assertThrows(PluginCDDException.class, () -> {
        	DefaultUserDefinitionFactory.load(buggy);
        });

        assertEquals(exception.getMessage(), "There are potential bugs in the 'cdd.json' file. Please revise it.");
    }
}
