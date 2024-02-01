package ej207;

import java.io.*;
import java.sql.*;

public class Main {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    public static final String USER = "root";
    public static final String PASS = "abc123.";

    public static void main(String[] args) throws Exception {
        File sqlScript = new File("empleados.sql");
        Connection con = DriverManager.getConnection(URL, USER, PASS);
//        ScriptSQL.exec(con, sqlScript.getAbsolutePath());
        executeFile(con, sqlScript);
    }

    static void executeFile(Connection con, File file) {
        String separator = ";";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
            String[] commands = text.toString().split(separator);
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

    static void execute(Connection con, String sql) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        System.out.println("EXECUTED: " + sql);
    }
}
