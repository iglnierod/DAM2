package ej202;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CompletaLibros {
    private String baseDeDatos;
    private String tabla;
    private Connection con;

    public CompletaLibros(String baseDeDatos, String tabla, Connection con) {
        this.baseDeDatos = baseDeDatos;
        this.tabla = tabla;
        this.con = con;
    }

    public void anadirLibros() {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("INSERT INTO %s (id, titulo, anio_publicacion, autor) VALUES\n" +
                    "(1, 'Cien años de soledad', 1967, 'Gabriel García Márquez'),\n" +
                    "(2, 'To Kill a Mockingbird', 1960, 'Harper Lee'),\n" +
                    "(3, '1984', 1949, 'George Orwell'),\n" +
                    "(4, 'The Great Gatsby', 1925, 'F. Scott Fitzgerald'),\n" +
                    "(5, 'One Hundred Years of Solitude', 1967, 'Gabriel García Márquez');", this.tabla);
            stmt.executeUpdate(sql);
            System.out.printf("Se han añadido 5 libros de ejemplo a la tabla: %s\n",tabla);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
