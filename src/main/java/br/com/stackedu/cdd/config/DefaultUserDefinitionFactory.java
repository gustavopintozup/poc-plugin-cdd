package br.com.stackedu.cdd.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stackedu.cdd.PluginCDDException;

/**
 * 
 * Loads a {@link UserDefinition} from a file cdd.json
 * 
 * @author albertoluizsouza
 *
 */
public class DefaultUserDefinitionFactory {

	/**
	 * 
	 * @param cddFilePath path to json file with list of {@link UserDefinition}
	 * @return
	 */
	public static Configuracoes load(String cddFilePath) {
		try {
			String config = Files.readString(Paths.get(cddFilePath));

			ObjectMapper mapper = new ObjectMapper();
			return new Configuracoes(mapper.readValue(config, UserDefinition.class));

		} catch (IOException e) {
			e.printStackTrace();
			throw new PluginCDDException(
					"O arquivo 'cdd.json' não foi encontrado na raiz do projeto!");
		}
	}
}
