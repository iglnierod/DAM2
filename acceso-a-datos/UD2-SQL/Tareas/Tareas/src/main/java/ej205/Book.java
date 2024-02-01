package ej205;

import query.Query;

import java.sql.*;

public class Book {
    private Connection con;

    public Book(Connection con) {
        this.con = con;
    }

    public void getAll() {
        Query.query(con, """
                SELECT l.titulo AS Titulo,
                  CONCAT(a.nombre, ' ', a.apellidos) AS Autor,
                  l.anio_publicacion AS 'Año publicación'
                FROM libros l, autores a
                WHERE l.autor_id = a.id;
                """);
    }

    public void getByAuthor(String authorName, String authorSurname) {
        String sql = String.format("""
                SELECT l.*
                FROM libros l
                JOIN autores a
                ON l.autor_id = a.id
                WHERE a.nombre = '%s' AND
                  a.apellidos = '%s'
                """, authorName, authorSurname);
        Query.query(con, sql);
    }
}
