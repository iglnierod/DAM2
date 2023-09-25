package ej107;

import java.io.File;
import java.util.Scanner;

public class ManejoFicheroTexto {
    public static void main(String[] args) {
        String rutaDestino = "C:\\Users\\dam2_alu08\\IdeaProjects\\Ficheros\\src\\ej107\\destino.txt";
        FicheroTexto ft = new FicheroTexto(new File(rutaDestino));
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1.- Escribir en el fichero");
            System.out.println("2.- Leer fichero");
            System.out.println("3.- Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.print("Introduce el texto a escribir: ");
                    ft.escribir(sc.nextLine());
                    break;
                case 2:
                    ft.leer();
                    break;
                default:
                    break;
             }
        } while (opcion != 3);
    }
}
