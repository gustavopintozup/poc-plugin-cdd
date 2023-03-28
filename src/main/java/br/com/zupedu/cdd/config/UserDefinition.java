package br.com.zupedu.cdd.config;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Represents the icps and limits defined by who will use the CDD
 * 
 * @author gustavopinto
 * @author albertoluizsouza
 *
 */
public class UserDefinition {
	private int limit;
	private List<Rule> rules;

	public UserDefinition(@JsonProperty("limit") int limit, @JsonProperty("rules") List<Rule> rules) {
		super();
		this.limit = limit;
		this.rules = rules;
	}

	public int getLimit() {
		return limit;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public Optional<Rule> find(SupportedRules rule) {
		return this.rules.stream()
				.filter(actual -> actual.hasTheSameName(rule))
				.findFirst();
	}
}