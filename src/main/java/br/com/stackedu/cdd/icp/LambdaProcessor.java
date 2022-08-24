package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtLambda;
import spoon.reflect.declaration.CtType;

public class LambdaProcessor extends AbstractProcessor<CtLambda> implements ICP {

    private int total;
    private List<String> stms;
    private final StoreMetrics context;

    public LambdaProcessor(StoreMetrics context) {
        this.context = context;
		stms = new ArrayList<>();
    }

    @Override
    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return stms;
    }

    @Override
    public void process(CtLambda element) {
        this.total++;
        this.stms.add(element.prettyprint());

        CtType type = element.getParent(CtType.class);
        context.save(type.getQualifiedName(), "LAMBDA_EXPRESSION");
    }
}