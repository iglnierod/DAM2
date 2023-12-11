package ej201;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MicrosoftSQLServerConnection {
    private String dbHost;
    private String dbUsername;
    private String dbPassword;

    public MicrosoftSQLServerConnection() {
        this.dbHost = "127.0.0.1:1433";
        this.dbUsername = "SA";
        this.dbPassword = "Abc123.Abc123.";

        System.out.println("Microsoft SQL: intentando conexión");
        Properties props = new Properties();
        props.setProperty("user", dbUsername);
        props.setProperty("password", dbPassword);

        try {
            String url = String.format("jdbc:sqlserver://%s;",dbHost);
            Connection connection = DriverManager.getConnection(url, props);
            System.out.println("Microsoft SQL: abierta conexión");
            connection.close();
            System.out.println("Microsoft SQL: cerrada conexión");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
