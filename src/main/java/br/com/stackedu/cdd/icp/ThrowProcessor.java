package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.MetricasCDD;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtClass;

public class ThrowProcessor extends AbstractProcessor<CtIf> implements ICP {

    private int total;
    private List<String> values;

    public ThrowProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtIf element) {
        total++;
        this.values.add(element.getCondition().prettyprint());

        CtClass clazz = element.getParent(CtClass.class);
        MetricasCDD.store(clazz.getQualifiedName(), "if");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}