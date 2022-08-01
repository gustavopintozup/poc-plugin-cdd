package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.ArmazenarMetricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtLambda;
import spoon.reflect.declaration.CtType;

public class LambdaProcessor extends AbstractProcessor<CtLambda> implements ICP {

    private int total;
    private List<String> stms;
    private final ArmazenarMetricas contexto;

    public LambdaProcessor(ArmazenarMetricas contexto) {
        this.contexto = contexto;
		stms = new ArrayList<>();
    }

    @Override
    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return stms;
    }

    @Override
    public void process(CtLambda element) {
        this.total++;
        this.stms.add(element.prettyprint());

        CtType type = element.getParent(CtType.class);
        contexto.salvar(type.getQualifiedName(), "LAMBDA_EXPRESSION");
    }
}