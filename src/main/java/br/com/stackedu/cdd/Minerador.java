package br.com.stackedu.cdd;

import br.com.stackedu.cdd.Configuracoes.CDDConfig.Rules;
import br.com.stackedu.cdd.icp.AnotacaoProcessor;
import br.com.stackedu.cdd.icp.CatchProcessor;
import br.com.stackedu.cdd.icp.ClasseAnonimaProcessor;
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

            if (regra.name.equals("ANNOTATION")) {
                spoon.addProcessor(new AnotacaoProcessor());
            } else if (regra.name.equals("IF_STATEMENT")) {
                spoon.addProcessor(new IfProcessor());
            } else if (regra.name.equals("TRY_STATEMENT")) {
                spoon.addProcessor(new TryProcessor());
            } else if (regra.name.equals("CATCH_SECTION")) {
                spoon.addProcessor(new CatchProcessor());
            } else if (regra.name.equals("LAMBDA_EXPRESSION")) {
                spoon.addProcessor(new LambdaProcessor());
            } else if (regra.name.equals("IF_STATEMENT")) {
                spoon.addProcessor(new MetodoProcessor());
            } else if (regra.name.equals("METHOD")) {
                spoon.addProcessor(new SwitchProcessor());
            } else if (regra.name.equals("THROW_STATEMENT")) {
                spoon.addProcessor(new ThrowProcessor());
            } else if (regra.name.equals("WHILE_STATEMENT")) {
                spoon.addProcessor(new WhileProcessor());
            } else if (regra.name.equals("FOR_STATEMENT")) {
                spoon.addProcessor(new ForProcessor());
            } else if (regra.name.equals("FOREACH_STATEMENT")) {
                spoon.addProcessor(new ForEachProcessor());
            } else if (regra.name.equals("YIELD_STATEMENT")) {
                spoon.addProcessor(new YieldProcessor());
            } else if (regra.name.equals("SUPER_EXPRESSION")) {
                spoon.addProcessor(new SuperProcessor());
            } else if (regra.name.equals("ANONYMOUS_CLASS")) {
                spoon.addProcessor(new ClasseAnonimaProcessor());
            } else if (regra.name.equals("LOCAL_VARIABLE")) {
                spoon.addProcessor(new VariavelLocalProcessor());
            }
        }

        spoon.run();
        System.out.println(Metricas.prettyprint());
    }

}
