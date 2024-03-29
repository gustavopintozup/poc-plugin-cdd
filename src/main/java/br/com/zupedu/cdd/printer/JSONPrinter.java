package br.com.zupedu.cdd.printer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zupedu.cdd.PluginCDDException;
import br.com.zupedu.cdd.config.Config;
import br.com.zupedu.cdd.config.SupportedRules;
import br.com.zupedu.cdd.storage.ICPValue;
import br.com.zupedu.cdd.storage.StoreMetrics;

/**
 * A class that prettyprints in a JSON format
 * 
 * @author gustavopinto
 */
public class JSONPrinter implements PrettyPrinter {

    private final Config config;
    private final StoreMetrics context;
    private final boolean fullReport;

    public JSONPrinter(Config config, StoreMetrics context, boolean fullReport) {
        this.context = context;
        this.config = config;
        this.fullReport = fullReport;
    }

    @Override
    public String print() {
        Iterator<Entry<String, List<ICPValue>>> iter = context
                .getDataset().entrySet().iterator();
        Map<String, Object> configMap = new HashMap<>();

        while (iter.hasNext()) {
            Entry<String, List<ICPValue>> entry = iter.next();
            Map<String, Object> json = new LinkedHashMap<>();
            double totalICPs = 0;

            init_icp_with_zeros(json);

            List<ICPValue> ICPs = entry.getValue();
            for (ICPValue ICP : ICPs) {
                var value = ICP.getValue() * config.computeCost(SupportedRules.valueOf(ICP.getName()));

                json.put(ICP.getName(), value);
                totalICPs += value;
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

    private void init_icp_with_zeros(Map<String, Object> json) {
        for (SupportedRules rule : config.getDefinedRules()) {
            json.put(rule.name(), 0.0);
        }
    }

    protected Config getConfig () {
        return this.config;
    }
}
