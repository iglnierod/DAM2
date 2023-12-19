package ej202;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTabla {
    private String baseDeDatos;
    private String nombreTabla;
    private Connection con;

    public CrearTabla(String baseDeDatos, String nombreTabla, Connection con) {
        this.baseDeDatos = baseDeDatos;
        this.nombreTabla = nombreTabla;
        this.con = con;
    }

    public void crearTabla() {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("CREATE TABLE IF NOT EXISTS %s(\n" +
                    "\tid int PRIMARY KEY,\n" +
                    "\ttitulo varchar(90) NOT NULL,\n" +
                    "\tanio_publicacion YEAR,\n" +
                    "\tautor varchar(90)\n" +
                    ");", nombreTabla);
            stmt.executeUpdate(sql);
            System.out.printf("Se ha creado la tabla: %s\n", nombreTabla);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
