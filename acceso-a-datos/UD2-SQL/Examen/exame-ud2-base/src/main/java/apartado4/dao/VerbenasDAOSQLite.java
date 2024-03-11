package apartado4.dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VerbenasDAOSQLite implements VerbenasDAO {

    Connection con;

    public VerbenasDAOSQLite(Connection con) {
        this.con = con;
    }

    @Override
    public void create(String location, String date, String orchestra) {
        String query = "INSERT INTO verbenas(lugar,data,orquesta) VALUES (?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, location);
            ps.setString(2, date);
            ps.setString(3, orchestra);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de verbenas\n");
        String query = "SELECT * FROM verbenas";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                String location = rs.getString(2);
                String date = rs.getString(3);

                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(date, inputFormat);
                DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                date = localDate.format(outputFormat);

                String orchestra = rs.getString(4);
                sb.append(String.format("(%d) %s - %s - %s\n", id, date, location, orchestra));
            }
            System.out.println(sb);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void edit(int id, String location, String date, String orchestra) {
        String query = "UPDATE verbenas SET lugar = ?, data = ?, orquesta = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, location);
            ps.setString(2, date);
            ps.setString(3, orchestra);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM verbenas WHERE id = ?";
        try(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
