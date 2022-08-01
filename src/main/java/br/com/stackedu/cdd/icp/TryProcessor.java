package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtTry;
import spoon.reflect.declaration.CtType;

public class TryProcessor extends AbstractProcessor<CtTry> implements ICP {

    private int total;
    private List<String> values;
    private final StoreMetrics context;

    public TryProcessor(StoreMetrics context) {
        this.context = context;
		this.values = new ArrayList<>();
    }

    @Override
    public void process(CtTry element) {
        total++;
        this.values.add(element.getBody().toString());

        if (element.getFinalizer() != null) {
            total++;
        }

        CtType clazz = element.getParent(CtType.class);
        context.save(clazz.getQualifiedName(), "TRY_CATCH_STATEMENT");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return this.values;
    }
}