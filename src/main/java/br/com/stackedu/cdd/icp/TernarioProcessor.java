package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.Metricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtConditional;
import spoon.reflect.declaration.CtType;

public class TernarioProcessor extends AbstractProcessor<CtConditional> implements ICP {

    private int total;
    private List<String> values;

    public TernarioProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtConditional element) {
        total++;
        this.values.add(element.getCondition().prettyprint());

        CtType clazz = element.getParent(CtType.class);
        Metricas.salvar(clazz.getQualifiedName(), "if");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}