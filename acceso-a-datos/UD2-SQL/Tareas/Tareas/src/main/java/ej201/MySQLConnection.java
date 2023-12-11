package ej201;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {
    private String dbHost;
    private String dbUsername;
    private String dbPassword;

    public MySQLConnection() {
        System.out.println("MySQL: intentando conexión");
        // Variables de acceso a la base de datos
        dbHost = "127.0.0.1:3306";
        dbUsername = "root";
        dbPassword = "abc123.";

        // Propiedades de conexión JDBC
        Properties props = new Properties();
        props.setProperty("user", dbUsername);
        props.setProperty("password", dbPassword);

        try {
            // Conexión a la base de datos
            String url = String.format("jdbc:mysql://%s/", dbHost);
            Connection connection = DriverManager.getConnection(url, props);
            System.out.println("MySQL: abierta conexión");
            connection.close();
            System.out.println("MySQL: cerrada conexión");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
