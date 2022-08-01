
package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtType;

public class AnonymousClassProcessor extends AbstractProcessor<CtClass> implements ICP {

    private int total;
    private List<String> values;
    private final StoreMetrics context;

    public AnonymousClassProcessor(StoreMetrics context) {
        this.context = context;
		this.values = new ArrayList<>();
    }

    @Override
    public boolean isToBeProcessed(CtClass candidate) {
        if (candidate.isAnonymous()) {
            return true;
        }
        return false;
    }

    @Override
    public void process(CtClass element) {
        total++;
        this.values.add(element.getSimpleName());

        CtType clazz = element.getParent(CtType.class);
        context.save(clazz.getQualifiedName(), "ANONYMOUS_CLASS");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return this.values;
    }
}