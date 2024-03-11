package apartado2;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "abc123.")) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet resultSet = metaData.getCatalogs();

            int schemasCount = 0;
            while (resultSet.next()) {
                String dbName = resultSet.getString("TABLE_CAT");

                if (!dbName.equals("information_schema") && !dbName.equals("mysql") &&
                        !dbName.equals("performance_schema") && !dbName.equals("sys")) {

                    schemasCount++;
                }
            }
            System.out.println("MYSQL: " + schemasCount + " schemas\n");

            resultSet.beforeFirst();
            while (resultSet.next()) {
                String dbName = resultSet.getString("TABLE_CAT");

                if (!dbName.equals("information_schema") && !dbName.equals("mysql") &&
                        !dbName.equals("performance_schema") && !dbName.equals("sys")) {

                    System.out.println("DB: " + dbName);

                    ResultSet tablesResultSet = metaData.getTables(dbName, null, null, new String[]{"TABLE"});
                    int totalTables = 0;
                    while (tablesResultSet.next()) {
                        String tableName = tablesResultSet.getString("TABLE_NAME");
                        totalTables++;
                        System.out.println("# " + tableName + " (" + getRowCount(conn, dbName, tableName) + " rows)");

                        ResultSet columnsResultSet = metaData.getColumns(dbName, null, tableName, null);
                        while (columnsResultSet.next()) {
                            String columnName = columnsResultSet.getString("COLUMN_NAME");
                            System.out.println("  - " + columnName);
                        }
                    }
                    System.out.println("\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getRowCount(Connection conn, String dbName, String tableName) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = String.format("SELECT COUNT(*) FROM %s.%s", dbName, tableName);
        ResultSet resultSet = stmt.executeQuery(query);
        resultSet.next();
        return resultSet.getInt(1);
    }
}

