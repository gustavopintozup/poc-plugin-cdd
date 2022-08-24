package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtSwitch;
import spoon.reflect.declaration.CtType;

public class SwitchProcessor extends AbstractProcessor<CtSwitch> implements ICP {

    private int total;
    private List<String> values;
    private final StoreMetrics context;

    public SwitchProcessor(StoreMetrics context) {
        this.context = context;
		this.values = new ArrayList<>();
    }

    @Override
    public void process(CtSwitch element) {
        total = element.getCases().size();
        this.values.add(element.getShortRepresentation());

        CtType clazz = element.getParent(CtType.class);
        context.save(clazz.getQualifiedName(), "SWITCH_STATEMENT");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return this.values;
    }
}