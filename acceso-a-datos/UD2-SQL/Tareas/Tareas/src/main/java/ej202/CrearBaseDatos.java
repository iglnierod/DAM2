package ej202;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CrearBaseDatos {
    private String nombre;
    private Connection con;

    public CrearBaseDatos(String nombre, Connection con) {
        this.nombre = nombre;
        this.con = con;
    }

    public void crearBD() {
        try {
            Statement stmt = this.con.createStatement();

            if (existeBD()) {
                System.out.println("La base de datos ya existe.");
                System.out.print("Desea elminarla [s/n]: ");
                Scanner sc = new Scanner(System.in);
                char c = sc.nextLine().charAt(0);
                if (c == 's' || c == 'S') {
                    eliminarBD();
                    crearBD();
                    return;
                }
            } else {
                String sql = String.format("CREATE DATABASE %s", this.nombre);
                stmt.executeUpdate(sql);
                System.out.println("Se ha creado la base de datos: " + this.nombre);
            }
            seleccionarBD();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean existeBD() throws SQLException {
        Statement stmt = this.con.createStatement();
        String sql = String.format("SHOW DATABASES LIKE '%s'", this.nombre);
        return stmt.executeQuery(sql).next();
    }

    private void eliminarBD() throws SQLException {
        Statement stmt = this.con.createStatement();
        String sql = String.format("DROP DATABASE %s", this.nombre);
        stmt.executeUpdate(sql);
        System.out.printf("Se ha borrado la base de datos: %s\n", this.nombre);
    }

    private void seleccionarBD() throws SQLException {
        Statement stmt = this.con.createStatement();
        String sql = String.format("USE %s;",this.nombre);
        stmt.executeUpdate(sql);
        System.out.println("Se ha seleccionado la base de datos: " + this.nombre);
    }
}
