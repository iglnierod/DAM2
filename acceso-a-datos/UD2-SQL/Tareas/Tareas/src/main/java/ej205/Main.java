package ej205;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DBMS dbms = DBMS.MySQL;
        DatabaseConnection databaseConnection = new DatabaseConnection(dbms);
        Connection con = databaseConnection.getConnection();

        Database database = new Database(con, dbms);
        database.build(databaseConnection);

        TableCreation tableCreation = new TableCreation(con, dbms);
        tableCreation.create();

        FillDatabase fillDatabase = new FillDatabase(con, databaseConnection.isCreated());
        fillDatabase.fill();

        Book book = new Book(con);
        book.getAll();
        book.getByAuthor("Agatha", "Christie");

        Author author = new Author(con);
        author.getAll();
        author.getBooksNumber();

        ClearDatabase clearDatabase = new ClearDatabase(con);
        clearDatabase.clearAll();
    }
}
