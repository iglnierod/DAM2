package ej205;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Database {
    private Connection con;
    private DBMS dbms;
    public static final String DATABASE_NAME = "biblioteca";

    public Database(Connection con, DBMS dbms) {
        this.con = con;
        this.dbms = dbms;
    }

    public void build() {
        try {
            Statement stmt = this.con.createStatement();
            if (exists()) {
                System.out.println("La base de datos ya existe.");
                System.out.print("Desea elminarla [s/n]: ");
                Scanner sc = new Scanner(System.in);
                char c = sc.nextLine().charAt(0);
                if (c == 's' || c == 'S') {
                    delete();
                    build();
                    return;
                }
            } else {
                String sql = String.format("CREATE DATABASE %s", DATABASE_NAME);
                stmt.executeUpdate(sql);
                System.out.println("Se ha creado la base de datos: " + DATABASE_NAME);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean exists() throws SQLException {
        Statement stmt = this.con.createStatement();
        String sql = String.format("SHOW DATABASES LIKE '%s'", DATABASE_NAME);
        return stmt.executeQuery(sql).next();
    }

    private void delete() throws SQLException {
        Statement stmt = this.con.createStatement();
        String sql = String.format("DROP DATABASE %s", DATABASE_NAME);
        stmt.executeUpdate(sql);
        System.out.printf("Se ha borrado la base de datos: %s\n", DATABASE_NAME);
    }


//    private String getSQLScript() {
//        String sql = null;
//        switch (this.dbms) {
//            case MySQL:
//
//        }
//    }
}
