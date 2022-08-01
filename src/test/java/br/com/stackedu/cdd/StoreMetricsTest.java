package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.icp.AnnotationProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.MethodProcessor;
import br.com.stackedu.cdd.shared.UserDefinitionForTesting;
import spoon.Launcher;

public class StoreMetricsTest {

    private Launcher spoon;

    @AfterEach
    public void cleanup() {
        this.spoon = null;
    }

    @BeforeEach
    public void setup() {
        this.spoon = new Launcher();
    }

    @Test
    public void testClassWithOneICPAboveLimit() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().findFile("Aluno.java"));

        StoreMetrics context = new StoreMetrics();
        this.spoon.addProcessor(new AnnotationProcessor(context));
        this.spoon.run();

        PrintMetrics print = new PrintMetrics(UserDefinitionForTesting.load(), context);
		assertNotNull(print.console());

        assertEquals("br.com.zup.lms.alunos.Aluno[ANNOTATION=28,ICP=28]\n", print.console());
    }

    @Test
    public void testClassWithTwoICPsAboveLimit() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().findFile("Aluno.java"));

        StoreMetrics context = new StoreMetrics();
        this.spoon.addProcessor(new AnnotationProcessor(context));

        Config testConfig = UserDefinitionForTesting.load();
        this.spoon.addProcessor(new MethodProcessor(testConfig, context));
        this.spoon.run();

        assertNotNull(new PrintMetrics(testConfig, context).console());

        assertEquals("br.com.zup.lms.alunos.Aluno[ANNOTATION=28,METHOD=11,ICP=39]\n", new PrintMetrics(testConfig, context).console());
    }

    @Test
    public void testClasseWithTwoICPsBelowLimit() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().findFile("Aluno.java"));

        StoreMetrics context = new StoreMetrics();
        this.spoon.addProcessor(new AnnotationProcessor(context));
        Config testConfig = UserDefinitionForTesting.load();
        this.spoon.addProcessor(new MethodProcessor(testConfig, context));
        this.spoon.addProcessor(new IfProcessor(testConfig, context));
        this.spoon.run();

        assertNotNull(new PrintMetrics(testConfig, context).console());

        assertEquals("br.com.zup.lms.alunos.Aluno[ANNOTATION=28,METHOD=11,IF_STATEMENT=1,ICP=40]\n", new PrintMetrics(testConfig, context).console());
    }

    @Test
    public void testClassWithoutICPAboveLimit() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().findFile("Aluno.java"));

        Config testConfig = UserDefinitionForTesting.load();
        StoreMetrics context = new StoreMetrics();
        this.spoon.addProcessor(new IfProcessor(testConfig, context));
        this.spoon.run();
        
        assertNotNull(new PrintMetrics(testConfig, context).console());

        assertEquals("", new PrintMetrics(testConfig, context).console());
    }

}
