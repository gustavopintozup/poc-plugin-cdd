package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.MetricasCDD;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

public class MetodoProcessor extends AbstractProcessor<CtMethod> implements ICP {

    private final List<String> metodos;
    private int total;

    public MetodoProcessor() {
        this.metodos = new ArrayList<>();
    }

    @Override
    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return metodos;
    }

    @Override
    public void process(CtMethod element) {
        CtClass clazz = element.getParent(CtClass.class);

        if (clazz != null) {
            MetricasCDD.store(clazz.getQualifiedName(), "metodo");
            this.total++;
            metodos.add(element.getSimpleName());
        }
    }
}