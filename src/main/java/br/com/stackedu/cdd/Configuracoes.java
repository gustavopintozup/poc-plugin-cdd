package br.com.stackedu.cdd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stackedu.cdd.Configuracoes.CDDConfig.Rules;

public class Configuracoes {

    private static CDDConfig config = carregar();

    public static int limite() {
        return config.limitOfComplexity;
    }

    public static List<Rules> regras() {
        return config.rules;
    }

    private static CDDConfig carregar() {
        try {
            String config = Files.readString(Paths.get("cdd.json"));

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(config, CDDConfig.class);

        } catch (IOException e) {
           throw new PluginCDDException("O arquivo 'cdd.json' não foi encontrado na raiz do projeto!");
        }
    }

    static class CDDConfig {
        public int limitOfComplexity;
        public List<Rules> rules;

        static class Rules {
            public String name;
            public String cost;
            public List<String> parameters;
        }
    }
}
