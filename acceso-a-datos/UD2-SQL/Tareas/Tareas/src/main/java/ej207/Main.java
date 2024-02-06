package ej207;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

public class Main {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    public static final String USER = "root";
    public static final String PASS = "abc123.";

    public static void main(String[] args) throws Exception {
        File sqlScript = new File("empleados.sql");
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        executeFile(con, sqlScript); // SIN LIBRERIA
        //executeSqlScript(con, sqlScript); // CON LIBRERIA
    }

    // SIN LIBRERIA
    static void executeFile(Connection con, File file) {
        String separator = ";";
        String[] commentString = {"//", "--", "#"};
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                //System.out.println(line);
                if (!containsInArray(commentString, line)) {
                    text.append(line).append(" ");
                }
            }

            String[] commands = text.toString().trim().split(separator);
            System.out.println(Arrays.deepToString(commands));
            for (int i = 0; i < commands.length; i++) {
                execute(con, commands[i]);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean containsInArray(String[] array, String string) {
        for (String s : array) {
            if (string.startsWith(s)) return true;
        }
        return false;
    }

    static void execute(Connection con, String sql) throws SQLException {
        Statement stmt = con.createStatement();
        System.out.println("EXECUTED: " + sql);
        stmt.executeUpdate(sql);
    }

    // CON LIBRERIA
    static void executeSqlScript(Connection con, File file) {
        String path = file.getAbsolutePath();
        boolean continueOrError = false;
        boolean ignoreFailedDrops = false;
        String commentPrefix = "--";
        String separator = ";";
        String blockCommentStartDelimiter = "/*";
        String blockCommentEndDelimiter = "*/";

        ScriptUtils.executeSqlScript(con, new EncodedResource(new PathResource(path)), continueOrError, ignoreFailedDrops, commentPrefix, separator, blockCommentStartDelimiter, blockCommentEndDelimiter);
    }
}
