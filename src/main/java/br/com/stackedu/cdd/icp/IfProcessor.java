package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.config.SupportedRules;
import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

public class IfProcessor extends AbstractProcessor<CtIf> implements ICP {

    private int total;
    private List<String> values;
    private final Config config;
    private final StoreMetrics contexto;

    public IfProcessor(Config configuracoes, StoreMetrics contexto) {
        this.config = configuracoes;
		this.values = new ArrayList<>();
		this.contexto = contexto;
    }

    @Override
    public boolean isToBeProcessed(CtIf candidate) {
        CtMethod<?> parent = candidate.getParent(CtMethod.class);

        if (config.exists(SupportedRules.METHODS_AUTOGEN)) {
            return false;
        }

        /**
         * The parent != null cover cases in whihch the var declarations uses an if
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
        contexto.save(clazz.getQualifiedName(), "IF_STATEMENT");
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return this.values;
    }
}