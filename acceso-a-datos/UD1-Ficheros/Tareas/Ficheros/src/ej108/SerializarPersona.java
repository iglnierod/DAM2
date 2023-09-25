package ej108;

import java.io.*;

public class SerializarPersona {
    public void escribirPersonaEnFichero(Persona persona, File fichero) {
        try (FileOutputStream fos = new FileOutputStream(fichero)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(persona);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Persona leerPersonaDeFichero(File fichero) {
        Persona p = new Persona();
        try (FileInputStream fis = new FileInputStream(fichero)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            p = (Persona) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public static void main(String[] args) {
        Persona p1 = new Persona("Marcos", 33);
        Persona p2;
        File fichero = new File("persona.txt");
        SerializarPersona sp = new SerializarPersona();
        sp.escribirPersonaEnFichero(p1, fichero);
        sp.leerPersonaDeFichero(fichero);
        p2 = sp.leerPersonaDeFichero(fichero);
        System.out.println(p2);
    }
}
