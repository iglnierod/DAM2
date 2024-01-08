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
}
