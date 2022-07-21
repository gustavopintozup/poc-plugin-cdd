package br.com.stackedu.cdd.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stackedu.cdd.PluginCDDException;
import br.com.stackedu.cdd.config.JSONParser.Regras;
import br.com.stackedu.cdd.config.JSONParser.RegrasDefinidas;

public class Configuracoes {

    private static JSONParser config = carregar();

    public static Regras get(RegrasDefinidas regra) {
        for (Regras rule : regras()) {
            if (rule.getName().equals(regra.toString())) {
                return rule;
            }
        }
        throw new PluginCDDException("A regra " + regra + " não está sendo utilizada!");
    }

    public static boolean existe(RegrasDefinidas regra) {
        for (Regras rule : regras()) {
            if (rule.getName().equals(regra.toString())) {
                return true;
            }
        }
        return false;
    }


    public static int limite() {
        return config.getLimite();
    }

    private static List<Regras> regras() {
        return config.getRegras();
    }

    private static JSONParser carregar() {
        try {
            String config = Files.readString(Paths.get("cdd.json"));

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(config, JSONParser.class);

        } catch (IOException e) {
            e.printStackTrace();
            throw new PluginCDDException("O arquivo 'cdd.json' não foi encontrado na raiz do projeto!");
        }
    }

}
