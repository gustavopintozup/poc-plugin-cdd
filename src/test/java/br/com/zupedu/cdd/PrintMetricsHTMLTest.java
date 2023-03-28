package br.com.zupedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.zupedu.cdd.Miner.FormatOption.Format;
import br.com.zupedu.cdd.config.DefaultUserDefinitionFactory;
import br.com.zupedu.cdd.icp.AnnotationProcessor;
import br.com.zupedu.cdd.printer.PrintMetrics;
import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class PrintMetricsHTMLTest {

    @Test
    public void testJsonWithOneProcessor() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("UploadFileService.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));
        spoon.run();

        String result = new PrintMetrics(DefaultUserDefinitionFactory.load(new Resources().findFile("json/cdd-annotation.json")), context).as(Format.HTML).print();
        String expected = "<h2>CDD Report</h2><table border='1'><tr><td>Class</td><td style='text-align: center;'>ANNOTATION</td><td style='text-align: center;'>TOTAL</td></tr><tr><th style='text-align:left'>com.mkyong.rest.UploadFileService</th><td style='text-align: center;'>12.0</td><td style='text-align: center;'>12.0</td></tr></table>";

        assertEquals(expected, result);
    }

}
