package ej103;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.List;

public class FiltrarNombre implements FilenameFilter {
    public static void main(String[] args) {
        FiltrarNombre fn = new FiltrarNombre();
        fn.filtrar("C:\\Users\\dam2_alu08\\Downloads\\", ".pdf");
    }

    void filtrar(String ruta, String extension) {
        File directorio = new File(ruta);
        List<File> ficheros = new LinkedList<>();
        for (String item : directorio.list()) {
            File f = new File(item);
            if (accept(f, extension)) {
                ficheros.add(f);
            }
        }
        System.out.println(ficheros);
    }

    @Override
    public boolean accept(File dir, String name) {
        return dir.getName().endsWith(name);
    }
}
