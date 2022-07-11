package br.com.stackedu.cdd;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Resources {

    public String buscaArquivo(String arquivo) {
        try {
            URL url = getClass().getClassLoader().getResource(arquivo);
            File file = Paths.get(url.toURI()).toFile();
            System.out.println(file.getAbsolutePath());
            return file.getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Arquivo " + arquivo + " não encontrado na pasta resource");
        }
    }

}