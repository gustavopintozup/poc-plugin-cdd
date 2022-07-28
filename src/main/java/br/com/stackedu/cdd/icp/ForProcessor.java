package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.ArmazenarMetricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFor;
import spoon.reflect.declaration.CtType;

public class ForProcessor extends AbstractProcessor<CtFor> implements ICP {

    private int total;
    private List<String> values;

    public ForProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtFor element) {
        total++;
        this.values.add(element.getShortRepresentation());

        CtType<?> clazz = element.getParent(CtType.class);
        ArmazenarMetricas.salvar(clazz.getQualifiedName(), "FOR_STATEMENT");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}