package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.icp.AnotacaoProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.MetodoProcessor;
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

        assertNotNull(Metricas.prettyprint());

        assertEquals("br.com.zup.lms.alunos.Aluno[anotação=28,ICP=28]\n", Metricas.prettyprint());
    }

    @Test
    public void testClasseComDoisICPsAcimaDoLimite() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));

        this.spoon.addProcessor(new AnotacaoProcessor());
        this.spoon.addProcessor(new MetodoProcessor());
        this.spoon.run();

        assertNotNull(Metricas.prettyprint());

        assertEquals("br.com.zup.lms.alunos.Aluno[anotação=28,metodo=11,ICP=39]\n", Metricas.prettyprint());
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

        assertEquals("br.com.zup.lms.alunos.Aluno[anotação=28,metodo=11,if=3,ICP=42]\n", Metricas.prettyprint());
    }

    @Test
    public void testClasseSemICPsAcimaDoLimete() throws Exception {
        this.spoon.getEnvironment().setNoClasspath(true);
        this.spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));

        this.spoon.addProcessor(new IfProcessor());
        this.spoon.run();

        assertNotNull(Metricas.prettyprint());

        assertEquals("", Metricas.prettyprint());
    }

}
