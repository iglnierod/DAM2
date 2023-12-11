package ej201;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnection {
    private String dbHost;
    private String dbSID;
    private String dbUsername;
    private String dbPassword;

    public OracleConnection() {
        this.dbHost = "127.0.0.1:1521";
        this.dbSID = "XE";
        this.dbUsername = "system";
        this.dbPassword = "abc123.";

        System.out.println("Oracle: intentando conexión");

        Properties props = new Properties();
        props.setProperty("user", dbUsername);
        props.setProperty("password", dbPassword);

        try {
            String url = String.format("jdbc:oracle:thin:@//%s/%s", dbHost, dbSID);
            Connection connection = DriverManager.getConnection(url, props);
            System.out.println("Oracle: abierta conexión");
            connection.close();
            System.out.println("Oracle: cerrada conexión");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
