package br.com.stackedu.cdd.icp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtType;

public class Anotacao implements ICP {

    private final CtType clazz;
    private int totalAnotacoes;
    private List<String> nomeDasAnotacoes = new ArrayList<>();

    public Anotacao(CtType clazz) {
        this.clazz = clazz;
        calcularAnotacoes();
    }

    private void calcularAnotacoesEmClasses() {
        for (CtAnnotation annotation : clazz.getAnnotations()) {
            totalAnotacoes++;
            nomeDasAnotacoes.add(annotation.getName());
        }
    }

    private void calcularAnotacoesEmAtributos() {
        List<CtField> fields = clazz.getFields();
        for (CtField field : fields) {
            for (CtAnnotation annotation : field.getAnnotations()) {
                totalAnotacoes++;
                nomeDasAnotacoes.add(annotation.getName());
            }
        }
    }

    private void calcularAnotacoesEmMetodos() {
        Set<CtMethod> methods = clazz.getMethods();
        for (CtMethod method : methods) {
            
            for (CtAnnotation annotation : method.getAnnotations()) {
                totalAnotacoes++;
                nomeDasAnotacoes.add(annotation.getName());
            }

            List<CtParameter> parametros = method.getParameters();
            for (CtParameter parametro : parametros) {
                for (CtAnnotation annotation : parametro.getAnnotations()) {
                    totalAnotacoes++;
                    nomeDasAnotacoes.add(annotation.getName());
                }
            }
        }
    }

    private void calcularAnotacoesEmConstrutores() {
        Set<CtConstructor> construtores = ((CtClass) clazz).getConstructors();
        for (CtConstructor construtor : construtores) {
            for (CtAnnotation annotation : construtor.getAnnotations()) {
                totalAnotacoes++;
                nomeDasAnotacoes.add(annotation.getName());
            }

            List<CtParameter> parametros = construtor.getParameters();
            for (CtParameter parametro : parametros) {
                for (CtAnnotation annotation : parametro.getAnnotations()) {
                    totalAnotacoes++;
                    nomeDasAnotacoes.add(annotation.getName());
                }
            }
        }
    }

    private int calcularAnotacoes() {

        calcularAnotacoesEmClasses();
        calcularAnotacoesEmMetodos();
        calcularAnotacoesEmConstrutores();
        calcularAnotacoesEmAtributos();

        return totalAnotacoes;
    }

    @Override
    public int total() {
        return totalAnotacoes;
    }

    @Override
    public List<String> valores() {
        return nomeDasAnotacoes;
    }
}