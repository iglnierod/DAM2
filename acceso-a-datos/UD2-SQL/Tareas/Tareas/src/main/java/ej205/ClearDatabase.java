package ej205;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ClearDatabase {
    private Connection con;

    public ClearDatabase(Connection con) {
        this.con = con;
    }

    public void clearAll() {
        clearTable("libros");
        clearTable("autores");
    }

    public void clearTable(String tableName) {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(String.format("DELETE FROM %s;", tableName));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
