package br.com.stackedu.cdd.printer;

import br.com.stackedu.cdd.Miner.FormatOption.Format;
import br.com.stackedu.cdd.PluginCDDException;
import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.storage.StoreMetrics;

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

	public PrettyPrinter as(Format type) {
		switch (type) {
			case TXT:
				return new TXTPrinter(config, context, fullReport);
			case JSON:
				return new JSONPrinter(config, context, fullReport);
			case HTML:
				return new HTMLPrinter(new JSONPrinter(config, context, fullReport));
			case SUMMARY:
				return new SummaryPrinter(config, context, fullReport);
			default:
				throw new PluginCDDException("This type of output is not supported: " + type); // in theory this should
																								// never happen
		}
	}
	/**
	 * Default printer uses JSON prettyprinter
	 * @return String
	 */
	public String print() {
		return new JSONPrinter(config, context, fullReport).print();
	}

}
