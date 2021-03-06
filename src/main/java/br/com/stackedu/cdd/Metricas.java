package br.com.stackedu.cdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.stackedu.cdd.config.Configuracoes;

public class Metricas {

    private static Map<String, List<ValorICP>> dataset = new HashMap<>();

    public static void salvar(String classQualifiedName, String ICP) {
        if (dataset.containsKey(classQualifiedName)) {
            List<ValorICP> valoresAtuais = dataset.get(classQualifiedName);

            List<ValorICP> novosValores = null;
            if (verificaSeICPJaExiste(ICP, valoresAtuais)) {
                novosValores = atualizaValoresAtuais(ICP, valoresAtuais);
            } else {
                novosValores = adicionaNovoValor(ICP, valoresAtuais);
            }

            dataset.replace(classQualifiedName, novosValores);
        } else {
            dataset.put(classQualifiedName, Arrays.asList(new ValorICP(ICP, 1)));
        }
    }

    private static List<ValorICP> adicionaNovoValor(String ICP, List<ValorICP> valoresAtuais) {
        List<ValorICP> v = new ArrayList<>(valoresAtuais);
        v.add(new ValorICP(ICP, 1));
        return v;
    }

    private static List<ValorICP> atualizaValoresAtuais(String ICP, List<ValorICP> valoresAtuais) {
        List<ValorICP> v = new ArrayList<>(valoresAtuais);

        for (int i = 0; i < valoresAtuais.size(); i++) {
            ValorICP atual = valoresAtuais.get(i);

            if (ICP.equals(atual.getICP())) {
                ValorICP novoValor = new ValorICP(ICP, atual.getValor() + 1);
                v.set(i, novoValor);
            }
        }

        return v;
    }

    private static boolean verificaSeICPJaExiste(String ICP, List<ValorICP> valoresAtuais) {
        boolean verificaSeICPJaExiste = false;
        for (ValorICP valorICP : valoresAtuais) {
            if (valorICP.getICP().equals(ICP)) {
                verificaSeICPJaExiste = true;
            }
        }
        return verificaSeICPJaExiste;
    }

    public static String prettyprint() {
        StringBuilder output = new StringBuilder();

        Iterator<Entry<String, List<ValorICP>>> iter = dataset.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, List<ValorICP>> entry = iter.next();

            StringBuilder sb = new StringBuilder();
            sb.append(entry.getKey());
            sb.append('[');

            int totalICPs = 0;

            List<ValorICP> ICPs = entry.getValue();
            for (ValorICP ICP : ICPs) {
                sb.append(ICP.getICP());
                sb.append('=');
                sb.append(ICP.getValor());
                sb.append(',');

                totalICPs += ICP.getValor();
            }

            sb.append("ICP");
            sb.append('=');
            sb.append(totalICPs);
            sb.append("]\n");

            if (totalICPs >= Configuracoes.limite()) {
                output.append(sb.toString());
            }
        }
        return output.toString();
    }

}

class ValorICP {
    private final String ICP;
    private final Integer valor;

    protected ValorICP(String ICP, Integer valor) {
        this.ICP = ICP;
        this.valor = valor;
    }

    protected String getICP() {
        return ICP;
    }

    protected Integer getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "[" + ICP + ", " + valor + "]";
    }
}
