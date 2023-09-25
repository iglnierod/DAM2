package ej102;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ManejoFicheros.crearFichero("test.txt");
        ManejoFicheros.borrarFichero("test.txt");
        ManejoFicheros.crearDirectorio("archivos");
        ManejoFicheros.borrarDirectorio("archivos");
        ManejoFicheros.listarDirectorio("C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros");
    }
}
