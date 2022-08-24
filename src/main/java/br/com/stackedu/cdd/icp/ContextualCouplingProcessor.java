package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.config.SupportedRules;
import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtVariableReference;

public class ContextualCouplingProcessor extends AbstractProcessor<CtVariableReference> implements ICP {

    private List<String> coupling;
	private Config config;
	private final StoreMetrics context;

    public ContextualCouplingProcessor(Config config, StoreMetrics context) {
        this.context = context;
		this.config = config;
		coupling = new ArrayList<>();
    }

    @Override
    public int total() {
        return coupling.size();
    }

    @Override
    public List<String> values() {
        return coupling;
    }

    @Override
    public boolean isToBeProcessed(CtVariableReference candidate) {

        if (config.exists(SupportedRules.CONTEXT_COUPLING)) {
            if (null != candidate.getType() && null != candidate.getType().getPackage()) {
                String packageName = candidate.getType().getPackage().getSimpleName();
                String contextualCoupling = config.get(SupportedRules.CONTEXT_COUPLING).getParameters();

                if (packageName.contains(contextualCoupling)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void process(CtVariableReference element) {
        String varName = element.getSimpleName();
        String className = element.getType().getSimpleName();
        String packageName = element.getType().getPackage().getSimpleName();

        String var = packageName + "." + className + "." + varName;

        if (!coupling.contains(var)) {
            coupling.add(var);

            CtType<?> clazz = element.getParent(CtType.class);
            context.save(clazz.getQualifiedName(), "CONTEXT_COUPLING");
        }
    }
}