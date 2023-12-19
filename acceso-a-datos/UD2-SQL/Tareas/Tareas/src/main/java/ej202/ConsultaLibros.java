package ej202;

import java.sql.*;

public class ConsultaLibros {
    public static void obtenerLibros(Connection con, String db, String tabla) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate(String.format("USE %s;", db));
            String sql = String.format("SELECT * FROM %s;", tabla);
            ResultSet rs = s.executeQuery(sql);
            mostrarResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void obtenerLibrosPorAutor(Connection con, String db, String tabla, String autor) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate(String.format("USE %s;", db));
            String sql = String.format("SELECT * FROM %s WHERE autor LIKE '%s';", tabla, autor);
            ResultSet rs = s.executeQuery(sql);
            mostrarResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void obtenerLibrosPosteriores(Connection con, String db, String tabla, String anio) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate(String.format("USE %s;", db));
            String sql = String.format("SELECT * FROM %s WHERE anio_publicacion > %s;", tabla, anio);
            ResultSet rs = s.executeQuery(sql);
            mostrarResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void obtenerLibrosPorID(Connection con, String db, String tabla, String id) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate(String.format("USE %s;", db));
            String sql = String.format("SELECT * FROM %s WHERE id = %s;", tabla, id);
            ResultSet rs = s.executeQuery(sql);
            mostrarResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void mostrarResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String id = rs.getString("id");
            String titulo = rs.getString("titulo");
            String anio_publicacion = rs.getString("anio_publicacion");
            String autor = rs.getString("autor");

            // 1967-01-01 -> 1967
            anio_publicacion = anio_publicacion.substring(0, anio_publicacion.indexOf('-'));

            System.out.printf("[%2s] %s de %s (%s)\n", id, titulo, autor, anio_publicacion);
        }
    }
}
