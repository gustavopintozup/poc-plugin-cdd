package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.Metricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtForEach;
import spoon.reflect.declaration.CtType;

public class ForEachProcessor extends AbstractProcessor<CtForEach> implements ICP {

    private int total;
    private List<String> values;

    public ForEachProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtForEach element) {
        total++;
        this.values.add(element.getShortRepresentation());

        CtType clazz = element.getParent(CtType.class);
        Metricas.salvar(clazz.getQualifiedName(), "forEach");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}