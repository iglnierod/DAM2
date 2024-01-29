package ej206;

import java.sql.*;

public class BorradoDepartamento {
    public static void eliminarID(String numdep) {
        final String URL = "jdbc:mysql://127.0.0.1:3306/";
        final String USER = "root";
        final String PASS = "abc123.";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate("USE " + Main.BD + ";");
            stmt.executeUpdate("UPDATE depto SET numjefe = NULL WHERE numdep = " + numdep);

            DatabaseManager.setForeignKeyChecks(false);

            String sql = "delete from emp where numdep = ?;";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, numdep);

            sentencia.executeUpdate();

            sql = "DELETE FROM depto WHERE numdep = ?;";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, numdep);

            sentencia.executeUpdate();

            DatabaseManager.setForeignKeyChecks(true);

            System.out.println("SE HA ELIMINADO EL DEPARTAMENTO: " + numdep);
        } catch (SQLException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}
