package ej203;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LimpiarTabla {
    public static void eliminarRegistros(Connection con) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("DELETE FROM %s;", "tareas");
            stmt.executeUpdate(sql);
            System.out.println("SE HAN ELIMINADO TODOS LOS REGISTROS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
