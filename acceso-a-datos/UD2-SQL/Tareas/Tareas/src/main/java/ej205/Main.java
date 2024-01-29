package ej205;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DBMS dbms = DBMS.MySQL;
        DatabaseConnection databaseConnection = new DatabaseConnection(dbms);
        Connection con = databaseConnection.get();

        Database database = new Database(con, dbms);
        database.build();

        TableCreation tableCreation = new TableCreation(con, dbms);
        tableCreation.create();
    }
}
