package br.com.stackedu.cdd;

import br.com.stackedu.cdd.ArquivoDeConfig.CDDConfig.Rules;
import br.com.stackedu.cdd.icp.AnotacaoProcessor;
import br.com.stackedu.cdd.icp.CatchProcessor;
import br.com.stackedu.cdd.icp.LambdaProcessor;
import br.com.stackedu.cdd.icp.MetodoProcessor;
import br.com.stackedu.cdd.icp.SwitchProcessor;
import br.com.stackedu.cdd.icp.ThrowProcessor;
import br.com.stackedu.cdd.icp.TryProcessor;
import spoon.Launcher;

public class MineradorCDD {

    public static void main(String[] args) {
        Launcher spoon = new Launcher();

        String target = "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/";

        spoon.addInputResource(target);

        // for (Rules regra : ArquivoDeConfig.regras()) {
        //     switch (regra.name) {
        //         case "IF_STATEMENT" -> spoon.addProcessor(new AnotacaoProcessor());

        //     }
        // }

        spoon.addProcessor(new AnotacaoProcessor());
        spoon.addProcessor(new TryProcessor());
        spoon.addProcessor(new CatchProcessor());
        spoon.addProcessor(new LambdaProcessor());
        spoon.addProcessor(new MetodoProcessor());
        spoon.addProcessor(new SwitchProcessor());
        spoon.addProcessor(new ThrowProcessor());

        spoon.run();

        System.out.println(MetricasCDD.prettyprint());
    }

}
