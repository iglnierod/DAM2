package ej202;

public class Main {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    public static final String USER = "root";
    public static final String PASS = "abc123.";
    public static void main(String[] args) {
        final String BD = "libros";
        CrearBaseDatos crearBaseDatos = new CrearBaseDatos(BD);
    }
}
