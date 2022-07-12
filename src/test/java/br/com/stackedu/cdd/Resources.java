package br.com.stackedu.cdd;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class Resources {

    public String buscaArquivo(String arquivo) {
        try {
            URL url = getClass().getClassLoader().getResource(arquivo);
            File file = Paths.get(url.toURI()).toFile();
            return file.getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException("Arquivo " + arquivo + " não encontrado na pasta /resources");
        }
    }

}