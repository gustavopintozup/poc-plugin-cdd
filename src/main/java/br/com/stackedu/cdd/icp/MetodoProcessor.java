package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.ArmazenarMetricas;
import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.config.RegraSuportada;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

public class MetodoProcessor extends AbstractProcessor<CtMethod> implements ICP {

    private final List<String> metodos = new ArrayList<>();
    private int total;
    private final Configuracoes configuracoes;

    public MetodoProcessor(Configuracoes configuracoes) {
		this.configuracoes = configuracoes;
    }

    @Override
    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return metodos;
    }

    @Override
    public boolean isToBeProcessed(CtMethod candidate) {
        if (configuracoes.existe(RegraSuportada.METHODS_AUTOGEN)) {
            //TODO: algum outro?
            if (candidate.getSignature().equals("equals(java.lang.Object)")) {
                return false;
            }

            if (candidate.getSignature().equals("hashCode()")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void process(CtMethod element) {
        CtType clazz = element.getParent(CtType.class);
        ArmazenarMetricas.salvar(clazz.getQualifiedName(), "METHOD");

        this.total++;
        metodos.add(element.getSimpleName());
    }
}