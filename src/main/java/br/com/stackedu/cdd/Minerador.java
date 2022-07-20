package br.com.stackedu.cdd;

import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.config.JSONParser.RegrasDefinidas;
import br.com.stackedu.cdd.icp.AcoplamentoContextualProcessor;
import br.com.stackedu.cdd.icp.AnotacaoProcessor;
import br.com.stackedu.cdd.icp.CatchProcessor;
import br.com.stackedu.cdd.icp.ClasseAnonimaProcessor;
import br.com.stackedu.cdd.icp.CondicionalProcessor;
import br.com.stackedu.cdd.icp.ForEachProcessor;
import br.com.stackedu.cdd.icp.ForProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.LambdaProcessor;
import br.com.stackedu.cdd.icp.MetodoProcessor;
import br.com.stackedu.cdd.icp.SuperProcessor;
import br.com.stackedu.cdd.icp.SwitchProcessor;
import br.com.stackedu.cdd.icp.ThrowProcessor;
import br.com.stackedu.cdd.icp.TryProcessor;
import br.com.stackedu.cdd.icp.VariavelLocalProcessor;
import br.com.stackedu.cdd.icp.WhileProcessor;
import br.com.stackedu.cdd.icp.YieldProcessor;
import spoon.Launcher;

public class Minerador {

    public static void main(String[] args) {

        String path = "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/";

        if (args.length > 0) {
            if (args[0].equals("-p") || args[0].equals("--path")) {
                path = args[1];
            }
        }

        Launcher spoon = new Launcher();
        spoon.addInputResource(path);

        if (Configuracoes.existe(RegrasDefinidas.ANNOTATION)) {
            spoon.addProcessor(new AnotacaoProcessor());
            // System.out.println("Anotação Definida");
        }
        if (Configuracoes.existe(RegrasDefinidas.IF_STATEMENT)) {
            spoon.addProcessor(new IfProcessor());
            // System.out.println("If Definida");
        }
        if (Configuracoes.existe(RegrasDefinidas.CONDITION)) {
            spoon.addProcessor(new CondicionalProcessor());
            // System.out.println("condicao Definida");
        }
        if (Configuracoes.existe(RegrasDefinidas.TRY_CATCH_STATEMENT)) {
            spoon.addProcessor(new TryProcessor());
            spoon.addProcessor(new CatchProcessor());
            // System.out.println("try-catch Definida");
        }
        if (Configuracoes.existe(RegrasDefinidas.LAMBDA_EXPRESSION)) {
            spoon.addProcessor(new LambdaProcessor());
            // System.out.println("lambda Definida");
        }

        if (Configuracoes.existe(RegrasDefinidas.METHOD)) {
            spoon.addProcessor(new MetodoProcessor());
            // System.out.println("method Definida");
        }
        if (Configuracoes.existe(RegrasDefinidas.SWITCH_STATEMENT)) {
            spoon.addProcessor(new SwitchProcessor());
            // System.out.println("Switch Definida");
        }

        if (Configuracoes.existe(RegrasDefinidas.THROW_STATEMENT)) {
            spoon.addProcessor(new ThrowProcessor());
            // System.out.println("throw Definida");
        }
        if (Configuracoes.existe(RegrasDefinidas.WHILE_STATEMENT)) {
            spoon.addProcessor(new WhileProcessor());
            // System.out.println("while Definida");
        }
        if (Configuracoes.existe(RegrasDefinidas.FOR_STATEMENT)) {
            spoon.addProcessor(new ForProcessor());
            // System.out.println("for Definida");
        }
        if (Configuracoes.existe(RegrasDefinidas.FOREACH_STATEMENT)) {
            spoon.addProcessor(new ForEachProcessor());
            // System.out.println("foreach Definida");
        }

        if (Configuracoes.existe(RegrasDefinidas.YIELD_STATEMENT)) {
            spoon.addProcessor(new YieldProcessor());
            // System.out.println("yield Definida");
        }
        if (Configuracoes.existe(RegrasDefinidas.SUPER_EXPRESSION)) {
            spoon.addProcessor(new SuperProcessor());
            // System.out.println("super Definida");
        }
        if (Configuracoes.existe(RegrasDefinidas.ANONYMOUS_CLASS)) {
            spoon.addProcessor(new ClasseAnonimaProcessor());
            // System.out.println("classe anonima Definida");
        }
        if (Configuracoes.existe(RegrasDefinidas.LOCAL_VARIABLE)) {
            spoon.addProcessor(new VariavelLocalProcessor());
            // System.out.println("local var Definida");
        }
        if (Configuracoes.existe(RegrasDefinidas.CONTEXT_COUPLING)) {
            spoon.addProcessor(new AcoplamentoContextualProcessor());
            // System.out.println("context Definida");
        }

        spoon.run();
        System.out.println(Metricas.prettyprint());
    }

}
