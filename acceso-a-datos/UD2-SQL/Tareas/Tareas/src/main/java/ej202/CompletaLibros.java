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
        anadirLibro("1", "El código Da Vinci", "2003", "Dan Brown");
        anadirLibro("2", "Harry Potter y la piedra filosofal", "1997", "J.K. Rowling");
        anadirLibro("3", "Cien años de soledad", "1967", "Gabriel García Márquez");
        anadirLibro("4", "El Señor de los Anillos", "1954", "J.R.R. Tolkien");
        anadirLibro("5", "Juego de tronos", "1996", "George R.R. Martin");
        anadirLibro("6", "El código Da Vinci", "2003", "Dan Brown");
        anadirLibro("7", "Crepúsculo", "2005", "Stephenie Meyer");
        anadirLibro("8", "El Alquimista", "1988", "Paulo Coelho");
        anadirLibro("9", "El gran Gatsby", "1925", "F. Scott Fitzgerald");
        anadirLibro("10", "Matar a un ruiseñor", "1960", "Harper Lee");
        anadirLibro("11", "Los juegos del hambre", "2008", "Suzanne Collins");
        anadirLibro("12", "Harry Potter y las reliquias de la muerte", "2007", "J.K. Rowling");
        anadirLibro("13", "Millennium 1: Los hombres que no amaban a las mujeres", "2005", "Stieg Larsson");
        anadirLibro("14", "La sombra del viento", "2001", "Carlos Ruiz Zafón");
        anadirLibro("15", "La chica del tren", "2015", "Paula Hawkins");
        anadirLibro("16", "La catedral del mar", "2006", "Ildefonso Falcones");
        anadirLibro("17", "El perfume", "1985", "Patrick Süskind");
        anadirLibro("18", "Los hombres que miraban fijamente a las cabras", "2004", "Jon Ronson");
        anadirLibro("19", "El código Da Vinci", "2003", "Dan Brown");
        anadirLibro("20", "El niño con el pijama de rayas", "2006", "John Boyne");
    }

    /*public void anadirLibros() {
        try {
            Statement stmt = con.createStatement();
            String sql = String.format("INSERT INTO %s (id, titulo, anio_publicacion, autor) VALUES\n" +
                    "(1, 'Cien años de soledad', 1967, 'Gabriel García Márquez'),\n" +
                    "(2, 'To Kill a Mockingbird', 1960, 'Harper Lee'),\n" +
                    "(3, '1984', 1949, 'George Orwell'),\n" +
                    "(4, 'The Great Gatsby', 1925, 'F. Scott Fitzgerald'),\n" +
                    "(5, 'One Hundred Years of Solitude', 1967, 'Gabriel García Márquez');", this.tabla);
            stmt.executeUpdate(sql);
            System.out.printf("Se han añadido 5 libros de ejemplo a la tabla: %s\n", tabla);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public void anadirLibro(String id, String titulo, String anio_publicacion, String autor) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(String.format("USE %s;", baseDeDatos));
            String sql = String.format("INSERT INTO %s VALUES (%s,'%s',%s,'%s');", tabla, id, titulo, anio_publicacion, autor);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
