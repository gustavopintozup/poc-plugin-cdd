package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.Metricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtWhile;
import spoon.reflect.declaration.CtType;

public class WhileProcessor extends AbstractProcessor<CtWhile> implements ICP {

    private int total;
    private List<String> values;

    public WhileProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtWhile element) {
        total++;
        this.values.add(element.getShortRepresentation());

        CtType clazz = element.getParent(CtType.class);
        Metricas.salvar(clazz.getQualifiedName(), "while");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}