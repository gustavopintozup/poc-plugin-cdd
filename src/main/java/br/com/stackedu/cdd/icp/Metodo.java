package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

public class Metodo implements ICP {

    private final CtType clazz;
    private final Set<CtMethod> methods;

    public Metodo(CtType clazz) {
        this.clazz = clazz;
        this.methods = clazz.getMethods();
    }

    @Override
    public int total() {
        return methods.size();
    }

    @Override
    public List<String> valores() {
        List<String> nomeDosMetodos = new ArrayList<>();

        for (CtMethod method : methods) {
                nomeDosMetodos.add(method.getSimpleName());
        }

        return nomeDosMetodos;
    }
}