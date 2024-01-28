package ej202;

import java.sql.*;

public class Main {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    public static final String USER = "root";
    public static final String PASS = "abc123.";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            final String bd = "libros";
            final String tabla = "libros";
            CrearBaseDatos crearBaseDatos = new CrearBaseDatos(bd, con);
            crearBaseDatos.crearBD();

            CrearTabla crearTabla = new CrearTabla(bd, tabla, con);
            crearTabla.crearTabla();

            CompletaLibros completaLibros = new CompletaLibros(bd, tabla, con);
            completaLibros.anadirLibros();

            System.out.println("TODOS LOS LIBROS");
            ConsultaLibros.obtenerLibros(con, bd, tabla);
            System.out.println("- - - - - - -");
            System.out.println("TODOS LOS LIBROS DE UN AUTOR");
            ConsultaLibros.obtenerLibrosPorAutor(con, bd, tabla, "George Orwell");
            System.out.println("- - - - - - -");
            System.out.println("TODOS LOS LIBROS POSTERIORES A UN AÑO");
            ConsultaLibros.obtenerLibrosPosteriores(con, bd, tabla, "1960");
            System.out.println("- - - - - - -");

            ModificacionLibros.modificarTitulo(con, bd, tabla, "1", "Titulo editado");
            ModificacionLibros.modificarAutor(con, bd, tabla, "1", "Autor inventado");
            ModificacionLibros.modificarAnioPublicacion(con, bd, tabla, "1", "2000");
            ModificacionLibros.eliminarLibro(con,bd,tabla,"2");
            ModificacionLibros.eliminarLibrosAnteriores(con,bd,tabla,"2000");


            //LimpiarTabla.eliminarRegistros(con, bd, tabla);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
