package ej205;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DBMS dbms = DBMS.SQLite;
        DatabaseConnection databaseConnection = new DatabaseConnection(dbms);
        Connection con = databaseConnection.getConnection();

        Database database = new Database(con, dbms);
        database.build(databaseConnection);

        TableCreation tableCreation = new TableCreation(con, dbms);
        tableCreation.create();

        FillDatabase fillDatabase = new FillDatabase(con);
        fillDatabase.fill();
    }
}
