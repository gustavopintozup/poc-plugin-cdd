package br.com.stackedu.cdd.icp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Teste {
    public static void main(String[] args) {
        List<String> elementos = Arrays.asList("oi", "nao", "tchau");

        String oi = elementos.stream()
                .filter(e -> e.equals("oei"))
                .findAny().orElse(null);

        System.out.println(oi);

        List<String> novaLista = elementos.stream()
                .map(e -> e.equals("oi") ? "oie" : e)
                .collect(Collectors.toList());

        System.out.println(novaLista);

    }
}
