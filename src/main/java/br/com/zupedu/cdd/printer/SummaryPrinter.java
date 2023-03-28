package br.com.zupedu.cdd.printer;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import br.com.zupedu.cdd.config.Config;
import br.com.zupedu.cdd.storage.ICPValue;
import br.com.zupedu.cdd.storage.StoreMetrics;

public class SummaryPrinter implements PrettyPrinter {

    private final Config config;
    private final StoreMetrics context;
    private final boolean fullReport;

    public SummaryPrinter(Config config, StoreMetrics context, boolean fullReport) {
        this.context = context;
        this.config = config;
        this.fullReport = fullReport;
    }


    @Override
    public String print() {

        StringBuilder output = new StringBuilder();

        var total_classes = 0;
        var total_classes_over_limit = 0 ;

        Iterator<Entry<String, List<ICPValue>>> iter = context
                .getDataset().entrySet().iterator();
        while (iter.hasNext()) {
            
            total_classes++;

            Entry<String, List<ICPValue>> entry = iter.next();
            int totalICPs = 0;

            List<ICPValue> ICPs = entry.getValue();
            for (ICPValue ICP : ICPs) {
                totalICPs += ICP.getValue();
            }

            if (totalICPs >= config.limit()) {
                total_classes_over_limit++;
            }
        }

        output.append("Total Classes: " + total_classes);
        output.append("\n");
        output.append("Total Classes over limit: " + total_classes_over_limit);

        return output.toString();
    }

}