package br.com.stackedu.cdd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LerArquivoDeConfig {

    public static void main(String[] args) {
        try {
            String config = Files.readString(Paths.get("cdd.json"));

            ObjectMapper mapper = new ObjectMapper();
            CDDConfig c = mapper.readValue(config, CDDConfig.class);

            System.out.println(c);

        } catch (IOException e) {
            e.printStackTrace();
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
