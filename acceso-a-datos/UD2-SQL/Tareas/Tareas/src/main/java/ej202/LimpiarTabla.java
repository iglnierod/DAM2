package ej202;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LimpiarTabla {
    public static void eliminarRegistros(Connection con, String db, String tabla) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("DELETE FROM %s.%s", db, tabla);
            stmt.executeUpdate(sql);
            System.out.println("SE HAN ELIMINADO TODOS LOS REGISTROS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
