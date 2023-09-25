package ej107;

import java.io.*;

public class FicheroTexto {
    // ATRIBUTO
    File fichero;

    // CONSTRUCTOR
    public FicheroTexto(File fichero) {
        this.fichero = fichero;
    }

    // METODOS
    public void escribir(String texto) {
        try (FileWriter fs = new FileWriter(fichero, true)) {
            fs.write(texto);
            System.out.println("Se ha escrito el texto '" + texto + "' en el fichero: " + fichero);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void leer() {
        try (FileReader fr = new FileReader(fichero)) {
            System.out.print("El fichero " + fichero + " contiene el siguiente texto: ");
            byte b;
            while ((b = (byte) fr.read()) != -1) {
                System.out.print((char) b);
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // GETTER
    public File getFichero() {
        return fichero;
    }

    // SETTER
    public void setFichero(File fichero) {
        this.fichero = fichero;
    }
}
