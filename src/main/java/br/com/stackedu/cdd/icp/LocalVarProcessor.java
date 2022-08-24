package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtType;

public class LocalVarProcessor extends AbstractProcessor<CtField> implements ICP {

    private int total;
    private List<String> values;
    private final StoreMetrics context;

    public LocalVarProcessor(StoreMetrics context) {
        this.context = context;
		this.values = new ArrayList<>();
    }

    @Override
    public void process(CtField element) {
        total++;
        System.out.println(element.getSimpleName());
        this.values.add(element.getSimpleName());

        CtType clazz = element.getParent(CtType.class);
        context.save(clazz.getQualifiedName(), "LOCAL_VARIABLE");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return this.values;
    }
}