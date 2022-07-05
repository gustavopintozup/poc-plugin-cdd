package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.MetricasCDD;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtType;

// O If conta somente o IF ou conta o IF/ELSE/ETC?
public class IfProcessor extends AbstractProcessor<CtIf> implements ICP {

    private int total;
    private List<String> values;

    public IfProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtIf element) {
        total++;
        if (element.getElseStatement() != null) {
            total++;
        }

        this.values.add(element.getCondition().prettyprint());

        CtType clazz = element.getParent(CtType.class);
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