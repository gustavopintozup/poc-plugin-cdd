package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.MetricasCDD;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

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
        CtType clazz = element.getParent(CtType.class);
        MetricasCDD.store(clazz.getQualifiedName(), "metodo");
        
        this.total++;
        metodos.add(element.getSimpleName());
    }
}