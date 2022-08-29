package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Miner.FormatOption.Format;
import br.com.stackedu.cdd.icp.AnnotationProcessor;
import br.com.stackedu.cdd.printer.PrintMetrics;
import br.com.stackedu.cdd.shared.UserDefinitionForTesting;
import br.com.stackedu.cdd.storage.StoreMetrics;
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

        String result = new PrintMetrics(UserDefinitionForTesting.load(), context).as(Format.HTML).print();

        assertEquals("<table border='1'><tr><td>Class</td><td style='text-align: center;'>IF_STATEMENT</td><td style='text-align: center;'>TRY_CATCH_STATEMENT</td><td style='text-align: center;'>SWITCH_STATEMENT</td><td style='text-align: center;'>CONDITION</td><td style='text-align: center;'>FOR_STATEMENT</td><td style='text-align: center;'>FOREACH_STATEMENT</td><td style='text-align: center;'>WHILE_STATEMENT</td><td style='text-align: center;'>CONTEXT_COUPLING</td><td style='text-align: center;'>TOTAL</td></tr><tr><th style='text-align:left'>com.mkyong.rest.UploadFileService</th><td style='text-align: center;'>0</td><td style='text-align: center;'>0</td><td style='text-align: center;'>0</td><td style='text-align: center;'>0</td><td style='text-align: center;'>0</td><td style='text-align: center;'>0</td><td style='text-align: center;'>0</td><td style='text-align: center;'>0</td><td style='text-align: center;'>12</td><td style='text-align: center;'>12</td></tr></table>", result);
    }

}
