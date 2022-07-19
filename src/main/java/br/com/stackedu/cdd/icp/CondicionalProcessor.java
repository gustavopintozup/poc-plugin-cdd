package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.stackedu.cdd.Metricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtType;
import spoon.support.reflect.code.CtBinaryOperatorImpl;

public class CondicionalProcessor extends AbstractProcessor<CtIf> implements ICP {

    private int total;
    private List<String> values;

    public CondicionalProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtIf element) {
        Iterator<CtElement> iter = element.getCondition().asIterable().iterator();
        Set<CtElement> elements = new HashSet<>();

        while (iter.hasNext()) {
            CtElement exp = iter.next();

            if (exp instanceof CtBinaryOperatorImpl) {
                CtBinaryOperatorImpl bin = (CtBinaryOperatorImpl) exp;
                if (bin.getKind().equals(BinaryOperatorKind.AND) ||
                        bin.getKind().equals(BinaryOperatorKind.OR)) {
                    elements.add(bin);
                }
            }
        }

        this.values.add(element.prettyprint());
        CtType clazz = element.getParent(CtType.class);
        Metricas.salvar(clazz.getQualifiedName(), "condicao");
        
        // +1 pois estou contando (escondido) expressoes unarias
        total = elements.size() + 1;
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}