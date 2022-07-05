package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.MetricasCDD;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtLambda;
import spoon.reflect.declaration.CtClass;

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

        CtClass clazz = element.getParent(CtClass.class);
        MetricasCDD.store(clazz.getQualifiedName(), "lambda");
    }
}