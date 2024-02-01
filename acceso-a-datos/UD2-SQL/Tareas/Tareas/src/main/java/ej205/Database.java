package ej205;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection con;
    private DBMS dbms;
    public static final String DATABASE_NAME = "biblioteca";

    public Database(Connection con, DBMS dbms) {
        this.con = con;
        this.dbms = dbms;
    }

    public void build(DatabaseConnection databaseConnection) {
        if (dbms == DBMS.MySQL) {
            if (!databaseConnection.isCreated()) {
                Statement stmt;
                try {
                    stmt = this.con.createStatement();
                    stmt.executeUpdate("CREATE DATABASE " + Database.DATABASE_NAME);
                    databaseConnection.setCreated(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
