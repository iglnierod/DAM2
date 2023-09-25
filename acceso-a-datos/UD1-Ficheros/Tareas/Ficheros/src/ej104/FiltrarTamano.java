package ej104;

import ej103.FiltrarNombre;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.List;

public class FiltrarTamano implements FilenameFilter {

    public static void main(String[] args) {
        FiltrarTamano ft = new FiltrarTamano();
        ft.filtrar("C:\\Users\\dam2_alu08\\Downloads\\", 0);
    }

    void filtrar(String ruta, float minTamano) {
        File directorio = new File(ruta);
        List<File> ficheros = new LinkedList<>();
        for (String item : directorio.list()) {
            File f = new File(ruta, item);
            if (f.length() >= minTamano) {
                ficheros.add(f);
            }
        }
        for(File f : ficheros) {
            System.out.println(f.getName() + " [" + f.length() + " bytes]");
        }
    }

    @Override
    public boolean accept(File dir, String name) {
        return false;
    }
}
