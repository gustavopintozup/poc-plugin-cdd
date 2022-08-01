package br.com.stackedu.cdd;

import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.config.RegraSuportada;
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
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import spoon.Launcher;
import spoon.reflect.factory.Factory;

@Command(name = "cdd.jar", version = "0.0.2",
header = {
    "@|green ░█████╗░██████╗░██████╗░|@",
    "@|green ██╔══██╗██╔══██╗██╔══██╗|@",
    "@|green ██║░░╚═╝██║░░██║██║░░██║|@", 
    "@|green ██║░░██╗██║░░██║██║░░██║|@",
    "@|green ╚█████╔╝██████╔╝██████╔╝|@",
    "@|green ░╚════╝░╚═════╝░╚═════╝░|@",
    })
public class Minerador implements Runnable {

    @Option(names = { "-p",
            "--path" }, required = true, description = "Pasta do projeto que se deseja analisar o código.")
    private String path = new String();

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Minerador()).execute(args); 
        System.exit(exitCode); 
    }

    @Override
    public void run() {
        try {
            Launcher spoon = new Launcher();
            final Factory factory = spoon.getFactory();
            factory.getEnvironment().setComplianceLevel(17);

            spoon.addInputResource(path);

            StringBuilder processadores = new StringBuilder();
            if (Configuracoes.existe(RegraSuportada.ANNOTATION)) {
                spoon.addProcessor(new AnotacaoProcessor());
                processadores.append("Anotação");
            }
            if (Configuracoes.existe(RegraSuportada.IF_STATEMENT)) {
                spoon.addProcessor(new IfProcessor());
                processadores.append("If");
            }
            if (Configuracoes.existe(RegraSuportada.CONDITION)) {
                spoon.addProcessor(new CondicionalProcessor());
                processadores.append("Condicional");
            }
            if (Configuracoes.existe(RegraSuportada.TRY_CATCH_STATEMENT)) {
                spoon.addProcessor(new TryProcessor());
                spoon.addProcessor(new CatchProcessor());
                processadores.append("Try-catch");
            }
            if (Configuracoes.existe(RegraSuportada.THROW_STATEMENT)) {
                spoon.addProcessor(new ThrowProcessor());
                processadores.append("Throw");
            }
            if (Configuracoes.existe(RegraSuportada.LAMBDA_EXPRESSION)) {
                spoon.addProcessor(new LambdaProcessor());
                processadores.append("Lambda");
            }

            if (Configuracoes.existe(RegraSuportada.METHOD)) {
                spoon.addProcessor(new MetodoProcessor());
                processadores.append("Método");
            }
            if (Configuracoes.existe(RegraSuportada.SWITCH_STATEMENT)) {
                spoon.addProcessor(new SwitchProcessor());
                processadores.append("Switch-case");
            }
            if (Configuracoes.existe(RegraSuportada.YIELD_STATEMENT)) {
                spoon.addProcessor(new YieldProcessor());
                processadores.append("Yield");
            }
            if (Configuracoes.existe(RegraSuportada.WHILE_STATEMENT)) {
                spoon.addProcessor(new WhileProcessor());
                processadores.append("While");
            }
            if (Configuracoes.existe(RegraSuportada.FOR_STATEMENT)) {
                spoon.addProcessor(new ForProcessor());
                processadores.append("For");
            }
            if (Configuracoes.existe(RegraSuportada.FOREACH_STATEMENT)) {
                spoon.addProcessor(new ForEachProcessor());
                processadores.append("Foreach");
            }
            if (Configuracoes.existe(RegraSuportada.SUPER_EXPRESSION)) {
                spoon.addProcessor(new SuperProcessor());
                processadores.append("Super");
            }
            if (Configuracoes.existe(RegraSuportada.ANONYMOUS_CLASS)) {
                spoon.addProcessor(new ClasseAnonimaProcessor());
                processadores.append("Classe Anonima");
            }
            if (Configuracoes.existe(RegraSuportada.LOCAL_VARIABLE)) {
                spoon.addProcessor(new VariavelLocalProcessor());
                processadores.append("Variavel local");
            }
            if (Configuracoes.existe(RegraSuportada.CONTEXT_COUPLING)) {
                spoon.addProcessor(new AcoplamentoContextualProcessor());
                processadores.append("Acoplamento contextual");
            }

            spoon.run();
            System.out.println(ImprimirMetricas.json());

        } catch (Error e) {
            System.out.print("[ERROR] ");
            System.out.println("O arquivo 'cdd.json' não foi encontrado na raiz do projeto!");
        } catch (PluginCDDException e) {
            System.out.print("[ERROR] ");
            System.out.println(e.getMessage());
        }
    }
}