package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtYieldStatement;
import spoon.reflect.declaration.CtType;

public class YieldProcessor extends AbstractProcessor<CtYieldStatement> implements ICP {

    private int total;
    private List<String> values;
    private final StoreMetrics context;

    public YieldProcessor(StoreMetrics context) {
        this.context = context;
		this.values = new ArrayList<>();
    }

    @Override
    public void process(CtYieldStatement element) {
        total++;
        System.out.println(element.prettyprint());
        this.values.add(element.getShortRepresentation());

        CtType clazz = element.getParent(CtType.class);
        context.save(clazz.getQualifiedName(), "YIELD_STATEMENT");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return this.values;
    }
}