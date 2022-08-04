package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.icp.AnnotationProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.MethodProcessor;
import br.com.stackedu.cdd.shared.UserDefinitionForTesting;
import spoon.Launcher;

public class StoreMetricsTest {

    @Test
    public void testClassWithOneICPAboveLimit() throws Exception {
        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("Aluno.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));
        spoon.run();

        PrintMetrics print = new PrintMetrics(UserDefinitionForTesting.load(), context);
        assertNotNull(print.txt());

        assertEquals("br.com.zup.lms.alunos.Aluno[ANNOTATION=28,ICP=28]\n", print.txt());
    }

    @Test
    public void testClassWithTwoICPsAboveLimit() throws Exception {
        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("Aluno.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));

        Config testConfig = UserDefinitionForTesting.load();
        spoon.addProcessor(new MethodProcessor(testConfig, context));
        spoon.run();

        assertNotNull(new PrintMetrics(testConfig, context).txt());

        assertEquals("br.com.zup.lms.alunos.Aluno[ANNOTATION=28,METHOD=11,ICP=39]\n",
                new PrintMetrics(testConfig, context).txt());
    }

    @Test
    public void testClasseWithTwoICPsBelowLimit() throws Exception {
        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("Aluno.java"));

        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new AnnotationProcessor(context));

        Config testConfig = UserDefinitionForTesting.load();
        spoon.addProcessor(new MethodProcessor(testConfig, context));
        spoon.addProcessor(new IfProcessor(testConfig, context));
        spoon.run();

        assertNotNull(new PrintMetrics(testConfig, context).txt());

        assertEquals("br.com.zup.lms.alunos.Aluno[ANNOTATION=28,METHOD=11,IF_STATEMENT=1,ICP=40]\n",
                new PrintMetrics(testConfig, context).txt());
    }

    @Test
    public void testClassWithoutICPAboveLimit() throws Exception {
        Launcher spoon = new Launcher();
        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().findFile("Aluno.java"));

        Config testConfig = UserDefinitionForTesting.load();
        StoreMetrics context = new StoreMetrics();
        spoon.addProcessor(new IfProcessor(testConfig, context));
        spoon.run();

        assertNotNull(new PrintMetrics(testConfig, context).txt());

        assertEquals("", new PrintMetrics(testConfig, context).txt());
    }

}
