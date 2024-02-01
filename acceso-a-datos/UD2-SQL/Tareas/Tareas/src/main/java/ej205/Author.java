package ej205;

import query.Query;

import java.sql.*;

public class Author {
    private Connection con;

    public Author(Connection con) {
        this.con = con;
    }

    public void getAll() {
        Query.query(con, "SELECT * FROM autores;");
    }

    public void getBooksNumber() {
        String sql = """
                SELECT CONCAT(a.nombre,' ', a.apellidos) as Autor,
                  COUNT(l.id) as 'Nº libros escritos'
                FROM libros l, autores a
                WHERE l.autor_id = a.id
                GROUP BY a.id;
                """;
        Query.query(con, sql);
    }
}
