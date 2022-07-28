package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.ArmazenarMetricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtLambda;
import spoon.reflect.declaration.CtType;

public class LambdaProcessor extends AbstractProcessor<CtLambda> implements ICP {

    private int total;
    private List<String> stms;

    public LambdaProcessor() {
        stms = new ArrayList<>();
    }

    @Override
    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return stms;
    }

    @Override
    public void process(CtLambda element) {
        this.total++;
        this.stms.add(element.prettyprint());

        CtType type = element.getParent(CtType.class);
        ArmazenarMetricas.salvar(type.getQualifiedName(), "lambda");
    }
}