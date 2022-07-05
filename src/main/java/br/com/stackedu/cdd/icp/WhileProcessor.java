package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtWhile;

public class WhileProcessor extends AbstractProcessor<CtWhile> implements ICP {

    private int total;
    private List<String> values;

    public WhileProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtWhile element) {
        total++;
        this.values.add(element.getShortRepresentation());
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}