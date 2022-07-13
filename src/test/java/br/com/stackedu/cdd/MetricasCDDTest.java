package br.com.stackedu.cdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.stackedu.cdd.icp.AnotacaoProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.MetodoProcessor;
import spoon.Launcher;

public class MetricasCDDTest {

    private Launcher spoon;

    @After
    public void cleanup() {
        this.spoon = null;
    }

    @Before
    public void setup() {
        this.spoon = new Launcher();
    }

    @Test
    public void testClasseComUmICPAcimaDoLimite() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));

        this.spoon.addProcessor(new AnotacaoProcessor());
        this.spoon.run();

        assertNotNull(Metricas.prettyprint());

        assertEquals("br.com.zup.lms.alunos.Aluno[anotação=28]", Metricas.prettyprint());
    }

    @Test
    public void testClasseComDoisICPsAcimaDoLimite() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));

        this.spoon.addProcessor(new AnotacaoProcessor());
        this.spoon.addProcessor(new MetodoProcessor());
        this.spoon.run();

        assertNotNull(Metricas.prettyprint());

        assertEquals("br.com.zup.lms.alunos.Aluno[anotação=28,metodo=11,]", Metricas.prettyprint());
    }

    @Test
    public void testClasseComDoisICPsAcimaEUmICPAbaixoDoLimite() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));

        this.spoon.addProcessor(new AnotacaoProcessor());
        this.spoon.addProcessor(new MetodoProcessor());
        this.spoon.addProcessor(new IfProcessor());
        this.spoon.run();

        assertNotNull(Metricas.prettyprint());

        assertEquals("br.com.zup.lms.alunos.Aluno[anotação=28,metodo=11,]", Metricas.prettyprint());
    }

    @Test
    public void testClasseSemICPsAcimaDoLimete() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));

        this.spoon.addProcessor(new IfProcessor());
        this.spoon.run();

        assertNotNull(Metricas.prettyprint());

        assertEquals("br.com.zup.lms.alunos.Aluno[]", Metricas.prettyprint());
    }

}
