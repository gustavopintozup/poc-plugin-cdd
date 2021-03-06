package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.Metricas;
import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.config.JSONParser.RegrasDefinidas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

// O If conta somente o IF ou conta o IF/ELSE/ETC?
public class IfProcessor extends AbstractProcessor<CtIf> implements ICP {

    private int total;
    private List<String> values;

    public IfProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public boolean isToBeProcessed(CtIf candidate) {
        CtMethod<?> parent = candidate.getParent(CtMethod.class);

        if (Configuracoes.existe(RegrasDefinidas.METHODS_AUTOGEN)) {
            return false;
        }

        /**
         * O parent != null cobre casos em que a declaração de variável usa um if
         */
        if (parent != null) {
            // TODO: algum outro?
            if (parent.getSignature().equals("equals(java.lang.Object)")) {
                return false;
            } else if (parent.getSignature().equals("hashCode()")) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void process(CtIf element) {
        total++;
        if (element.getElseStatement() != null) {
            total++;
        }

        this.values.add(element.getCondition().prettyprint());

        CtType<?> clazz = element.getParent(CtType.class);
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