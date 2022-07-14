package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.stackedu.cdd.Metricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtUnaryOperator;
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

        CtExpression<Boolean> condition = element.getCondition();
        Iterator<CtElement> iter = condition.asIterable().iterator();

        total++;
        while (iter.hasNext()) {
            CtElement exp = iter.next();

            if (exp instanceof CtBinaryOperatorImpl) {
                CtBinaryOperatorImpl bin = (CtBinaryOperatorImpl) exp;

                if (bin.getKind().equals(BinaryOperatorKind.AND) || bin.getKind().equals(BinaryOperatorKind.OR)) {
                    total++;
                    this.values.add(bin.prettyprint());

                    CtType clazz = element.getParent(CtType.class);
                    Metricas.salvar(clazz.getQualifiedName(), "condicao-bin");
                }

            } else if (exp instanceof CtUnaryOperator) {
                CtUnaryOperator un = (CtUnaryOperator) exp;
                total++;

                this.values.add(un.prettyprint());

                CtType clazz = element.getParent(CtType.class);
                Metricas.salvar(clazz.getQualifiedName(), "condicao-un");
            }
        }
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}