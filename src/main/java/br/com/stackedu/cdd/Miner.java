package br.com.stackedu.cdd;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.config.DefaultUserDefinitionFactory;
import br.com.stackedu.cdd.config.SupportedRules;
import br.com.stackedu.cdd.printer.PrintMetrics;
import br.com.stackedu.cdd.storage.StoreMetrics;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import spoon.Launcher;
import spoon.processing.Processor;
import spoon.reflect.factory.Factory;

@Command(name = "cdd.jar", version = "0.0.2", header = {
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

    @Option(names = { "-f", "--full" }, description = "List the full analysis for all existing classes.")
    private boolean fullReport = false;

    @CommandLine.ArgGroup(exclusive = true, multiplicity = "0..1")
    FormatOption formatOption = new FormatOption();

    public static class FormatOption {
        // default value for formatOption
        public FormatOption() {
            this.format = Format.JSON;
        }

        public enum Format {
            TXT,
            JSON,
            HTML
        }

        @Option(names = { "-o",
                "--output" }, description = "Format of the output: ${COMPLETION-CANDIDATES}", defaultValue = "JSON")
        private Format format;
    }

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
            factory.getEnvironment().setCopyResources(false);

            spoon.addInputResource(path);

            Config config = DefaultUserDefinitionFactory.load("cdd.json");
            StoreMetrics context = new StoreMetrics();

            List<Processor> requiredProcessors = new ArrayList<>();

            for (SupportedRules rule : config.getDefinedRules()) {
                List<Processor> processors = rule.resolveProcessors(config, context);
                requiredProcessors.addAll(processors);
            }

            requiredProcessors.forEach(spoon::addProcessor);

            spoon.run();

            System.out.println(new PrintMetrics(config, context, fullReport).as(formatOption.format).print());

        } catch (spoon.compiler.ModelBuildingException e) {
            System.out.println("[ERROR] " + e.getMessage() + ". Types cannot be defined twice.");
        } catch (PluginCDDException e) {
            System.out.println("[ERROR] " + e.getMessage());
        } catch (Error e) {
            System.out.println("[ERROR] The file 'cdd.json' was not found in the root dir of the project!");
        }
    }
}