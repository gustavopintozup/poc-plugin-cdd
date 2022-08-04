package br.com.stackedu.cdd;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.config.DefaultUserDefinitionFactory;
import br.com.stackedu.cdd.config.SupportedRules;
import br.com.stackedu.cdd.icp.AnnotationProcessor;
import br.com.stackedu.cdd.icp.AnonymousClassProcessor;
import br.com.stackedu.cdd.icp.CatchProcessor;
import br.com.stackedu.cdd.icp.CondicionalProcessor;
import br.com.stackedu.cdd.icp.ContextualCouplingProcessor;
import br.com.stackedu.cdd.icp.ForEachProcessor;
import br.com.stackedu.cdd.icp.ForProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.LambdaProcessor;
import br.com.stackedu.cdd.icp.LocalVarProcessor;
import br.com.stackedu.cdd.icp.MethodProcessor;
import br.com.stackedu.cdd.icp.SuperProcessor;
import br.com.stackedu.cdd.icp.SwitchProcessor;
import br.com.stackedu.cdd.icp.ThrowProcessor;
import br.com.stackedu.cdd.icp.TryProcessor;
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
public class Miner implements Runnable {

    @Option(names = { "-p",
            "--path" }, required = true, description = "Path to the project to be analyzed.")
    private String path = new String();

    @Option(names = { "-o", "--output" }, description = "Type of output: json or txt. Default: json")
    private String output = new String("json");

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Miner()).execute(args); 
        System.exit(exitCode); 
    }

    @Override
    public void run() {
        try {
            Launcher spoon = new Launcher();
            final Factory factory = spoon.getFactory();
            factory.getEnvironment().setComplianceLevel(17);
            
            spoon.addInputResource(path);
            
            Config config = DefaultUserDefinitionFactory.load("cdd.json");

            StoreMetrics context = new StoreMetrics();
            if (config.exists(SupportedRules.ANNOTATION)) {
                spoon.addProcessor(new AnnotationProcessor(context));
            }
            if (config.exists(SupportedRules.IF_STATEMENT)) {
                spoon.addProcessor(new IfProcessor(config, context));
            }
            if (config.exists(SupportedRules.CONDITION)) {
                spoon.addProcessor(new CondicionalProcessor(config, context));
            }
            if (config.exists(SupportedRules.TRY_CATCH_STATEMENT)) {
                spoon.addProcessor(new TryProcessor(context));
                spoon.addProcessor(new CatchProcessor(context));
            }
            if (config.exists(SupportedRules.THROW_STATEMENT)) {
                spoon.addProcessor(new ThrowProcessor(context));
            }
            if (config.exists(SupportedRules.LAMBDA_EXPRESSION)) {
                spoon.addProcessor(new LambdaProcessor(context));
            }

            if (config.exists(SupportedRules.METHOD)) {
                spoon.addProcessor(new MethodProcessor(config, context));
            }
            if (config.exists(SupportedRules.SWITCH_STATEMENT)) {
                spoon.addProcessor(new SwitchProcessor(context));
            }
            if (config.exists(SupportedRules.YIELD_STATEMENT)) {
                spoon.addProcessor(new YieldProcessor(context));
            }
            if (config.exists(SupportedRules.WHILE_STATEMENT)) {
                spoon.addProcessor(new WhileProcessor(context));
            }
            if (config.exists(SupportedRules.FOR_STATEMENT)) {
                spoon.addProcessor(new ForProcessor(context));
            }
            if (config.exists(SupportedRules.FOREACH_STATEMENT)) {
                spoon.addProcessor(new ForEachProcessor(context));
            }
            if (config.exists(SupportedRules.SUPER_EXPRESSION)) {
                spoon.addProcessor(new SuperProcessor(context));
            }
            if (config.exists(SupportedRules.ANONYMOUS_CLASS)) {
                spoon.addProcessor(new AnonymousClassProcessor(context));
            }
            if (config.exists(SupportedRules.LOCAL_VARIABLE)) {
                spoon.addProcessor(new LocalVarProcessor(context));
            }
            if (config.exists(SupportedRules.CONTEXT_COUPLING)) {
                spoon.addProcessor(new ContextualCouplingProcessor(config, context));
            }

            spoon.run();
            
            System.out.println(new PrintMetrics(config, new StoreMetrics()).as(output));

        } catch (Error e) {
            System.out.print("[ERROR] ");
            System.out.println("The file 'cdd.json' was not found in the root dir of the project!");
        } catch (PluginCDDException e) {
            System.out.print("[ERROR] ");
            System.out.println(e.getMessage());
        }
    }
}