package br.com.zupedu.cdd.shared;

import br.com.zupedu.cdd.config.Config;
import br.com.zupedu.cdd.config.DefaultUserDefinitionFactory;

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
