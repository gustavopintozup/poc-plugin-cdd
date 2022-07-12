package br.com.stackedu.cdd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Metricas {

    private static Map<String, Integer> dataset = new HashMap<>();

    public static void salvar(String classQualifiedName, String ICP) {
        String chave = classQualifiedName + "." + ICP;
        if (dataset.containsKey(chave)) {
            int contadorAtual = dataset.get(chave);
            dataset.replace(chave, contadorAtual, contadorAtual + 1);
        } else {
            dataset.put(chave, 1);
        }
    }

    public static String prettyprint() {
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, Integer>> iter = dataset.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Integer> entry = iter.next();

            int valorDaMetrica = entry.getValue();
            if (valorDaMetrica >= Configuracoes.limite()) {
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(valorDaMetrica);
                if (iter.hasNext()) {
                    sb.append('\n');
                }
            }
        }
        return sb.toString();
    }

}
