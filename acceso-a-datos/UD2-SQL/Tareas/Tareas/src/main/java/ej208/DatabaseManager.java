package ej208;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import query.ANSI;

import java.io.File;
import java.sql.*;

public class DatabaseManager {
    private static final File DATABASE = new File(App.DIR_SQL, "gestor_musica.sqlite");

    public static boolean addSong(Song newSong) {
        String query = "INSERT INTO canciones(titulo, artista, duracion, anio) VALUES (?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, newSong.getTitle());
            ps.setString(2, newSong.getArtist());
            ps.setInt(3, newSong.getLength());
            ps.setInt(4, newSong.getYear());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            while (keys.next()) {
                int id = keys.getInt(1);
                newSong.setId(id);
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addUser(User newUser) {
        String query = "INSERT INTO usuarios(nombre_usuario, nombre, correo) VALUES (?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getUser());
            ps.setString(3, newUser.getEmail());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            while (keys.next()) {
                int id = keys.getInt(1);
                newUser.setId(id);
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void buildDatabase() {
        try {
            executeSqlScript(getConnection(), new File(App.DIR_SQL, "crear.sql"));
            printWarning("Se ha creado la base de datos");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean databaseExists() {
        return DATABASE.exists();
    }

    private static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:sqlite:%s", DATABASE.getAbsolutePath());
        return DriverManager.getConnection(url);
    }

    public static void printWarning(String mensaje) {
        System.out.println(ANSI.YELLOW_BACKGROUND + ANSI.BLACK + mensaje + ANSI.RESET);
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
