package ej202;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBaseDatos {
    private String nombre;

    public CrearBaseDatos(String nombre) {
        this.nombre = nombre;
        /*TODO: crear base de datos*/
        try (Connection con = DriverManager.getConnection(Main.URL, Main.USER, Main.PASS);
             Statement stmt = con.createStatement()) {
            String sql = String.format("CREATE DATABASE %s", this.nombre);
            stmt.executeUpdate(sql);
            System.out.println("Se ha creado la base de datos: " + this.nombre);
        } catch (SQLException e) {
            System.err.println("No se ha creado la base de datos: " + this.nombre);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
