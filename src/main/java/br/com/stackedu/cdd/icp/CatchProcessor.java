package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;

public class CatchProcessor extends AbstractProcessor<CtCatch> implements ICP {

    private int total;
    private List<String> values;

    public CatchProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtCatch element) {
        total++;
        this.values.add(element.getBody().toString());
    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}