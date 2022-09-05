package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Miner.FormatOption.Format;
import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.icp.AnnotationProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.MethodProcessor;
import br.com.stackedu.cdd.printer.PrintMetrics;
import br.com.stackedu.cdd.shared.UserDefinitionForTesting;
import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class StoreMetricsTest {

    @Test
    public void testClassWithOneICPAboveLimit() throws Exception {
        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("GetterAccessLevel.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));
        spoon.run();

        PrintMetrics print = new PrintMetrics(UserDefinitionForTesting.load(), context);
        assertNotNull(print.as(Format.TXT).print());

        assertEquals("GetterAccessLevel[ANNOTATION=29,ICP=29]\n", print.as(Format.TXT).print());
    }

    @Test
    @Disabled("Found a bug here. The limit should be per ICP, and not the sum of all ICPs")
    public void testClasseWithOneICPAboveAndOneICPBelowLimit() throws Exception {
        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("GetterAccessLevel.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));

        Config testConfig = UserDefinitionForTesting.load();
        spoon.addProcessor(new MethodProcessor(testConfig, context));
        spoon.run();

        assertNotNull(new PrintMetrics(testConfig, context).as(Format.TXT).print());

        assertEquals("GetterAccessLevel[ANNOTATION=29,ICP=38]\n",
                new PrintMetrics(testConfig, context).as(Format.TXT).print());
    }

    @Test
    public void testClassWithoutICPAboveLimit() throws Exception {
        Launcher spoon = new Launcher();
        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("AlunoSimples.java"));

        Config testConfig = UserDefinitionForTesting.load();
        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new IfProcessor(testConfig, context));
        spoon.run();

        assertNotNull(new PrintMetrics(testConfig, context).as(Format.TXT).print());

        assertEquals("", new PrintMetrics(testConfig, context).as(Format.TXT).print());
    }

}
