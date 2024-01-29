<<<<<<< HEAD
//package ej205;
//
//public class DatabaseConnection {
//
//    public static Connection get(String engine) {
//        //
//        if(databaseEngine == "mysql") {
//
//        }
//
//        if(databaseEngine == "sqlite") {
//
//        }
//        return connection;
//    }
//}
=======
package ej205;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private DBMS dbms;

    public DatabaseConnection(DBMS dbms) {
        this.dbms = dbms;
    }

    public DBMS getDbms() {
        return dbms;
    }

    public void setDbms(DBMS dbms) {
        this.dbms = dbms;
    }

    public Connection get() {
        Connection con = null;
        switch (this.dbms) {
            case MySQL:
                try {
                    con = DriverManager.getConnection(MySQL.getUrl(), MySQL.getProperties());
                } catch (SQLException e) {
                    System.out.println("No se ha podido establecer la conexión con la base de datos MySQL");
                    e.printStackTrace();
                }
                break;
            case SQLite:
                try {
                    con = DriverManager.getConnection(SQLite.getUrl());
                } catch (SQLException e) {
                    System.out.println("No se ha podido establecer la conexión con la base de datos SQLite");
                    e.printStackTrace();
                }
                break;
        }
        return con;
    }
}
>>>>>>> 5959f5e571f64c47f850c5daf24d780ef4f88b78
