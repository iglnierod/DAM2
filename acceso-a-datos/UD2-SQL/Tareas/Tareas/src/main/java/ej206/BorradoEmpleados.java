package ej206;

import java.sql.*;

public class BorradoEmpleados {
    public static void eliminarID(String id) {
        try (Connection con = DriverManager.getConnection(Main.URL, Main.USER, Main.PASS);
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate("USE " + Main.BD + ";");

            // si el empleado es jefe del departamento pasa a ser numjefe = NULL
            String sql = String.format("UPDATE depto SET numjefe = NULL where numjefe = %s", id);
            stmt.executeUpdate(sql);

            sql = "delete from emp where numemp = ?;";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, id);

            sentencia.executeUpdate();

            System.out.println("SE HA ELIMINADO EL EMPLEADO CON ID: " + id);
        } catch (SQLException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}
