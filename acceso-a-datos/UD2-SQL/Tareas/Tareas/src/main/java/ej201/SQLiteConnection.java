package ej201;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    private String pathDatabase;

    public SQLiteConnection() {
        System.out.println("SQLite: intentando conexión");
        // Fichero SQLite
        this.pathDatabase = "acceso-a-datos.sqlite";

        try {
            // Conexión a la base de datos
            String url = String.format("jdbc:sqlite:%s",pathDatabase);
            Connection connection = DriverManager.getConnection(url);

            System.out.println("SQLite: abierta conexión");
            connection.close();
            System.out.println("SQLite: cerrada conexión");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
