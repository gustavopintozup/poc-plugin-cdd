package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtSuperAccess;
import spoon.reflect.declaration.CtType;

public class SuperProcessor extends AbstractProcessor<CtSuperAccess> implements ICP {

    private int total;
    private List<String> values;
    private final StoreMetrics context;

    public SuperProcessor(StoreMetrics context) {
        this.context = context;
		this.values = new ArrayList<>();
    }

    @Override
    public void process(CtSuperAccess element) {
        total++;
        this.values.add(element.getShortRepresentation());

        CtType clazz = element.getParent(CtType.class);
        context.save(clazz.getQualifiedName(), "SUPER_EXPRESSION");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return this.values;
    }
}