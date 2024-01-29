package ej205;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FillDatabase {
    private Connection con;

    public FillDatabase(Connection con) {
        this.con = con;
    }

    public void fill() {
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String[] scripts = getScript().split(";");
            for (String script : scripts) {
                if (!script.trim().isEmpty()) {
                    stmt.executeUpdate(script);
                }
            }
            System.out.println("FillDatabase: se han insertado los datos.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getScript() {
        return """
                INSERT INTO autores (id, nombre, apellidos) VALUES
                (1, 'Gabriel', 'García Márquez'),
                (2, 'Jane', 'Austen'),
                (3, 'Haruki', 'Murakami'),
                (4, 'Agatha', 'Christie'),
                (5, 'George', 'Orwell');
                                        
                INSERT INTO libros (id, titulo, anio_publicacion, autor_id) VALUES
                (1, 'Cien años de soledad', 1967, 1),
                (2, 'Orgullo y prejuicio', 1950, 2),
                (3, 'Norwegian Wood', 1987, 3),
                (4, 'Asesinato en el Orient Express', 1934, 4),
                (5, '1984', 1949, 5),
                (6, 'El amor en los tiempos del cólera', 1985, 1),
                (7, 'Matar a un ruiseñor', 1960, 2),
                (8, 'Kafka en la orilla', 2002, 3),
                (9, 'Diez negritos', 1939, 4),
                (10, 'Rebelión en la granja', 1945, 5);
                """;
    }
}