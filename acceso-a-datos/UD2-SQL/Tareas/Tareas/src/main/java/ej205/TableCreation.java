package ej205;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreation {
    private Connection con;
    private DBMS dbms;

    public TableCreation(Connection con, DBMS dbms) {
        this.con = con;
        this.dbms = dbms;
    }

    public void create() {
        try {
            Statement stmt = con.createStatement();
            if (dbms == DBMS.MySQL) {
                stmt.executeUpdate(String.format("USE %s", Database.DATABASE_NAME));
            }
            String[] scripts = getScript().split(";");
            for (String script : scripts) {
                if (!script.trim().isEmpty()) {
                    stmt.executeUpdate(script);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getScript() {
        String sql = null;
        if (this.dbms == DBMS.MySQL) {
            sql = """
                    CREATE TABLE IF NOT EXISTS autores (
                        id INT PRIMARY KEY,
                        nombre VARCHAR(45),
                        apellidos VARCHAR(45)
                    );
                                        
                    CREATE TABLE IF NOT EXISTS libros (
                        id INT PRIMARY KEY,
                        titulo VARCHAR(100) NOT NULL,
                        anio_publicacion YEAR,
                        autor_id INT,
                        FOREIGN KEY (autor_id) REFERENCES autores(id)
                    );
                    """;
        } else if (dbms == DBMS.SQLite) {
            sql = """
                    CREATE TABLE IF NOT EXISTS autores (
                        id INTEGER PRIMARY KEY,
                        nombre TEXT,
                        apellidos TEXT
                    );
                                        
                    CREATE TABLE IF NOT EXISTS libros (
                        id INTEGER PRIMARY KEY,
                        titulo TEXT NOT NULL,
                        anio_publicacion INTEGER,
                        autor_id INTEGER,
                        FOREIGN KEY (autor_id) REFERENCES autores(id)
                    );
                    """;
        }
        return sql;
    }
}
