package br.com.stackedu.cdd.config;

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
	private int limite;
	private List<Regra> regras;

	public UserDefinition(@JsonProperty("limite") int limite, @JsonProperty("regras") List<Regra> regras) {
		super();
		this.limite = limite;
		this.regras = regras;
	}

	
	public int getLimite() {
		return limite;
	}

	public List<Regra> getRegras() {
		return regras;
	}

	public Optional<Regra> find(RegraSuportada regra) {
		return this.regras.stream()
				.filter(regraAtual -> regraAtual.hasTheSameName(regra))
				.findFirst();
	}
}