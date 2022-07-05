package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtYieldStatement;

public class YieldProcessor extends AbstractProcessor<CtYieldStatement> implements ICP {

    private int total;
    private List<String> values;

    public YieldProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtYieldStatement element) {
        total++;
        System.out.println(element.prettyprint());
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