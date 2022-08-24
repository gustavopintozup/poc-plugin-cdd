package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;

import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtCompilationUnit;
import spoon.reflect.declaration.CtImport;

public class ImportProcessor extends AbstractProcessor<CtCompilationUnit> implements ICP {

    private int total;
    private List<String> values;
    private final StoreMetrics contexto;

    public ImportProcessor(StoreMetrics contexto) {
        this.contexto = contexto;
		this.values = new ArrayList<>();
    }

    @Override
    public void process(CtCompilationUnit element) {
        System.err.println("Seriously??????");
        for (CtImport im : element.getImports()) {
            System.out.println("??????");
            total++;

            this.values.add(im.getShortRepresentation());
            contexto.save(element.getDeclaredTypes().get(0).getQualifiedName(), "import");
        }

    }

    public int total() {
        return total;
    }

    @Override
    public List<String> values() {
        return this.values;
    }
}