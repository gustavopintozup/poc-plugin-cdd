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

	private final Configuracoes configuracoes;
	private final ArmazenarMetricas contexto;

	public ImprimirMetricas(Configuracoes configuracoes, ArmazenarMetricas contexto) {
		super();
		this.configuracoes = configuracoes;
		this.contexto = contexto;
	}

	public String console() {
		StringBuilder output = new StringBuilder();

		Iterator<Entry<String, List<ValorICP>>> iter = contexto
				.getDataset().entrySet().iterator();
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

			if (totalICPs >= configuracoes.limite()) {
				output.append(sb.toString());
			}
		}
		return output.toString();
	}

	public String json() {
		try {
			Iterator<Entry<String, List<ValorICP>>> iter = contexto
					.getDataset().entrySet().iterator();
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

				if (totalICPs >= configuracoes.limite()) {
					configMap.put(entry.getKey(), json);
				}
			}

			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(configMap);

		} catch (Exception e) {
			return null;
		}
	}

	private void inicializa_icp_com_zero(Map<String, Object> json) {
		for (Regra regra : configuracoes.regras()) {
			json.put(regra.getName(), 0);
		}
	}

}
