import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ComprobadorFichero {
    private File archivo;
    private Map<String, String> archivosSoportados;

    public ComprobadorFichero() {
        archivo = new File("");
        archivosSoportados = new HashMap<>();
        setArchivosSoportados();
    }

    private void setArchivosSoportados() {
        archivosSoportados.put("pdf", "%PDF");
        archivosSoportados.put("zip", "PK\u0003");
        archivosSoportados.put("7z", "7z¼");
        archivosSoportados.put("rar", "Rar!");
        archivosSoportados.put("png", "‰PNG");
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
            byte[] bytes = fis.readNBytes(3);
            char[] primerosBytes = new char[bytes.length];
            for(int i = 0; i < primerosBytes.length; i++) {
                primerosBytes[i] = (char) bytes[i];
            }
            String bytesString = String.valueOf(primerosBytes);
            // BUSCAR LA KEY EN EL MAP
            String tipoArchivo = null;
            for (Map.Entry<String, String> entry : archivosSoportados.entrySet()) {
                if (entry.getValue().contains(bytesString)) {
                    tipoArchivo = entry.getKey();
                    break;
                }
            }

            if (tipoArchivo != null) {
                System.out.println("El archivo es de tipo: " + tipoArchivo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
