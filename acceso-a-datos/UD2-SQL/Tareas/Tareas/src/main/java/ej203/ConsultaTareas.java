package ej203;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaTareas {
    public static void obtenerTodas(Connection con) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("SELECT * FROM %s;", "tareas");
            ResultSet rs = stmt.executeQuery(sql);
            printLimitador(sql);
            mostrarResultSet(rs);
            printLimitador();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void obtenerPosterior(Connection con, String fecha) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("SELECT * FROM %s WHERE DATE(fecha_creacion) >= DATE('%s');", "tareas", fecha);
            ResultSet rs = stmt.executeQuery(sql);
            printLimitador(sql);
            mostrarResultSet(rs);
            printLimitador();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void obtenerPorEstado(Connection con, Estado estado) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("SELECT * FROM %s WHERE estado = '%s';", "tareas", estado.name());
            ResultSet rs = stmt.executeQuery(sql);
            printLimitador(sql);
            mostrarResultSet(rs);
            printLimitador();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void obtenerPorID(Connection con, int id) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("SELECT * FROM %s WHERE id = '%s';", "tareas", id);
            ResultSet rs = stmt.executeQuery(sql);
            printLimitador(sql);
            mostrarResultSet(rs);
            printLimitador();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String id = rs.getString("id");
            String descripcion = rs.getString("descripcion");
            String fecha_creacion = rs.getString("fecha_creacion");
            String estado = rs.getString("estado");

            System.out.printf("[%2s : %s] %-50s %10s\n", id, fecha_creacion, descripcion, "(" + estado + ")");
        }
    }

    private static void printLimitador(String consulta) {
        System.out.printf("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ %20s ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n", consulta);
    }

    private static void printLimitador() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 49; i++) {
            sb.append("~ ");
        }
        System.out.println(sb + "\n");
    }
}
