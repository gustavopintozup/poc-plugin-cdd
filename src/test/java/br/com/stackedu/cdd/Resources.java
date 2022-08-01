package br.com.stackedu.cdd;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class Resources {

    public String findFile(String file) {
        try {
            URL url = getClass().getClassLoader().getResource(file);
            File foundFile = Paths.get(url.toURI()).toFile();
            return foundFile.getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException("The file " + file + " was not found in the /resources folder");
        }
    }
}