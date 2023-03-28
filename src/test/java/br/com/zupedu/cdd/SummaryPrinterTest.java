package br.com.zupedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.zupedu.cdd.Miner.FormatOption.Format;
import br.com.zupedu.cdd.config.DefaultUserDefinitionFactory;
import br.com.zupedu.cdd.icp.AnnotationProcessor;
import br.com.zupedu.cdd.icp.CatchProcessor;
import br.com.zupedu.cdd.icp.TryProcessor;
import br.com.zupedu.cdd.icp.WhileProcessor;
import br.com.zupedu.cdd.printer.PrettyPrinter;
import br.com.zupedu.cdd.printer.PrintMetrics;
import br.com.zupedu.cdd.storage.StoreMetrics;
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
