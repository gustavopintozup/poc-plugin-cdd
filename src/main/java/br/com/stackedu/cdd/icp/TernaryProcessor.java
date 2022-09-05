package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.config.SupportedRules;
import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtConditional;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

public class TernaryProcessor extends AbstractProcessor<CtConditional> implements ICP {

    private int total;
    private List<String> values = new ArrayList<>();
    private final Config config;
    private final StoreMetrics context;

    public TernaryProcessor(Config config, StoreMetrics context) {
		this.config = config;
		this.context = context;
    }

    @Override
    public boolean isToBeProcessed(CtConditional candidate) {
        CtMethod parent = candidate.getParent(CtMethod.class);

        if (config.exists(SupportedRules.METHODS_AUTOGEN)) {
            // TODO: any other?
            if (parent.getSignature().equals("equals(java.lang.Object)")) {
                return false;
            }

            if (parent.getSignature().equals("hashCode()")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void process(CtConditional element) {
        total++;
        this.values.add(element.getCondition().prettyprint());

        CtType clazz = element.getParent(CtType.class);
        context.save(clazz.getQualifiedName(), "IF_STATEMENT");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return this.values;
    }
}