package br.com.stackedu.cdd;

import br.com.stackedu.cdd.icp.Metodo;
import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtType;

public class IdentificandoClassesParaParsing {

    public static void main(String[] args) {
        Launcher spoon = new Launcher();

        String target = "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/alunos/Aluno.java";

        spoon.addInputResource(target);
        spoon.buildModel();

        CtModel model = spoon.getModel();
        for (CtType clazz : model.getAllTypes()) {
            int metodos = new Metodo().calcular(clazz);
            System.out.println("Total métodos: " + metodos );
        }
    }
}