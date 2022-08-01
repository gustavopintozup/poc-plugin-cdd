package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.ArmazenarMetricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.declaration.CtType;

public class CatchProcessor extends AbstractProcessor<CtCatch> implements ICP {

    private int total;
    private List<String> values;
    private final ArmazenarMetricas contexto;

    public CatchProcessor(ArmazenarMetricas contexto) {
        this.contexto = contexto;
		this.values = new ArrayList<>();
    }

    @Override
    public void process(CtCatch element) {
        total++;
        this.values.add(element.getBody().toString());

        CtType clazz = element.getParent(CtType.class);
        contexto.salvar(clazz.getQualifiedName(), "TRY_CATCH_STATEMENT");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}