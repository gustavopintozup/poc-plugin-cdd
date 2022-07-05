package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.MetricasCDD;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtType;

public class VariavelLocalProcessor extends AbstractProcessor<CtField> implements ICP {

    private int total;
    private List<String> values;

    public VariavelLocalProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtField element) {
        total++;
        System.out.println(element.getSimpleName());
        this.values.add(element.getSimpleName());

        CtType clazz = element.getParent(CtType.class);
        MetricasCDD.store(clazz.getQualifiedName(), "variavelLocal");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}