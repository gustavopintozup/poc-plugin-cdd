package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.MetricasCDD;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtType;

public class AnotacaoProcessor extends AbstractProcessor<CtAnnotation> implements ICP {

    private int totalAnotacoes;
    private List<String> nomeDasAnotacoes;

    public AnotacaoProcessor() {
        nomeDasAnotacoes = new ArrayList<>();
    }

    @Override
    public int total() {
        return totalAnotacoes;
    }

    @Override
    public List<String> valores() {
        return nomeDasAnotacoes;
    }

    @Override
    public void process(CtAnnotation element) {
        
        this.totalAnotacoes++;
        this.nomeDasAnotacoes.add(element.getName());

        CtType clazz = element.getParent(CtType.class);
        MetricasCDD.store(clazz.getQualifiedName(), "anotação");
    }
}