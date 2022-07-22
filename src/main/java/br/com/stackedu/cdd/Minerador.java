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

        try {

            String path = "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/";
            Boolean debug = false;

            if (args.length > 0) {
                if (args[0].equals("-p") || args[0].equals("--path")) {
                    path = args[1];
                }
            }

            Launcher spoon = new Launcher();
            spoon.addInputResource(path);

            StringBuilder processadores = new StringBuilder();
            if (Configuracoes.existe(RegrasDefinidas.ANNOTATION)) {
                spoon.addProcessor(new AnotacaoProcessor());
                processadores.append("Anotação");
            }
            if (Configuracoes.existe(RegrasDefinidas.IF_STATEMENT)) {
                spoon.addProcessor(new IfProcessor());
                processadores.append("If");
            }
            if (Configuracoes.existe(RegrasDefinidas.CONDITION)) {
                spoon.addProcessor(new CondicionalProcessor());
                processadores.append("Condicional");
            }
            if (Configuracoes.existe(RegrasDefinidas.TRY_CATCH_STATEMENT)) {
                spoon.addProcessor(new TryProcessor());
                spoon.addProcessor(new CatchProcessor());
                processadores.append("Try-catch");
            }
            if (Configuracoes.existe(RegrasDefinidas.THROW_STATEMENT)) {
                spoon.addProcessor(new ThrowProcessor());
                processadores.append("Throw");
            }
            if (Configuracoes.existe(RegrasDefinidas.LAMBDA_EXPRESSION)) {
                spoon.addProcessor(new LambdaProcessor());
                processadores.append("Lambda");
            }

            if (Configuracoes.existe(RegrasDefinidas.METHOD)) {
                spoon.addProcessor(new MetodoProcessor());
                processadores.append("Método");
            }
            if (Configuracoes.existe(RegrasDefinidas.SWITCH_STATEMENT)) {
                spoon.addProcessor(new SwitchProcessor());
                processadores.append("Switch-case");
            }
            if (Configuracoes.existe(RegrasDefinidas.YIELD_STATEMENT)) {
                spoon.addProcessor(new YieldProcessor());
                processadores.append("Yield");
            }
            if (Configuracoes.existe(RegrasDefinidas.WHILE_STATEMENT)) {
                spoon.addProcessor(new WhileProcessor());
                processadores.append("While");
            }
            if (Configuracoes.existe(RegrasDefinidas.FOR_STATEMENT)) {
                spoon.addProcessor(new ForProcessor());
                processadores.append("For");
            }
            if (Configuracoes.existe(RegrasDefinidas.FOREACH_STATEMENT)) {
                spoon.addProcessor(new ForEachProcessor());
                processadores.append("Foreach");
            }
            if (Configuracoes.existe(RegrasDefinidas.SUPER_EXPRESSION)) {
                spoon.addProcessor(new SuperProcessor());
                processadores.append("Super");
            }
            if (Configuracoes.existe(RegrasDefinidas.ANONYMOUS_CLASS)) {
                spoon.addProcessor(new ClasseAnonimaProcessor());
                processadores.append("Classe Anonima");
            }
            if (Configuracoes.existe(RegrasDefinidas.LOCAL_VARIABLE)) {
                spoon.addProcessor(new VariavelLocalProcessor());
                processadores.append("Variavel local");
            }
            if (Configuracoes.existe(RegrasDefinidas.CONTEXT_COUPLING)) {
                spoon.addProcessor(new AcoplamentoContextualProcessor());
                processadores.append("Acoplamento contextual");
            }

            spoon.run();
            System.out.println(Metricas.prettyprint());

        } catch (Error e) {
            System.out.print("[ERROR] ");
            System.out.println("O arquivo 'cdd.json' não foi encontrado na raiz do projeto!");
        } catch (PluginCDDException e) {
            System.out.print("[ERROR] ");
            System.out.println(e.getMessage());
        }
    }
}