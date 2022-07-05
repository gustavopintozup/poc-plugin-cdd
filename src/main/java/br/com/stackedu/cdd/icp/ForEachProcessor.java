package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtForEach;

public class ForEachProcessor extends AbstractProcessor<CtForEach> implements ICP {

    private int total;
    private List<String> values;

    public ForEachProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtForEach element) {
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