package br.com.stackedu.cdd;

import br.com.stackedu.cdd.icp.AnotacaoProcessor;
import spoon.Launcher;

public class MineradorCDD {

    public static void main(String[] args) {
        Launcher spoon = new Launcher();

        String target = "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/";

        spoon.addInputResource(target);

        // spoon.run();

        // final Factory factory = spoon.getFactory();
        // final ProcessingManager processingManager = new QueueProcessingManager(factory);
        
        AnotacaoProcessor annProcessor = new AnotacaoProcessor();
        spoon.addProcessor(annProcessor);
        spoon.run();


        
        System.out.println(spoon.getFactory().Class().getAll());

        System.out.println(annProcessor.valores());
        

    }

}
