package br.com.stackedu.cdd.shared;

import br.com.stackedu.cdd.config.Config;
import br.com.stackedu.cdd.config.DefaultUserDefinitionFactory;

/**
 * Helper to load rules for testing
 * @author albertoluizsouza
 *
 */
public class UserDefinitionForTesting {

	
	public static Config load() {
		return DefaultUserDefinitionFactory.load("cdd.json");
	} 
}
