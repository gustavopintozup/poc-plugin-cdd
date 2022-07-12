package br.com.zup.lms.alunos;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.io.FileInputStream;

public class StaticImport {
    public static void main(String[] args) {
        System.out.println(sqrt(4));
        System.out.println(pow(2, 2));
        System.out.println(abs(6.3));

        FileInputStream bla;
    }
}
