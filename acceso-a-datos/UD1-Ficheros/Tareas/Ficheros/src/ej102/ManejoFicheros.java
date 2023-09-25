package ej102;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ManejoFicheros {
    static void crearFichero(String fichero) {
        File f = new File(fichero);
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Se ha creado el fichero " + fichero);
    }

    static void borrarFichero(String fichero) {
        File f = new File(fichero);
        f.delete();
        System.out.println("Se ha borrado el fichero " + fichero);
    }

    static void crearDirectorio(String ruta) {
        File f = new File(ruta);
        f.mkdir();
        System.out.println("Se ha creado el directorio " + ruta);
    }

    static void borrarDirectorio(String ruta) {
        File f = new File(ruta);
        f.delete();
        System.out.println("Se ha borrado el directorio " + ruta);
    }

    static void listarDirectorio(String ruta) {
        File f = new File(ruta);
        String[] lista = f.list();
        System.out.println("Listado de la ruta " + ruta);
        for (String item : lista) {
            System.out.print(item);
            File file = new File(item);
            if(file.isDirectory())
                System.out.print(" (DIRECTORIO)\n");
            else
                System.out.print(" (ARCHIVO)\n");
        }
    }
}
