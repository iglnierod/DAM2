package ej101;

import java.io.File;
import java.util.Scanner;

public class InfoFichero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce una dirección: ");
        File file = new File(sc.nextLine());
        if (!file.exists()) {
            System.out.println("La ruta indicada no existe.");
        } else {
            System.out.println("Nombre: " + file.getName());
            System.out.println("Ruta relativa: " + file.getPath());
            System.out.println("Ruta absoluta: " + file.getAbsolutePath());
            System.out.println("Permiso de lectura: " + file.canRead());
            System.out.println("Permiso de escritura: " + file.canWrite());
            System.out.println("Tamaño: " + file.length() + " bytes");
            if (file.isDirectory()) {
                System.out.println("Es un directorio");
            } else {
                System.out.println("Es un archivo");
            }
        }
    }
}
