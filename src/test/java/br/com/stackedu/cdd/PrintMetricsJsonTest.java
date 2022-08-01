package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.icp.AnnotationProcessor;
import br.com.stackedu.cdd.icp.CatchProcessor;
import br.com.stackedu.cdd.icp.ContextualCouplingProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.TryProcessor;
import br.com.stackedu.cdd.shared.UserDefinitionForTesting;
import spoon.Launcher;

public class PrintMetricsJsonTest {

    @Test
    public void testJsonWithOneProcessor() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("Aluno.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));
        spoon.run();

        assertEquals(removeWhiteSpaces("{" +
                "  \"br.com.zup.lms.alunos.Aluno\" : {" +
                "    \"FOREACH_STATEMENT\" : 0," +
                "    \"TOTAL\" : 28," +
                "    \"WHILE_STATEMENT\" : 0," +
                "    \"ANNOTATION\" : 28," +
                "    \"TRY_CATCH_STATEMENT\" : 0," +
                "    \"CONDITION\" : 0," +
                "    \"IF_STATEMENT\" : 0," +
                "    \"SWITCH_STATEMENT\" : 0," +
                "    \"FOR_STATEMENT\" : 0," +
                "    \"CONTEXT_COUPLING\" : 0" +
                "  }" +
                "}"), removeWhiteSpaces(new PrintMetrics(UserDefinitionForTesting.load(), context).json()));
    }

    @Test
    public void testJsonWithFourProcessors() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);

        spoon.addInputResource(new Resources().findFile("Aluno.java"));
        spoon.addInputResource(new Resources().findFile("Ajuda.java"));
        spoon.addInputResource(new Resources().findFile("HeadingWrapper.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));
        
        Config currentConfiguration = UserDefinitionForTesting.load();
        spoon.addProcessor(new IfProcessor(currentConfiguration, context));
        spoon.addProcessor(new ContextualCouplingProcessor(currentConfiguration, context));
        spoon.addProcessor(new TryProcessor(context));
        spoon.addProcessor(new CatchProcessor(context));

        spoon.run();

        assertEquals(removeWhiteSpaces("{" +
                "  \"br.com.zup.lms.admin.HeadingWrapper\" : {" +
                "    \"FOREACH_STATEMENT\" : 0," +
                "    \"TOTAL\" : 11," +
                "    \"WHILE_STATEMENT\" : 0," +
                "    \"ANNOTATION\" : 4," +
                "    \"TRY_CATCH_STATEMENT\" : 0," +
                "    \"CONDITION\" : 0," +
                "    \"IF_STATEMENT\" : 1," +
                "    \"SWITCH_STATEMENT\" : 0," +
                "    \"FOR_STATEMENT\" : 0," +
                "    \"CONTEXT_COUPLING\" : 6" +
                "  }," +
                "  \"br.com.zup.lms.admin.Ajuda\" : {" +
                "    \"FOREACH_STATEMENT\" : 0," +
                "    \"TOTAL\" : 39," +
                "    \"WHILE_STATEMENT\" : 0," +
                "    \"ANNOTATION\" : 28," +
                "    \"TRY_CATCH_STATEMENT\" : 0," +
                "    \"CONDITION\" : 0," +
                "    \"IF_STATEMENT\" : 0," +
                "    \"SWITCH_STATEMENT\" : 0," +
                "    \"FOR_STATEMENT\" : 0," +
                "    \"CONTEXT_COUPLING\" : 11" +
                "  }," +
                "  \"br.com.zup.lms.alunos.Aluno\" : {" +
                "    \"FOREACH_STATEMENT\" : 0," +
                "    \"TOTAL\" : 38," +
                "    \"WHILE_STATEMENT\" : 0," +
                "    \"ANNOTATION\" : 28," +
                "    \"TRY_CATCH_STATEMENT\" : 0," +
                "    \"CONDITION\" : 0," +
                "    \"IF_STATEMENT\" : 1," +
                "    \"SWITCH_STATEMENT\" : 0," +
                "    \"FOR_STATEMENT\" : 0," +
                "    \"CONTEXT_COUPLING\" : 9" +
                "  }" +
                "}"),
                removeWhiteSpaces(new PrintMetrics(currentConfiguration, context).json()));
    }

    private String removeWhiteSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }
}
