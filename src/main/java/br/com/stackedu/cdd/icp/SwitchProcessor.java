package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.MetricasCDD;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtSwitch;
import spoon.reflect.declaration.CtClass;

public class SwitchProcessor extends AbstractProcessor<CtSwitch> implements ICP {

    private int total;
    private List<String> values;

    public SwitchProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtSwitch element) {
        //confirmar se a contagem do switch é feita pelo numero de cases
        total = element.getCases().size();
        this.values.add(element.getShortRepresentation());

        CtClass clazz = element.getParent(CtClass.class);
        MetricasCDD.store(clazz.getQualifiedName(), "switch");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}