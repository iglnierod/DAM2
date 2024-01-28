package ej203;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTabla {
    public static void crearTabla(Connection con, String nombreTabla) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("""
                    CREATE TABLE IF NOT EXISTS %s(
                       id INTEGER PRIMARY KEY,
                       descripcion TEXT,
                       fecha_creacion DATETIME,
                       estado VARCHAR(15) CHECK( estado IN ('PENDIENTE','EN_PROCESO','COMPLETADA') )
                    );
                    """, nombreTabla);
            stmt.executeUpdate(sql);
            System.out.println("Se ha creado la tabla: " + nombreTabla);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
