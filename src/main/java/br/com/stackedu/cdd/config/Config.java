package br.com.stackedu.cdd.config;

import java.util.List;

import br.com.stackedu.cdd.PluginCDDException;

/**
 * Wrapper to facilitate find and usage of some {@link Rule}. 
 * @author gustavopinto
 * @author albertoluizsouza
 *
 */
public class Config {

	private final UserDefinition config;

	public Config(UserDefinition config) {
		this.config = config;
	}

	public Rule get(SupportedRules regra) {
		return this.config.find(regra).orElseThrow(() -> new PluginCDDException(
				"The rule " + regra + " is not being used!"));
	}

	public boolean exists(SupportedRules regra) {
		return this.config.find(regra).isPresent();
	}

	public int limit() {
		return config.getLimit();
	}

	public List<Rule> rules() {
		return config.getRules();
	}
}
