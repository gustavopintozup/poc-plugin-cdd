package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Miner.FormatOption.Format;
import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.icp.AnnotationProcessor;
import br.com.stackedu.cdd.icp.CatchProcessor;
import br.com.stackedu.cdd.icp.ContextualCouplingProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.TryProcessor;
import br.com.stackedu.cdd.printer.PrintMetrics;
import br.com.stackedu.cdd.shared.UserDefinitionForTesting;
import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class PrintMetricsJsonTest {

    @Test
    public void testJsonWithOneProcessor() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("UploadFileService.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));
        spoon.run();

        assertEquals(removeWhiteSpaces("{\"com.mkyong.rest.UploadFileService\":{\"IF_STATEMENT\":0,\"TRY_CATCH_STATEMENT\":0,\"SWITCH_STATEMENT\":0,\"CONDITION\":0,\"FOR_STATEMENT\":0,\"FOREACH_STATEMENT\":0,\"WHILE_STATEMENT\":0,\"CONTEXT_COUPLING\":0,\"ANNOTATION\":12,\"TOTAL\":12}}"), removeWhiteSpaces(new PrintMetrics(UserDefinitionForTesting.load(), context).as(Format.JSON).print()));
    }

    @Test
    public void testJsonWithTwoClassesAndFiveProcessors() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);

        spoon.addInputResource(new Resources().findFile("UploadFileService.java"));
        spoon.addInputResource(new Resources().findFile("GetterAccessLevel.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));
        
        Config currentConfiguration = UserDefinitionForTesting.load();
        spoon.addProcessor(new IfProcessor(currentConfiguration, context));
        spoon.addProcessor(new ContextualCouplingProcessor(currentConfiguration, context));
        spoon.addProcessor(new TryProcessor(context));
        spoon.addProcessor(new CatchProcessor(context));

        spoon.run();

        assertEquals("{\"com.mkyong.rest.UploadFileService\":{\"IF_STATEMENT\":0,\"TRY_CATCH_STATEMENT\":2,\"SWITCH_STATEMENT\":0,\"CONDITION\":0,\"FOR_STATEMENT\":0,\"FOREACH_STATEMENT\":0,\"WHILE_STATEMENT\":0,\"CONTEXT_COUPLING\":0,\"ANNOTATION\":12,\"TOTAL\":14},\"GetterAccessLevel\":{\"IF_STATEMENT\":0,\"TRY_CATCH_STATEMENT\":0,\"SWITCH_STATEMENT\":0,\"CONDITION\":0,\"FOR_STATEMENT\":0,\"FOREACH_STATEMENT\":0,\"WHILE_STATEMENT\":0,\"CONTEXT_COUPLING\":0,\"ANNOTATION\":29,\"TOTAL\":29}}",
                removeWhiteSpaces(new PrintMetrics(currentConfiguration, context).as(Format.JSON).print()));
    }

    private String removeWhiteSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }
}
