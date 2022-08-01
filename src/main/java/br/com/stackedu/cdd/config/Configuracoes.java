package br.com.stackedu.cdd.config;

import java.util.List;

import br.com.stackedu.cdd.PluginCDDException;

/**
 * Wrapper to facilitate find and usage of some {@link Regra}. 
 * @author gustavopinto
 * @author albertoluizsouza
 *
 */
public class Configuracoes {

	private final UserDefinition config;

	public Configuracoes(UserDefinition config) {
		this.config = config;
	}

	public Regra get(RegraSuportada regra) {
		return this.config.find(regra).orElseThrow(() -> new PluginCDDException(
				"A regra " + regra + " não está sendo utilizada!"));

	}

	public boolean existe(RegraSuportada regra) {
		return this.config.find(regra).isPresent();
	}

	public int limite() {
		return config.getLimite();
	}

	public List<Regra> regras() {
		return config.getRegras();
	}
}
