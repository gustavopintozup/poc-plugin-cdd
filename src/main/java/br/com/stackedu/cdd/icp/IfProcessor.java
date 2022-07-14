package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.Metricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtType;

// O If conta somente o IF ou conta o IF/ELSE/ETC?
public class IfProcessor extends AbstractProcessor<CtIf> implements ICP {

    private int total;
    private List<String> values;

    public IfProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtIf element) {
        total++;
        //TODO: escrever um teste pra ver como se comporta com else if
        if (element.getElseStatement() != null) {
            total++;
        }

        this.values.add(element.getCondition().prettyprint());

        CtType clazz = element.getParent(CtType.class);
        Metricas.salvar(clazz.getQualifiedName(), "if");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}