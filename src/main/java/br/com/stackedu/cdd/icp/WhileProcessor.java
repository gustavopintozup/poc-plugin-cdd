package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.ArmazenarMetricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtWhile;
import spoon.reflect.declaration.CtType;

public class WhileProcessor extends AbstractProcessor<CtWhile> implements ICP {

    private int total;
    private List<String> values;
    private final ArmazenarMetricas contexto;

    public WhileProcessor(ArmazenarMetricas contexto) {
        this.contexto = contexto;
		this.values = new ArrayList<>();
    }

    @Override
    public void process(CtWhile element) {
        total++;
        this.values.add(element.getShortRepresentation());

        CtType clazz = element.getParent(CtType.class);
        contexto.salvar(clazz.getQualifiedName(), "WHILE_STATEMENT");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}