package br.com.zupedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.zupedu.cdd.config.Config;
import br.com.zupedu.cdd.config.DefaultUserDefinitionFactory;
import br.com.zupedu.cdd.icp.AnnotationProcessor;
import br.com.zupedu.cdd.icp.CatchProcessor;
import br.com.zupedu.cdd.icp.ContextualCouplingProcessor;
import br.com.zupedu.cdd.icp.IfProcessor;
import br.com.zupedu.cdd.icp.TryProcessor;
import br.com.zupedu.cdd.printer.PrintMetrics;
import br.com.zupedu.cdd.shared.UserDefinitionForTesting;
import br.com.zupedu.cdd.storage.StoreMetrics;
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

        var config = DefaultUserDefinitionFactory.load(new Resources().findFile("json/cdd-annotation.json"));
        var result = removeWhiteSpaces(new PrintMetrics(config, context).print());
        var expected = removeWhiteSpaces(
                "{\"com.mkyong.rest.UploadFileService\":{\"ANNOTATION\":12.0,\"TOTAL\":12.0}}");

        assertEquals(expected, result);
    }

    @Test
    public void testJsonWithOneProcessorWithCost() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("UploadFileService.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));
        spoon.run();

        var config = DefaultUserDefinitionFactory.load(new Resources().findFile("json/cdd-annotation-cost.json"));
        var result = removeWhiteSpaces(new PrintMetrics(config, context).print());
        var expected = removeWhiteSpaces(
                "{\"com.mkyong.rest.UploadFileService\":{\"ANNOTATION\":24.0,\"TOTAL\":24.0}}");

        assertEquals(expected, result);
    }

    @Test
    public void testJsonWithTwoClassesAndTwoProcessorsAndTwoCosts() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);

        spoon.addInputResource(new Resources().findFile("UploadFileService.java"));
        spoon.addInputResource(new Resources().findFile("GetterAccessLevel.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));

        spoon.addProcessor(new TryProcessor(context));
        spoon.addProcessor(new CatchProcessor(context));
        spoon.run();

        var config = DefaultUserDefinitionFactory.load(new Resources().findFile("json/cdd-annotation-cost-trycatch-cost.json"));
        var result = removeWhiteSpaces(new PrintMetrics(config, context).print());
        var expected = removeWhiteSpaces("{\"com.mkyong.rest.UploadFileService\":{\"TRY_CATCH_STATEMENT\":2.6,\"ANNOTATION\":24.0,\"TOTAL\":26.6},\"GetterAccessLevel\":{\"TRY_CATCH_STATEMENT\":0.0,\"ANNOTATION\":58.0,\"TOTAL\":58.0}}");

        assertEquals(expected, result);
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

        var config = DefaultUserDefinitionFactory.load(new Resources().findFile("json/cdd-annotation-if-cc-try.json"));
        var result = removeWhiteSpaces(new PrintMetrics(config, context).print());
        var expected = removeWhiteSpaces("{\"com.mkyong.rest.UploadFileService\":{\"IF_STATEMENT\":0.0,\"TRY_CATCH_STATEMENT\":2.0,\"CONTEXT_COUPLING\":0.0,\"ANNOTATION\":12.0,\"TOTAL\":14.0},\"GetterAccessLevel\":{\"IF_STATEMENT\":0.0,\"TRY_CATCH_STATEMENT\":0.0,\"CONTEXT_COUPLING\":0.0,\"ANNOTATION\":29.0,\"TOTAL\":29.0}}");

        assertEquals(expected, result);
    }

    private String removeWhiteSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }
}
