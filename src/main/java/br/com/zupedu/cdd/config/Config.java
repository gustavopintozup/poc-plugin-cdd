package br.com.zupedu.cdd.config;

import java.util.ArrayList;
import java.util.List;

import br.com.zupedu.cdd.PluginCDDException;

/**
 * Wrapper to facilitate find and usage of some {@link Rule}.
 * 
 * @author gustavopinto
 * @author albertoluizsouza
 *
 */
public class Config {

	private final UserDefinition config;

	public Config(UserDefinition config) {
		this.config = config;
	}

	public Rule get(SupportedRules rule) {
		return this.config.find(rule).orElseThrow(() -> new PluginCDDException(
				"The rule " + rule + " is not being used!"));
	}

	public Double computeCost(SupportedRules rule) {
		return Double.parseDouble(get(rule).getCost());
	}

	public boolean exists(SupportedRules rule) {
		return this.config.find(rule).isPresent();
	}

	public int limit() {
		return config.getLimit();
	}

	public List<SupportedRules> getDefinedRules() {
		List<SupportedRules> defined = new ArrayList<>();

		for (SupportedRules rule : SupportedRules.values()) {
			if(exists(rule)) {
				defined.add(rule);
			}
		}

		return defined;
	}
}
