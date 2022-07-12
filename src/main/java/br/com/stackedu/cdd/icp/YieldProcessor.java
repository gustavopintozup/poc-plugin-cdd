package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.Metricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtYieldStatement;
import spoon.reflect.declaration.CtType;

public class YieldProcessor extends AbstractProcessor<CtYieldStatement> implements ICP {

    private int total;
    private List<String> values;

    public YieldProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtYieldStatement element) {
        total++;
        System.out.println(element.prettyprint());
        this.values.add(element.getShortRepresentation());

        CtType clazz = element.getParent(CtType.class);
        Metricas.salvar(clazz.getQualifiedName(), "yield");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}