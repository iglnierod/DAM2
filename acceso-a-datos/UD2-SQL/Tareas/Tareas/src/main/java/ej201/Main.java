package ej201;

public class Main {
    public static void main(String[] args) {
        // MySQL
        MySQLConnection MySQL = new MySQLConnection();
        System.out.println(" - - - - - ");
        // SQLite
        SQLiteConnection SQLite = new SQLiteConnection();
        System.out.println(" - - - - - ");

        // PostgreSQL
        PostgreSQLConnection PostgreSQL = new PostgreSQLConnection();
        System.out.println(" - - - - - ");

        // Oracle
        OracleConnection Oracle = new OracleConnection();
        System.out.println(" - - - - - ");

        // MicrosoftSQL
        MicrosoftSQLServerConnection MicrosoftSQL = new MicrosoftSQLServerConnection();
        System.out.println(" - - - - - ");

        // MariaDB
        MariaDBConnection MariaDB = new MariaDBConnection();
    }
}
