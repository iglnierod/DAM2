package ej206;

import java.sql.*;

public class ConsultaNombres {
    public static void empiezaPor(String letra) {
        try (Connection con = DriverManager.getConnection(Main.URL, Main.USER, Main.PASS);
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate("USE " + Main.BD + ";");

            String sql = "SELECT nomemp FROM emp WHERE nomemp LIKE ?;";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, letra + "%");

            ResultSet rs = sentencia.executeQuery();
            DatabaseManager.printResultSet(rs);
        } catch (SQLException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}
