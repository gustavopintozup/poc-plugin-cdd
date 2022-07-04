package br.com.stackedu.cdd.icp;

import java.util.Set;

import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

public class Metodo implements ICP {

    @Override
    public int calcular(CtType clazz) {
        Set<CtMethod> methods = clazz.getMethods();
        return methods.size();
    }
}