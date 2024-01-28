package ej203;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ModificacionTareas {
    public static void modificarDescripcion(Connection con, int id, String descripcionMod) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("UPDATE %s SET descripcion = '%s' WHERE id = %s;", "tareas", descripcionMod, id);
            System.out.println("Se ha modificado el libro: " + id);
            System.out.println("ANTERIOR: ");
            ConsultaTareas.obtenerPorID(con, id);
            System.out.println("NUEVO:");
            stmt.executeUpdate(sql);
            ConsultaTareas.obtenerPorID(con, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modificarEstado(Connection con, int id, Estado estado) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("UPDATE %s SET estado = '%s' WHERE id = %s;", "tareas", estado.name(), id);
            System.out.println("Se ha modificado el libro: " + id);
            System.out.println("ANTERIOR: ");
            ConsultaTareas.obtenerPorID(con, id);
            System.out.println("NUEVO:");
            stmt.executeUpdate(sql);
            ConsultaTareas.obtenerPorID(con, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminarTarea(Connection con, int id) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("DELETE FROM %s WHERE id = %s;", "tareas", id);
            stmt.executeUpdate(sql);
            System.out.println("Se ha eliminado la tarea con id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarTareasCompletadas(Connection con) {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("DELETE FROM %s WHERE estado = '%s';", "tareas", Estado.COMPLETADA.name());
            stmt.executeUpdate(sql);
            System.out.println("Se han eliminado las tareas completadas");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
