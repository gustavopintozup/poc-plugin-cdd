package br.com.stackedu.cdd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stackedu.cdd.config.Configuracoes;
import br.com.stackedu.cdd.config.Regra;

public class ImprimirMetricas {

    public static String console() {
        StringBuilder output = new StringBuilder();

        Iterator<Entry<String, List<ValorICP>>> iter = ArmazenarMetricas.getDataset().entrySet().iterator();
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

    public static String json() {
        try {
            Iterator<Entry<String, List<ValorICP>>> iter = ArmazenarMetricas.getDataset().entrySet().iterator();
            Map<String, Object> configMap = new HashMap<>();

            while (iter.hasNext()) {
                Entry<String, List<ValorICP>> entry = iter.next();
                Map<String, Object> json = new HashMap<>();
                int totalICPs = 0;

                inicializa_icp_com_zero(json);

                List<ValorICP> ICPs = entry.getValue();
                for (ValorICP ICP : ICPs) {
                    json.put(ICP.getICP(), ICP.getValor());

                    totalICPs += ICP.getValor();
                }

                json.put("TOTAL", totalICPs);

                if (totalICPs >= Configuracoes.limite()) {
                    configMap.put(entry.getKey(), json);
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(configMap);

        } catch (Exception e) {
            return null;
        }
    }

    private static void inicializa_icp_com_zero(Map<String, Object> json) {
        for (Regra regra : Configuracoes.regras()) {
            json.put(regra.getName(), 0);
        }
    }

}
