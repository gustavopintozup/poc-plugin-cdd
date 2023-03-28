package br.com.zupedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.declaration.CtType;

public class CatchProcessor extends AbstractProcessor<CtCatch> implements ICP {

    private int total;
    private List<String> values;
    private final StoreMetrics context;

    public CatchProcessor(StoreMetrics context) {
        this.context = context;
		this.values = new ArrayList<>();
    }

    @Override
    public void process(CtCatch element) {
        total++;
        this.values.add(element.getBody().toString());

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