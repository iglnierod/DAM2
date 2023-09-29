import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ComprobadorFichero {
    private File archivo;
    private Map<String, byte[]> archivosSoportados;

    public ComprobadorFichero() {
        archivo = new File("");
        archivosSoportados = new HashMap<>();
        setArchivosSoportados();
    }

    private void setArchivosSoportados() {
        archivosSoportados.put("pdf", "%PDF".getBytes());
        archivosSoportados.put("zip", "PK\u0003".getBytes());
        archivosSoportados.put("7z", "7z¼".getBytes());
        archivosSoportados.put("rar", "Rar!".getBytes());
        archivosSoportados.put("png", "‰PNG".getBytes());
    }

    public Set<String> getArchivosSoportados() {
        return archivosSoportados.keySet();
    }

    public File getArchivo() {
        return archivo;
    }

    public void pedirArchivo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta a un archivo, archivos soportados: " + getArchivosSoportados());
        System.out.print("ruta: ");
        setArchivo(new File(sc.nextLine()));
    }

    public void setArchivo(File archivo) {
        if (!archivo.exists() || archivo.isDirectory()) {
            System.out.println("ERROR: El archivo no existe o es un directorio");
            pedirArchivo();
            return;
        }
        // COMPROBAR QUE EL ARCHIVO ESTÁ SOPORTADO
/*        if (!archivosSoportados.keySet().contains(archivo.getName().substring(archivo.getName().lastIndexOf('.') + 1))) {
            System.out.println("El archivo introducido no está soportado por el programa.");
            pedirArchivo();
            return;
        }*/
        this.archivo = archivo;
        comprobarTipoFichero();
    }

    public void comprobarTipoFichero() {
        try (FileInputStream fis = new FileInputStream(archivo)) {
            byte[] bytes = fis.readNBytes(5);
            Set<String> bytesSoportados = archivosSoportados.keySet();
            System.out.println(bytesSoportados);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
