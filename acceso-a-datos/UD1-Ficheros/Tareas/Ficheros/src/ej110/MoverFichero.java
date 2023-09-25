package ej110;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoverFichero {
    public static void main(String[] args) {
        Path origen = Paths.get("C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros\\src\\ej110\\dir\\origen.txt");
        Path destino = Paths.get("C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros\\src\\ej110\\dir\\subdirectorio\\destino-sub.txt");
        try {
            Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Se ha movido el archivo " + origen.toFile() + " a " + destino.toFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
