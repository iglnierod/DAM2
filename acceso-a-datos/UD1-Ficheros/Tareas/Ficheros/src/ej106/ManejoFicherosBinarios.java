package ej106;

import java.io.File;

public class ManejoFicherosBinarios {
    public static void main(String[] args) {
        String rutaOrigen = "C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros\\src\\ej106\\origen.bin";
        String rutaDestino = "C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros\\src\\ej106\\destino.bin";
        FicheroBinario fb = new FicheroBinario(new File(rutaOrigen));
        fb.escribir("ESTE ES EL TEXTO DE ORIGEN.");
        fb.leer();

        FicheroBinario destino = new FicheroBinario(new File(rutaDestino));
        fb.copiar(destino);
    }
}
