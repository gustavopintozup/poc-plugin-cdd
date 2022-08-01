package br.com.stackedu.cdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.icp.AcoplamentoContextualProcessor;
import br.com.stackedu.cdd.icp.AnotacaoProcessor;
import br.com.stackedu.cdd.icp.CatchProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.TryProcessor;
import br.com.stackedu.cdd.shared.UserDefinitionForTesting;
import spoon.Launcher;

public class ImprimirMetricasJsonTest {

    @Test
    public void testJsonUsandoUmProcessador() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));

        ArmazenarMetricas context = new ArmazenarMetricas();
        spoon.addProcessor(new AnotacaoProcessor(context));
        spoon.run();

        assertEquals("{\"br.com.zup.lms.alunos.Aluno\":{\"ICP\":\"anotação\",\"Total\":28,\"valor\":28}}",
                new ImprimirMetricas(UserDefinitionForTesting.load(), context).json());
    }

    @Test
    public void testJsonUsandoDoisProcessadores() throws Exception {

        Launcher spoon = new Launcher();

        spoon.getEnvironment().setNoClasspath(true);
        
        spoon.addInputResource(new Resources().buscaArquivo("Aluno.java"));
        spoon.addInputResource(new Resources().buscaArquivo("Ajuda.java"));
        spoon.addInputResource(new Resources().buscaArquivo("HeadingWrapper.java"));

        ArmazenarMetricas context = new ArmazenarMetricas();
        spoon.addProcessor(new AnotacaoProcessor(context));
        Configuracoes currentConfiguration = UserDefinitionForTesting.load();
		spoon.addProcessor(new IfProcessor(currentConfiguration, context));
        spoon.addProcessor(new AcoplamentoContextualProcessor(currentConfiguration, context));
        spoon.addProcessor(new TryProcessor(context));
        spoon.addProcessor(new CatchProcessor(context));

        spoon.run();

        assertEquals("{\"br.com.zup.lms.admin.HeadingWrapper\":{\"FOREACH_STATEMENT\":0,\"WHILE_STATEMENT\":0,\"ANNOTATION\":4,\"Total\":11,\"TRY_CATCH_STATEMENT\":0,\"CONDITION\":0,\"IF_STATEMENT\":1,\"SWITCH_STATEMENT\":0,\"FOR_STATEMENT\":0,\"CONTEXT_COUPLING\":6},\"br.com.zup.lms.admin.Ajuda\":{\"FOREACH_STATEMENT\":0,\"WHILE_STATEMENT\":0,\"ANNOTATION\":28,\"Total\":39,\"TRY_CATCH_STATEMENT\":0,\"CONDITION\":0,\"IF_STATEMENT\":0,\"SWITCH_STATEMENT\":0,\"FOR_STATEMENT\":0,\"CONTEXT_COUPLING\":11},\"br.com.zup.lms.alunos.Aluno\":{\"FOREACH_STATEMENT\":0,\"WHILE_STATEMENT\":0,\"ANNOTATION\":28,\"Total\":38,\"TRY_CATCH_STATEMENT\":0,\"CONDITION\":0,\"IF_STATEMENT\":1,\"SWITCH_STATEMENT\":0,\"FOR_STATEMENT\":0,\"CONTEXT_COUPLING\":9}}",
                new ImprimirMetricas(currentConfiguration, context).json());
    }
}
