package ej206;

import java.sql.*;

public class ConsultaComision {
    public static void tiene(String comision) {
        try (Connection con = DriverManager.getConnection(Main.URL, Main.USER, Main.PASS);
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate("USE " + Main.BD + ";");

            String sql = "SELECT * FROM emp WHERE comision = ?;";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, comision);

            ResultSet rs = sentencia.executeQuery();
            DatabaseManager.printResultSet(rs);
        } catch (SQLException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}
