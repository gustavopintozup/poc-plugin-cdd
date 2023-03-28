package br.com.zupedu.cdd.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zupedu.cdd.ExcludeFromJacocoGeneratedReport;

/**
 * Represents a rule defined in the configuration
 * 
 * @author albertoluizsouza
 * @author gustavopinto
 *
 */
public class Rule {

	private String name;
	private String cost;
	private String parameters;

	public Rule(@JsonProperty("name")String name, @JsonProperty("cost") String cost) {
		super();
		this.name = name;
		this.cost = cost;
	}
	
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getName() {
		return this.name;
	}

	public String getParameters() {
		return this.parameters;
	}

	public String getCost() {
		return this.cost;
	}

	public boolean hasTheSameName(SupportedRules rules) {
		return this.name.toUpperCase().equals(rules.name().toUpperCase());
	}

	@Override
	@ExcludeFromJacocoGeneratedReport
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	@ExcludeFromJacocoGeneratedReport
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}