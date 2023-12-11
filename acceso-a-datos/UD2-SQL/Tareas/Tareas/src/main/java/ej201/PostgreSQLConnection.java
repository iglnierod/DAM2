package ej201;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgreSQLConnection {
    private String dbHost;
    private String dbUsername;
    private String dbPassword;

    public PostgreSQLConnection() {
        System.out.println("PostgreSQL: intentando conexión");

        this.dbHost = "127.0.0.1:5432";
        this.dbUsername = "postgres";
        this.dbPassword = "abc123.";

        // Propiedades de conexión JDBC
        Properties props = new Properties();
        props.setProperty("user", dbUsername);
        props.setProperty("password", dbPassword);

        try {
            String url = String.format("jdbc:postgresql://%s/", dbHost);
            Connection connection = DriverManager.getConnection(url, props);
            System.out.println("PostgreSQL: abierta conexión");
            connection.close();
            System.out.println("PostgreSQL: cerrada conexión");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
