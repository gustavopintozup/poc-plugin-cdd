package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.icp.AnotacaoProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.MetodoProcessor;
import br.com.stackedu.cdd.shared.UserDefinitionForTesting;
import spoon.Launcher;

public class MetricasCDDTest {

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
    public void testClasseComUmICPAcimaDoLimite() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));

        this.spoon.addProcessor(new AnotacaoProcessor());
        this.spoon.run();

        ImprimirMetricas imprimirMetricas = new ImprimirMetricas(UserDefinitionForTesting.load());
		assertNotNull(imprimirMetricas.console());

        assertEquals("br.com.zup.lms.alunos.Aluno[ANNOTATION=28,ICP=28]\n", imprimirMetricas.console());
    }

    @Test
    public void testClasseComDoisICPsAcimaDoLimite() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));

        this.spoon.addProcessor(new AnotacaoProcessor());
        Configuracoes testConfig = UserDefinitionForTesting.load();
        this.spoon.addProcessor(new MetodoProcessor(testConfig));
        this.spoon.run();

        assertNotNull(new ImprimirMetricas(testConfig).console());

        assertEquals("br.com.zup.lms.alunos.Aluno[ANNOTATION=28,METHOD=11,ICP=39]\n", new ImprimirMetricas(testConfig).console());
    }

    @Test
    public void testClasseComDoisICPsAcimaEUmICPAbaixoDoLimite() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));

        this.spoon.addProcessor(new AnotacaoProcessor());
        Configuracoes testConfig = UserDefinitionForTesting.load();
        this.spoon.addProcessor(new MetodoProcessor(testConfig));
        this.spoon.addProcessor(new IfProcessor(testConfig));
        this.spoon.run();

        assertNotNull(new ImprimirMetricas(testConfig).console());

        assertEquals("br.com.zup.lms.alunos.Aluno[ANNOTATION=28,METHOD=11,IF_STATEMENT=1,ICP=40]\n", new ImprimirMetricas(testConfig).console());
    }

    @Test
    public void testClasseSemICPsAcimaDoLimete() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));

        Configuracoes testConfig = UserDefinitionForTesting.load();
        this.spoon.addProcessor(new IfProcessor(testConfig));
        this.spoon.run();

        assertNotNull(new ImprimirMetricas(testConfig).console());

        assertEquals("", new ImprimirMetricas(testConfig).console());
    }

}
