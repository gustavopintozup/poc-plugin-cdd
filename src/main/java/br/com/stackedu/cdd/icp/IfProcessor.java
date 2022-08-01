package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.ArmazenarMetricas;
import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.config.RegraSuportada;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

// O If conta somente o IF ou conta o IF/ELSE/ETC?
public class IfProcessor extends AbstractProcessor<CtIf> implements ICP {

    private int total;
    private List<String> values;
    private final Configuracoes configuracoes;
    private final ArmazenarMetricas contexto;

    public IfProcessor(Configuracoes configuracoes, ArmazenarMetricas contexto) {
        this.configuracoes = configuracoes;
		this.values = new ArrayList<>();
		this.contexto = contexto;
    }

    @Override
    public boolean isToBeProcessed(CtIf candidate) {
        CtMethod<?> parent = candidate.getParent(CtMethod.class);

        if (configuracoes.existe(RegraSuportada.METHODS_AUTOGEN)) {
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
        contexto.salvar(clazz.getQualifiedName(), "IF_STATEMENT");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}