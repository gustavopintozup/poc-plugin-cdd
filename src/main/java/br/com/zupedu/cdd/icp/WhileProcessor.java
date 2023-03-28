package br.com.zupedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtWhile;
import spoon.reflect.declaration.CtType;

public class WhileProcessor extends AbstractProcessor<CtWhile> implements ICP {

    private int total;
    private List<String> values;
    private final StoreMetrics context;

    public WhileProcessor(StoreMetrics context) {
        this.context = context;
		this.values = new ArrayList<>();
    }

    @Override
    public void process(CtWhile element) {
        total++;
        this.values.add(element.getShortRepresentation());

        CtType clazz = element.getParent(CtType.class);
        context.save(clazz.getQualifiedName(), "WHILE_STATEMENT");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return this.values;
    }
}