package ej106;

import java.io.*;

public class FicheroBinario {
    // ATRIBUTOS
    File fichero;

    // CONSTRUCTOR
    public FicheroBinario(File fichero) {
        this.fichero = fichero;
    }

    // METODOS
    public void escribir(String texto) {
        try {
            FileOutputStream fos = new FileOutputStream(fichero);
            char[] charTexto = texto.toCharArray();
            for (int i = 0; i < charTexto.length; i++) {
                fos.write(charTexto[i]);
            }
            System.out.println("Se ha escrito el texto: '" + texto + "' en el fichero: " + fichero.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void leer() {
        try {
            FileInputStream fis = new FileInputStream(fichero);
//            byte b;
//            while((b = (byte)fis.read()) != -1) {
//                System.out.print(fis.available()+ " - ");
//                System.out.println((char)b);
//            }


//            if(true) {
//                return;
//            }

            int len = fis.available();
            char[] texto = new char[len];
            for (int i = 0; i < len; i++) {
                byte x = (byte) fis.read();
                char caracter = (char) x;
                texto[i] = caracter;
            }
            System.out.print("El texto del fichero: " + fichero.getName() + " es: ");
            for (char c :
                    texto) {
                System.out.print(c);
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void copiar(FicheroBinario ficheroDestino) {
        try {
            FileInputStream fis = new FileInputStream(fichero);
            FileOutputStream fos = new FileOutputStream(ficheroDestino.getFichero());

            int len = fis.available();
            char[] caracteres = new char[len];
            for(int i = 0; i < len; i++) {
                caracteres[i] = (char) ((byte) fis.read());
                fos.write(caracteres[i]);
            }

            System.out.println("Se ha copiado el texto del fichero: " + fichero + " a " + ficheroDestino.getFichero());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // GETTERS
    public File getFichero() {
        return fichero;
    }

    // SETTER
    public void setFichero(File fichero) {
        this.fichero = fichero;
    }
}
