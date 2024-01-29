package ej205;

import java.util.Properties;

public class MySQL {
    public static final String JDBC = "jdbc:mysql:";
    public static final String HOST_NAME = "127.0.0.1";
    public static final String PORT = "3306";
    public static final String USER = "root";
    public static final String PASSWORD = "abc123.";

    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);
        return properties;
    }

    public static String getUrl() {
        return String.format("%s//%s:%s", JDBC, HOST_NAME, PORT);
    }
}
