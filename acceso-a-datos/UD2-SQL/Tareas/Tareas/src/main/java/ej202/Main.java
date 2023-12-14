package ej202;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    public static final String USER = "root";
    public static final String PASS = "abc123.";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            final String baseDeDatos = "libros";
            final String tabla = "libros";
            CrearBaseDatos crearBaseDatos = new CrearBaseDatos(baseDeDatos, con);
            crearBaseDatos.crearBD();

            CrearTabla crearTabla = new CrearTabla(baseDeDatos, tabla, con);
            crearTabla.crearTabla();

            CompletaLibros completaLibros = new CompletaLibros(baseDeDatos, tabla, con);
            completaLibros.anadirLibros();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
