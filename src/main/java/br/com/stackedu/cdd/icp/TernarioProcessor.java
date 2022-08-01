package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.ArmazenarMetricas;
import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.config.RegraSuportada;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtConditional;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

public class TernarioProcessor extends AbstractProcessor<CtConditional> implements ICP {

    private int total;
    private List<String> values = new ArrayList<>();
    private final Configuracoes configuracoes;

    public TernarioProcessor(Configuracoes configuracoes) {
		this.configuracoes = configuracoes;
    }

    @Override
    public boolean isToBeProcessed(CtConditional candidate) {
        CtMethod parent = candidate.getParent(CtMethod.class);

        if (configuracoes.existe(RegraSuportada.METHODS_AUTOGEN)) {
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
    public void process(CtConditional element) {
        total++;
        this.values.add(element.getCondition().prettyprint());

        CtType clazz = element.getParent(CtType.class);
        ArmazenarMetricas.salvar(clazz.getQualifiedName(), "if");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}