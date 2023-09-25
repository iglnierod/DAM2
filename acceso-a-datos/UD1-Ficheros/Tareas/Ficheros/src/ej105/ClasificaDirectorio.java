package ej105;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ClasificaDirectorio {
    public void segunExtension(String dir) {
        File directorio = new File(dir);
        if (!directorio.isDirectory()) {
            System.err.println("La dirección introducida no es un directorio.");
            return;
        }

        File[] archivos = directorio.listFiles();
        if (archivos == null) {
            System.err.println("El directorio está vacío.");
            return;
        }

        Map<String, List<File>> extensiones = new HashMap<>();

        for (File archivo : archivos) {
            String nombre = archivo.getName();
            int puntoIndex = nombre.lastIndexOf(".");
            if (puntoIndex != -1) {
                String extension = nombre.substring(puntoIndex + 1).toUpperCase();
                extensiones.computeIfAbsent(extension, k -> new ArrayList<>()).add(archivo);
            }
        }

        for (Map.Entry<String, List<File>> entry : extensiones.entrySet()) {
            String extension = entry.getKey();
            List<File> archivosConExtension = entry.getValue();

            File subdirectorio = new File(directorio, extension);
            if (!subdirectorio.exists()) {
                if (subdirectorio.mkdir()) {
                    System.out.println("Directorio creado: " + subdirectorio.getAbsolutePath());
                } else {
                    System.err.println("No se pudo crear el directorio: " + subdirectorio.getAbsolutePath());
                    continue;
                }
            }

            for (File archivo : archivosConExtension) {
                Path origen = archivo.toPath();
                Path destino = subdirectorio.toPath().resolve(archivo.getName());
                try {
                    Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Movido: " + archivo.getAbsolutePath() + " a " + destino.toAbsolutePath());
                } catch (IOException e) {
                    System.err.println("Error al mover " + archivo.getAbsolutePath() + ": " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        ClasificaDirectorio cd = new ClasificaDirectorio();
        cd.segunExtension("C:\\Users\\dam2_alu08\\Desktop\\test\\");
    }
}
