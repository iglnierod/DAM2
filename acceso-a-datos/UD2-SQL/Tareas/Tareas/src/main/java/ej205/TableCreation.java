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
            stmt.executeUpdate(String.format("USE %s", Database.DATABASE_NAME));
            switch (dbms) {
                case MySQL:
                    stmt.executeUpdate(getScript());
                    break;
                case SQLite:
                    // TODO
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
        } else {
            // TODO
        }
        return sql;
    }
}
