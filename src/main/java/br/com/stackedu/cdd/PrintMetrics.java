package br.com.stackedu.cdd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.config.Rule;

/**
 * 
 * Class for prety pritting the collected CDD metrics
 * 
 * @author gustavopinto
 */
public class PrintMetrics {

	private final Config config;
	private final StoreMetrics context;
	private boolean fullReport;

	public PrintMetrics(Config config, StoreMetrics context) {
		this.config = config;
		this.context = context;
		this.fullReport = false;
	}

	public PrintMetrics(Config config, StoreMetrics context, boolean fullReport) {
		this(config, context);
		this.fullReport = fullReport;
	}

	public String as(String type) {
		if (type.equals("json")) {
			return json();
		} else if (type.equals("txt")) {
			return txt();
		}
		throw new PluginCDDException("This type of output is not supported: " + type);
	}

	public String txt() {
		StringBuilder output = new StringBuilder();

		Iterator<Entry<String, List<ICPValue>>> iter = context
				.getDataset().entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, List<ICPValue>> entry = iter.next();

			StringBuilder sb = new StringBuilder();
			sb.append(entry.getKey());
			sb.append('[');

			int totalICPs = 0;

			List<ICPValue> ICPs = entry.getValue();
			for (ICPValue ICP : ICPs) {
				sb.append(ICP.getICP());
				sb.append('=');
				sb.append(ICP.getValue());
				sb.append(',');

				totalICPs += ICP.getValue();
			}

			sb.append("ICP");
			sb.append('=');
			sb.append(totalICPs);
			sb.append("]\n");

			if (fullReport || totalICPs >= config.limit()) {
				output.append(sb.toString());
			}
		}
		return output.toString();
	}

	public String json() {
		Iterator<Entry<String, List<ICPValue>>> iter = context
				.getDataset().entrySet().iterator();
		Map<String, Object> configMap = new HashMap<>();

		while (iter.hasNext()) {
			Entry<String, List<ICPValue>> entry = iter.next();
			Map<String, Object> json = new HashMap<>();
			int totalICPs = 0;

			initi_icp_with_zeros(json);

			List<ICPValue> ICPs = entry.getValue();
			for (ICPValue ICP : ICPs) {
				json.put(ICP.getICP(), ICP.getValue());

				totalICPs += ICP.getValue();
			}

			json.put("TOTAL", totalICPs);

			if (fullReport || totalICPs >= config.limit()) {
				configMap.put(entry.getKey(), json);
			}
		}

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(configMap);

		} catch (Exception e) {
			throw new PluginCDDException(e.getMessage());
		}
	}

	private void initi_icp_with_zeros(Map<String, Object> json) {
		for (Rule rule : config.rules()) {
			json.put(rule.getName(), 0);
		}
	}
}
