package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.stackedu.cdd.ArmazenarMetricas;
import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.config.RegraSuportada;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.support.reflect.code.CtBinaryOperatorImpl;

public class CondicionalProcessor extends AbstractProcessor<CtIf> implements ICP {

    private int total;
    private List<String> values;

    public CondicionalProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public boolean isToBeProcessed(CtIf candidate) {
        CtMethod<?> parent = candidate.getParent(CtMethod.class);

        if (Configuracoes.existe(RegraSuportada.METHODS_AUTOGEN)) {
            return false;
        }
        /**
         * O parent != null cobre casos em que a declaração de variável usa um if
         */
        if (parent != null) {
            // TODO: algum outro?
            if (parent.getSignature().equals("equals(java.lang.Object)")) {
                return false;
            }

            if (parent.getSignature().equals("hashCode()")) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void process(CtIf element) {
        Iterator<CtElement> iter = element.getCondition().asIterable().iterator();
        Set<CtElement> elements = new HashSet<>();

        while (iter.hasNext()) {
            CtElement exp = iter.next();

            if (exp instanceof CtBinaryOperatorImpl) {
                CtBinaryOperatorImpl<?> bin = (CtBinaryOperatorImpl<?>) exp;
                if (bin.getKind().equals(BinaryOperatorKind.AND) ||
                        bin.getKind().equals(BinaryOperatorKind.OR)) {
                    elements.add(bin);
                }
            }
        }

        this.values.add(element.prettyprint());
        CtType<?> clazz = element.getParent(CtType.class);
        ArmazenarMetricas.salvar(clazz.getQualifiedName(), "CONDITION");

        /**
         * O +1 aqui requer uma atenção. No while acima, eu conto a presença de um
         * operador binário
         * tipo AND ou OR. Se aparecer esse operador, eu coloco o elemento na lista. Mas
         * perceba que
         * dessa forma eu não terei como armazenar toda a condicional. Por exemplo, se a
         * condicional
         * for (a > b || c < d), no laço eu só contarei a existencia do OR (1, nesse
         * caso), e não da
         * quantidade de condicionais (2, nesse caso). Logo, o +1 abaixo é uma pog pra
         * minimizar esse
         * problema. No futuro eu penso numa forma mais elegante de resolver isso.
         */
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