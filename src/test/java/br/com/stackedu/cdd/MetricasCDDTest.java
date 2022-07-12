package br.com.stackedu.cdd;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.stackedu.cdd.icp.AnotacaoProcessor;
import spoon.Launcher;

public class MetricasCDDTest {

    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
                "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/alunos");

        AnotacaoProcessor processor = new AnotacaoProcessor();

        l.addProcessor(processor);
        l.run();
        assertNotNull(Metricas.prettyprint());
    }

}
