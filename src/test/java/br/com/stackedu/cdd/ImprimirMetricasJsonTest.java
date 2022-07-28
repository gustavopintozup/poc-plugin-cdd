package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.icp.AcoplamentoContextualProcessor;
import br.com.stackedu.cdd.icp.AnotacaoProcessor;
import br.com.stackedu.cdd.icp.CatchProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.TryProcessor;
import spoon.Launcher;

public class ImprimirMetricasJsonTest {

    @Test
    public void testJsonUsandoUmProcessador() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));

        spoon.addProcessor(new AnotacaoProcessor());
        spoon.run();

        assertEquals("{\"br.com.zup.lms.alunos.Aluno\":{\"ICP\":\"anotação\",\"Total\":28,\"valor\":28}}",
                ImprimirMetricas.json());
    }

    @Test
    public void testJsonUsandoDoisProcessadores() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        
        spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));
        spoon.addInputResource(new Resources().buscaArquivo("Ajuda.java"));
        spoon.addInputResource(new Resources().buscaArquivo("HeadingWrapper.java"));

        spoon.addProcessor(new AnotacaoProcessor());
        spoon.addProcessor(new IfProcessor());
        spoon.addProcessor(new AcoplamentoContextualProcessor());
        spoon.addProcessor(new TryProcessor());
        spoon.addProcessor(new CatchProcessor());
        

        spoon.run();

        assertEquals("{\"br.com.zup.lms.alunos.Aluno\":{\"ICP\":\"anotação\",\"Total\":28,\"valor\":28}}",
                ImprimirMetricas.json());
    }
}
