package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtThrow;
import spoon.reflect.declaration.CtType;

public class ThrowProcessor extends AbstractProcessor<CtThrow> implements ICP {

    private int total;
    private List<String> values;
    private final StoreMetrics context;

    public ThrowProcessor(StoreMetrics context) {
        this.context = context;
		this.values = new ArrayList<>();
    }

    @Override
    public void process(CtThrow element) {
        total++;
        this.values.add(element.prettyprint());

        CtType clazz = element.getParent(CtType.class);
        context.save(clazz.getQualifiedName(), "THROW_STATEMENT");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return this.values;
    }
}