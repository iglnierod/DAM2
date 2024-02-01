package query;

import java.sql.*;
import java.util.ArrayList;

public class Query {
    private static int count;
    public static void query(Connection con, String sql) {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            Query.print(rs, sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void print(ResultSet rs, String sql) throws Exception {
        System.out.printf("%s%s   #%s   %s%n%s%s%s%n", ANSI.YELLOW_BACKGROUND, ANSI.BLACK, count++, ANSI.RESET, ANSI.PURPLE, sql, ANSI.RESET);
        ArrayList<String> columns = getColumnNames(rs);
        System.out.println(getHeaderRow(columns));

        while (rs.next()) {
            String row = "";
            for (int i = 1; i <= columns.size(); i++) {
                row += String.format("%-35s  ", rs.getObject(i));
            }
            System.out.println(row);
        }

        System.out.println("\n");
    }

    public static ArrayList<String> getColumnNames(ResultSet resultSet) throws Exception {
        ArrayList<String> names = new ArrayList<>();
        ResultSetMetaData metadata = resultSet.getMetaData();
        int columns = metadata.getColumnCount();

        for (int i = 1; i <= columns; i++) {
            String name = metadata.getColumnLabel(i);
            names.add(name);
        }

        return names;
    }

    public static String getHeaderRow(ArrayList<String> columnNames) {
        String str = ANSI.YELLOW_BACKGROUND + ANSI.BLACK;
        for (String colName : columnNames) {
            str += String.format("%-35s  ", colName);
        }
        return str + ANSI.RESET;
    }
}
