package ej208;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.io.File;
import java.sql.*;

public class DatabaseManager {
    private static final File DATABASE = new File(App.DIR_SQL, "gestor_musica.sqlite");

    public static boolean addSong(Song newSong) {
        String query = "INSERT INTO canciones(id, titulo, artista, duracion, anio) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, newSong.getId());
            ps.setString(2, newSong.getTitle());
            ps.setString(3, newSong.getArtist());
            ps.setInt(4, newSong.getLength());
            ps.setInt(5, newSong.getYear());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addUser(User newUser) {
        String query = "INSERT INTO usuarios(id, nombre_usuario, nombre, correo) VALUES (?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, newUser.getId());
            ps.setString(2, newUser.getUsername());
            ps.setString(3, newUser.getUser());
            ps.setString(4, newUser.getEmail());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void buildDatabase() {
        try {
            executeSqlScript(getConnection(), new File(App.DIR_SQL, "crear.sql"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:sqlite:%s", DATABASE.getAbsolutePath());
        return DriverManager.getConnection(url);
    }

    private static void executeSqlScript(Connection con, File file) {
        String path = file.getAbsolutePath();
        boolean continueOrError = false;
        boolean ignoreFailedDrops = false;
        String commentPrefix = "--";
        String separator = ";";
        String blockCommentStartDelimiter = "/*";
        String blockCommentEndDelimiter = "*/";

        ScriptUtils.executeSqlScript(
                con,
                new EncodedResource(new PathResource(path)),
                continueOrError,
                ignoreFailedDrops,
                commentPrefix,
                separator,
                blockCommentStartDelimiter,
                blockCommentEndDelimiter
        );
    }
}
