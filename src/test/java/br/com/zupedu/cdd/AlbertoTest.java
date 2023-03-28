package br.com.zupedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import br.com.zupedu.cdd.Miner.FormatOption.Format;
import br.com.zupedu.cdd.icp.ContextualCouplingProcessor;
import br.com.zupedu.cdd.printer.PrintMetrics;
import br.com.zupedu.cdd.shared.UserDefinitionForTesting;
import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

@Disabled
public class AlbertoTest {
    @Test
    public void test() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("NovoTreinamentoController.java"));

        StoreMetrics context = new StoreMetrics();
        
        var x = new ContextualCouplingProcessor(UserDefinitionForTesting.load(), context);
        spoon.addProcessor(x);
        spoon.run();

        PrintMetrics printer = new PrintMetrics(UserDefinitionForTesting.load(), context);
        assertNotNull(printer.as(Format.TXT).print());
        
        assertEquals(x.values(), new ArrayList<>());
        assertEquals("br.com.zup.lms.alunos.Aluno[ANNOTATION=28,ICP=28]\n", printer.as(Format.TXT).print());
    }

    @Test
    public void test2() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource("/home/gustavopinto/workspace/poc-plugin-cdd/spooned/");

        StoreMetrics context = new StoreMetrics();
        
        var x = new ContextualCouplingProcessor(UserDefinitionForTesting.load(), context);
        spoon.addProcessor(x);
        spoon.run();

        PrintMetrics printer = new PrintMetrics(UserDefinitionForTesting.load(), context);
        assertNotNull(printer.as(Format.TXT).print());
        
        assertEquals(x.values(), new ArrayList<>());
        assertEquals("br.com.zup.lms.alunos.Aluno[ANNOTATION=28,ICP=28]\n", printer.as(Format.TXT).print());
    }
}
