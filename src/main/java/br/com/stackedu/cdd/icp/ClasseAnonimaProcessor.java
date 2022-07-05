
package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

// O If conta somente o IF ou conta o IF/ELSE/ETC?
public class ClasseAnonimaProcessor extends AbstractProcessor<CtClass> implements ICP {

    private int total;
    private List<String> values;

    public ClasseAnonimaProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public boolean isToBeProcessed(CtClass candidate) {
        if (candidate.isAnonymous()) {
            return true;
        }
        return false;
    }

    @Override
    public void process(CtClass element) {
        total++;
        this.values.add(element.getSimpleName());
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}