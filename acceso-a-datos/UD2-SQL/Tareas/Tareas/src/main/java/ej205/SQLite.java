package ej205;

import java.io.File;

public class SQLite {
    public static final String JDBC = "jdbc:sqlite:";
    public static final String DATABASE = "biblioteca.sqlite";
    public static final File DATABASE_FILE = new File(DATABASE);

    public static String getUrl() {
        return String.format("%s%s", JDBC, DATABASE);
    }
}
