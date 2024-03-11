### MySQL JDBC
```txt
jdbc:mysql://<ip>:<port>/<database>
```

### SQLite JDBC
```txt
jdbc:sqlite:<fichero.sqlite>
```

### Consultas Por consola
```java
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
                row += String.format("%-40s  ", rs.getObject(i));
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
            str += String.format("%-40s  ", colName);
        }
        return str + ANSI.RESET;
    }
}
```

### ANSI
```java
package query;

public class ANSI {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";
}
```
