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
	public static Config load(String cddFilePath) {
		try {
			String config = Files.readString(Paths.get(cddFilePath));

			ObjectMapper mapper = new ObjectMapper();
			return new Config(mapper.readValue(config, UserDefinition.class));

		} catch (IOException e) {
			e.printStackTrace();
			throw new PluginCDDException(
					"The 'cdd.json' file was not found in the root dir of the project!");
		}
	}
}
