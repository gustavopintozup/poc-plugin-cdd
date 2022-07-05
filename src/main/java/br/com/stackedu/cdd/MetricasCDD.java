package br.com.stackedu.cdd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MetricasCDD {

    private static Map<String, Integer> dataset = new HashMap<>();

    public static void store(String classQualifiedName, String ICP) {
        String chave = classQualifiedName + "." + ICP;
        if (dataset.containsKey(chave)) {
			int contadorAtual = dataset.get(chave);
            dataset.replace(chave, contadorAtual, contadorAtual+1);
        } else {
			dataset.put(chave, 1);
		}
    }

    public static String prettyprint() {
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, Integer>> iter = dataset.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Integer> entry = iter.next();
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            if (iter.hasNext()) {
                sb.append(',').append(' ');
            }
        }
        return sb.toString();
    }

}
