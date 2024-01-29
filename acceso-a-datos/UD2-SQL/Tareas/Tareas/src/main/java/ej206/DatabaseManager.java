package ej206;

import java.sql.*;

public class DatabaseManager {
    public static void printResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Imprimir encabezados en mayúsculas
        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) {
                System.out.print("   ");
            }
            System.out.printf("%-20s", metaData.getColumnName(i).toUpperCase());
        }
        System.out.println();
        for (int i = 0; i < 75; i++) {
            System.out.print("- ");
        }
        System.out.println();
        // Imprimir datos
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) {
                    System.out.print("   ");
                }
                System.out.printf("%-20s", resultSet.getString(i));
            }
            System.out.println();
        }
    }

    public static void setForeignKeyChecks(boolean enable) {
        String sqlStatement = "SET FOREIGN_KEY_CHECKS = " + (enable ? "1" : "0");

        try (Connection connection = DriverManager.getConnection(Main.URL, Main.USER, Main.PASS);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            System.err.println("Error al cambiar el estado de las claves foráneas: " + e.getMessage());
        }
    }
}
