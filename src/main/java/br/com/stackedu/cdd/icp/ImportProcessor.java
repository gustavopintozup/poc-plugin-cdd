package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.ArmazenarMetricas;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtCompilationUnit;
import spoon.reflect.declaration.CtImport;

public class ImportProcessor extends AbstractProcessor<CtCompilationUnit> implements ICP {

    private int total;
    private List<String> values;

    public ImportProcessor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void process(CtCompilationUnit element) {
        System.err.println("Seriously??????");
        for (CtImport im : element.getImports()) {
            System.out.println("??????");
            total++;

            this.values.add(im.getShortRepresentation());
            ArmazenarMetricas.salvar(element.getDeclaredTypes().get(0).getQualifiedName(), "import");
        }

    }

    public int total() {
        return total;
    }

    @Override
    public List<String> valores() {
        return this.values;
    }
}