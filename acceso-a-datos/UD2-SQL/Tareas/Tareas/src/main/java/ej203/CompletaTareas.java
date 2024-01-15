package ej203;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompletaTareas {
    public static void crearTareas(Connection con) {
        try {
            Statement stmt = con.createStatement();
            String sql = """
                    INSERT INTO tareas (descripcion, fecha_creacion, estado) VALUES ('Completar informe mensual', '2024-01-12 08:30:00', 'PENDIENTE');
                    INSERT INTO tareas (descripcion, fecha_creacion, estado) VALUES ('Revisar correos electrónicos', '2024-01-13 10:00:00', 'EN_PROCESO');
                    INSERT INTO tareas (descripcion, fecha_creacion, estado) VALUES ('Preparar presentación para reunión', '2024-01-14 13:45:00', 'EN_PROCESO');
                    INSERT INTO tareas (descripcion, fecha_creacion, estado) VALUES ('Realizar llamadas de seguimiento a clientes', '2024-01-15 15:30:00', 'PENDIENTE');
                    INSERT INTO tareas (descripcion, fecha_creacion, estado) VALUES ('Revisar y cerrar tickets de soporte', '2024-01-16 17:00:00', 'COMPLETADA');
                    """;
            stmt.executeUpdate(sql);
            System.out.println("Se han añadido 5 tareas a la tabla");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
