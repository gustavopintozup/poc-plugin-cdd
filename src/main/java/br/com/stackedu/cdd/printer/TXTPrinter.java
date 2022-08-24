package br.com.stackedu.cdd.printer;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.storage.ICPValue;
import br.com.stackedu.cdd.storage.StoreMetrics;

/**
 * A class that prettyprints in a TXT format
 * 
 * @author gustavopinto
 */
public class TXTPrinter implements PrettyPrinter {

    private final Config config;
    private final StoreMetrics context;
    private boolean fullReport;

    public TXTPrinter(Config config, StoreMetrics context) {
        this.config = config;
        this.context = context;
        this.fullReport = false;
    }

    public TXTPrinter(Config config, StoreMetrics context, boolean fullReport) {
        this(config, context);
        this.fullReport = fullReport;
    }

    @Override
    public String print() {
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

}
