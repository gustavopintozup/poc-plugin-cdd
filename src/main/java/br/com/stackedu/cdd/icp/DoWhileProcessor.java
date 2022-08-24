package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtDo;
import spoon.reflect.declaration.CtType;

public class DoWhileProcessor extends AbstractProcessor<CtDo> implements ICP {

    private int total;
    private List<String> values;
    private final StoreMetrics context;

    public DoWhileProcessor(StoreMetrics context) {
        this.context = context;
		this.values = new ArrayList<>();
    }

    @Override
    public void process(CtDo element) {
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