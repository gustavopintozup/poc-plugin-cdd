package br.com.stackedu.cdd;

import br.com.stackedu.cdd.Configuracoes.CDDConfig.Rules;
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
        Launcher spoon = new Launcher();

        String target = "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/";

        spoon.addInputResource(target);

        for (Rules regra : Configuracoes.regras()) {

            if (regra.getName().equals("ANNOTATION")) {
                spoon.addProcessor(new AnotacaoProcessor());
            } else if (regra.getName().equals("IF_STATEMENT")) {
                spoon.addProcessor(new IfProcessor());
            } else if (regra.getName().equals("CONDITION")) {
                spoon.addProcessor(new CondicionalProcessor());
            } else if (regra.getName().equals("TRY_STATEMENT")) {
                spoon.addProcessor(new TryProcessor());
            } else if (regra.getName().equals("CATCH_SECTION")) {
                spoon.addProcessor(new CatchProcessor());
            } else if (regra.getName().equals("LAMBDA_EXPRESSION")) {
                spoon.addProcessor(new LambdaProcessor());
            } else if (regra.getName().equals("IF_STATEMENT")) {
                spoon.addProcessor(new MetodoProcessor());
            } else if (regra.getName().equals("METHOD")) {
                spoon.addProcessor(new SwitchProcessor());
            } else if (regra.getName().equals("THROW_STATEMENT")) {
                spoon.addProcessor(new ThrowProcessor());
            } else if (regra.getName().equals("WHILE_STATEMENT")) {
                spoon.addProcessor(new WhileProcessor());
            } else if (regra.getName().equals("FOR_STATEMENT")) {
                spoon.addProcessor(new ForProcessor());
            } else if (regra.getName().equals("FOREACH_STATEMENT")) {
                spoon.addProcessor(new ForEachProcessor());
            } else if (regra.getName().equals("YIELD_STATEMENT")) {
                spoon.addProcessor(new YieldProcessor());
            } else if (regra.getName().equals("SUPER_EXPRESSION")) {
                spoon.addProcessor(new SuperProcessor());
            } else if (regra.getName().equals("ANONYMOUS_CLASS")) {
                spoon.addProcessor(new ClasseAnonimaProcessor());
            } else if (regra.getName().equals("LOCAL_VARIABLE")) {
                spoon.addProcessor(new VariavelLocalProcessor());
            }
        }

        spoon.run();
        System.out.println(Metricas.prettyprint());
    }

}
