package ej203;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String url = "jdbc:sqlite:";
        Connection con = CrearBaseDatos.crearBD(url,"tareas.sqlite");
        CrearTabla.crearTabla(con,"tareas");
        CompletaTareas.crearTareas(con);
        ConsultaTareas.obtenerTodas(con);
        ConsultaTareas.obtenerPorEstado(con, Estado.EN_PROCESO);
        ConsultaTareas.obtenerPosterior(con, "2024-01-13"); // Format: YYYY-MM-DD
        ModificacionTareas.modificarDescripcion(con, 1, "Esta es una descripcion modificada");
        ModificacionTareas.modificarEstado(con, 1, Estado.COMPLETADA);
        Thread.sleep(500);
        ModificacionTareas.eliminarTarea(con, 1);
        Thread.sleep(500);
        ModificacionTareas.eliminarTareasCompletadas(con);
        Thread.sleep(500);
        ej203.LimpiarTabla.eliminarRegistros(con);
    }
}
