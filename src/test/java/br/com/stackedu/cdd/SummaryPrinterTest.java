package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Miner.FormatOption.Format;
import br.com.stackedu.cdd.config.DefaultUserDefinitionFactory;
import br.com.stackedu.cdd.icp.AnnotationProcessor;
import br.com.stackedu.cdd.icp.CatchProcessor;
import br.com.stackedu.cdd.icp.TryProcessor;
import br.com.stackedu.cdd.icp.WhileProcessor;
import br.com.stackedu.cdd.printer.PrettyPrinter;
import br.com.stackedu.cdd.printer.PrintMetrics;
import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class SummaryPrinterTest {

    @Test
    public void testSummaryWithJustOneClassOverTheLimit() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("UploadFileService.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));
        spoon.addProcessor(new TryProcessor(context));
        spoon.addProcessor(new CatchProcessor(context));
        spoon.addProcessor(new WhileProcessor(context));
        
        spoon.run();

        PrettyPrinter printer = new PrintMetrics(DefaultUserDefinitionFactory.load(
            new Resources().findFile("json/cdd-annotation-if-cc-try.json")), context).as(Format.SUMMARY);
        String expected = "Total Classes: 1\nTotal Classes over limit: 1";

        assertEquals(expected, printer.print());
    }


    @Test
    public void testSummaryWithNoClassOverTheLimit() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("UploadFileService.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new TryProcessor(context));
        spoon.addProcessor(new CatchProcessor(context));
        spoon.addProcessor(new WhileProcessor(context));
        
        spoon.run();

        PrettyPrinter printer = new PrintMetrics(DefaultUserDefinitionFactory.load(
            new Resources().findFile("json/cdd-annotation-if-cc-try.json")), context).as(Format.SUMMARY);
        String expected = "Total Classes: 1\nTotal Classes over limit: 0";

        assertEquals(expected, printer.print());
    }

    @Test
    public void testSummaryWithTwoClassesButNoneOverTheLimit() throws Exception {
        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("UploadFileService.java"));
        spoon.addInputResource(new Resources().findFile("LoopsSimples.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new TryProcessor(context));
        spoon.addProcessor(new CatchProcessor(context));
        spoon.addProcessor(new WhileProcessor(context));
        
        spoon.run();

        PrettyPrinter printer = new PrintMetrics(DefaultUserDefinitionFactory.load(
            new Resources().findFile("json/cdd-annotation-if-cc-try.json")), context).as(Format.SUMMARY);
        String expected = "Total Classes: 2\nTotal Classes over limit: 0";

        assertEquals(expected, printer.print());
    }


    @Test
    public void testSummaryWithTwoClassesButOneOverTheLimit() throws Exception {
        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("UploadFileService.java"));
        spoon.addInputResource(new Resources().findFile("LoopsSimples.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));
        spoon.addProcessor(new TryProcessor(context));
        spoon.addProcessor(new CatchProcessor(context));
        spoon.addProcessor(new WhileProcessor(context));
        
        spoon.run();

        PrettyPrinter printer = new PrintMetrics(DefaultUserDefinitionFactory.load(
            new Resources().findFile("json/cdd-annotation-if-cc-try.json")), context).as(Format.SUMMARY);
        String expected = "Total Classes: 2\nTotal Classes over limit: 1";

        assertEquals(expected, printer.print());
    }

}
