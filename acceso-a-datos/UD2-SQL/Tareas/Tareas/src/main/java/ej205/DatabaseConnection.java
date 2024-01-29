package ej205;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Scanner;

public class DatabaseConnection {
    private DBMS dbms;
    private Connection con;
    private boolean exists;

    public DatabaseConnection(DBMS dbms) {
        this.dbms = dbms;
    }

    public DBMS getDbms() {
        return dbms;
    }

    public void setDbms(DBMS dbms) {
        this.dbms = dbms;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public Connection getConnection() {
        Connection con = null;
        switch (this.dbms) {
            case MySQL:
                checkMySQL();
                break;
            case SQLite:
                checkSQLite();
                break;
        }
        return this.con;
    }

    private void checkSQLite() {
        try {
            Path path = Paths.get(SQLite.DATABASE_FILE.getAbsolutePath());
            this.exists = Files.exists(path);
            if (exists) {
                System.out.println("La base de datos ya existe.");
                System.out.print("Desea elminarla [s/n]: ");
                Scanner sc = new Scanner(System.in);
                char c = sc.nextLine().charAt(0);
                if (c == 's' || c == 'S') {
                    Files.delete(path);
                }
            }
            this.con = DriverManager.getConnection(SQLite.getUrl());
            this.exists = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkMySQL() {
        try {
            this.con = DriverManager.getConnection(MySQL.getUrl(), MySQL.getProperties());
            Statement stmt = this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = stmt.executeQuery(String.format("SHOW DATABASES LIKE '%s'", Database.DATABASE_NAME));
            int rows = 0;
            if (resultSet.last()) {
                rows = resultSet.getRow();
                resultSet.beforeFirst();
            }

            if (rows == 1) {
                this.exists = true;
                System.out.println("La base de datos ya existe.");
                System.out.print("Desea elminarla [s/n]: ");
                Scanner sc = new Scanner(System.in);
                char c = sc.nextLine().charAt(0);
                if (c == 's' || c == 'S') {
                    stmt.executeUpdate("DROP DATABASE " + Database.DATABASE_NAME);
                    this.exists = false;
                }
            }
        } catch (SQLException e) {
            System.out.println("No se ha podido establecer la conexión con la base de datos MySQL");
            e.printStackTrace();
        }
    }
}