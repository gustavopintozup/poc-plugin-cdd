package br.com.stackedu.cdd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stackedu.cdd.ArquivoDeConfig.CDDConfig.Rules;

public class ArquivoDeConfig {

    private static CDDConfig config;

    static {
        config = carregar();
    }

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
            e.printStackTrace();
            return null;
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
