package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.config.SupportedRules;
import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

public class MethodProcessor extends AbstractProcessor<CtMethod> implements ICP {

    private final List<String> metodos = new ArrayList<>();
    private int total;
    private final Config config;
    private final StoreMetrics context;

    public MethodProcessor(Config config, StoreMetrics context) {
		this.config = config;
		this.context = context;
    }

    @Override
    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return metodos;
    }

    @Override
    public boolean isToBeProcessed(CtMethod candidate) {
        if (config.exists(SupportedRules.METHODS_AUTOGEN)) {
            //TODO: any other?
            if (candidate.getSignature().equals("equals(java.lang.Object)")) {
                System.out.println("equals");
                return false;
            }

            if (candidate.getSignature().equals("hashCode()")) {
                System.out.println("hashcode");
                return false;
            }
        }
        return true;
    }

    @Override
    public void process(CtMethod element) {
        CtType clazz = element.getParent(CtType.class);
        context.save(clazz.getQualifiedName(), "METHOD");

        this.total++;
        metodos.add(element.getSimpleName());
    }
}