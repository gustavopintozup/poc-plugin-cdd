package br.com.zupedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtType;

public class AnnotationProcessor extends AbstractProcessor<CtAnnotation> implements ICP {

    private int total;
    private List<String> annotations;
    private final StoreMetrics context;

    public AnnotationProcessor(StoreMetrics context) {
        this.context = context;
		annotations = new ArrayList<>();
    }

    @Override
    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return annotations;
    }

    @Override
    public void process(CtAnnotation element) {
        this.total++;
        this.annotations.add(element.getName());

        CtType clazz = element.getParent(CtType.class);
        context.save(clazz.getQualifiedName(), "ANNOTATION");
    }
}