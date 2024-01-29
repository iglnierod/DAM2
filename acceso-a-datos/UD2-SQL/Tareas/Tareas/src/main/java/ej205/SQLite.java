package ej205;

public class SQLite {
    public static final String JDBC = "jdbc:sqlite:";
    public static final String DATABASE = "biblioteca.sqlite";

    public static String getUrl() {
        return String.format("%s%s", JDBC, DATABASE);
    }
}
