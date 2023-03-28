package br.com.zupedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.zupedu.cdd.config.Config;
import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

public class MethodSizeProcessor extends AbstractProcessor<CtMethod> implements ICP {

    private final List<String> metodos = new ArrayList<>();
    private int total;
    private final Config config;
    private final StoreMetrics context;

    public MethodSizeProcessor(Config config, StoreMetrics context) {
        this.config = config;
        this.context = context;
    }

    @Override
    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return metodos;
    }

    @Override
    public boolean isToBeProcessed(CtMethod candidate) {
        // TODO: any other?
        if (candidate.getSignature().equals("equals(java.lang.Object)")) {
            // System.out.println("equals");
            return false;
        }

        if (candidate.getSignature().equals("hashCode()")) {
            // System.out.println("hashcode");
            return false;
        }

        if (candidate.getBody() != null) {
            if (candidate.getSignature().startsWith("get") && countLines(candidate.getBody().prettyprint()) == 3) {
                // System.out.println("get");
                return false;
            }

            if (candidate.getSignature().startsWith("set") && countLines(candidate.getBody().prettyprint()) == 3) {
                // System.out.println("set");
                return false;
            }
        }

        // TODO: what to do with this class?
        // I created this class only for an experiment in the ICSE SEIP paper. We dont use this 
        // class for the traditional CDD analysis. We have to think about what to do with it.
        return false;
    }

    private static int countLines(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        return lines.length;
    }

    @Override
    public void process(CtMethod element) {
        CtType clazz = element.getParent(CtType.class);

        if (element.getBody() != null) {
            System.out.println(clazz.getQualifiedName() + ", \"" + element.getSignature() + "\", "
                   + countLines(element.getBody().prettyprint()));
        }

        // CtType clazz = element.getParent(CtType.class);
        // context.save(clazz.getQualifiedName(), "METHOD_SIZE");

        // this.total = countLines(element.getBody().prettyprint());
        // metodos.add(element.getSignature());
    }
}