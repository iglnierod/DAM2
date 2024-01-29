<<<<<<< HEAD
//package ej205;
//
//public class Main {
//    public static void main(String[] args) {
//        Connection connection = DatabaseConnection("mysql");
//    }
//}
=======
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
>>>>>>> 5959f5e571f64c47f850c5daf24d780ef4f88b78
