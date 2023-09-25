package ej109;

import java.io.*;
import java.nio.file.*;

public class CopiaFichero {
    public static void main(String[] args) {
        Path origen = Paths.get("C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros\\dir\\origen.txt");
        Path destino = Paths.get("C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros\\dir\\destino.txt");
        try {
            FileInputStream in = new FileInputStream(origen.toFile());
            Files.copy(in, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Se ha copiado el texto del archivo origen.txt a destino.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
