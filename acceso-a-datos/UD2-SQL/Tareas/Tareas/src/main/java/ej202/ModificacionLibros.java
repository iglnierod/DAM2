package ej202;

import java.sql.*;

public class ModificacionLibros {
    public static void modificarTitulo(Connection con, String db, String tabla, String id, String nuevoTitulo) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("UPDATE %s SET titulo = '%s' WHERE id = %s;", tabla, nuevoTitulo, id);
            stmt.executeUpdate(sql);
            System.out.println("Se ha modificado el libro con id: " + id);
            ConsultaLibros.obtenerLibrosPorID(con, db, tabla, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modificarAutor(Connection con, String db, String tabla, String id, String nuevoAutor) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("UPDATE %s SET autor = '%s' WHERE id = %s;", tabla, nuevoAutor, id);
            stmt.executeUpdate(sql);
            System.out.println("Se ha modificado el libro con id: " + id);
            ConsultaLibros.obtenerLibrosPorID(con, db, tabla, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modificarAnioPublicacion(Connection con, String db, String tabla, String id, String nuevoAnioPublicacion) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("UPDATE %s SET anio_publicacion = '%s' WHERE id = %s;", tabla, nuevoAnioPublicacion, id);
            stmt.executeUpdate(sql);
            System.out.println("Se ha modificado el libro con id: " + id);
            ConsultaLibros.obtenerLibrosPorID(con, db, tabla, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminarLibro(Connection con, String db, String tabla, String id) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("DELETE FROM %s.%s WHERE id = %s", db, tabla, id);
            stmt.executeUpdate(sql);
            System.out.println("Se ha eliminado el libro con id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarLibrosAnteriores(Connection con, String db, String tabla, String anioPublicacion) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("DELETE FROM %s.%s WHERE anio_publicacion < %s", db, tabla, anioPublicacion);
            stmt.executeUpdate(sql);
            System.out.println("Se han eliminado los libros anteriores al año: " + anioPublicacion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
