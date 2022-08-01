package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.ArmazenarMetricas;
import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.config.RegraSuportada;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtVariableReference;

public class AcoplamentoContextualProcessor extends AbstractProcessor<CtVariableReference> implements ICP {

    private List<String> acoplamento;
	private Configuracoes configuracoes;

    public AcoplamentoContextualProcessor(Configuracoes configuracoes) {
        this.configuracoes = configuracoes;
		acoplamento = new ArrayList<>();
    }

    @Override
    public int total() {
        return acoplamento.size();
    }

    @Override
    public List<String> valores() {
        return acoplamento;
    }

    @Override
    public boolean isToBeProcessed(CtVariableReference candidate) {

        if (configuracoes.existe(RegraSuportada.CONTEXT_COUPLING)) {
            if (null != candidate.getType() && null != candidate.getType().getPackage()) {
                String nomeDoPacote = candidate.getType().getPackage().getSimpleName();
                String acoplamentoContextual = configuracoes.get(RegraSuportada.CONTEXT_COUPLING).getParameters();

                if (nomeDoPacote.contains(acoplamentoContextual)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void process(CtVariableReference element) {
        String nomeDaVariavel = element.getSimpleName();
        String nomeDaClasse = element.getType().getSimpleName();
        String nomeDoPacote = element.getType().getPackage().getSimpleName();

        String var = nomeDoPacote + "." + nomeDaClasse + "." + nomeDaVariavel;

        if (!acoplamento.contains(var)) {
            acoplamento.add(var);

            CtType<?> clazz = element.getParent(CtType.class);
            ArmazenarMetricas.salvar(clazz.getQualifiedName(), "CONTEXT_COUPLING");
        }
    }
}