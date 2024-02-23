package ej208;

import ej208.playlist.Playlist;
import ej208.song.Song;
import ej208.user.User;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import query.ANSI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

            printInformation("Se ha añadido la canción: [" + newSong.getId() + "] " + newSong.getTitle());
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

            printInformation("Se ha añadido el usuario: [" + newUser.getId() + "] " + newUser.getUsername());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            DatabaseManager.printError("No se ha podido añadir el usuario");
            return false;
        }
    }

    public static boolean addPlaylist(Playlist newPlaylist) {
        String query = "INSERT INTO listas_reproduccion(nombre_lista, id_usuario) VALUES (?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, newPlaylist.getName());
            ps.setInt(2, newPlaylist.getUser());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            while (keys.next()) {
                int id = keys.getInt(1);
                newPlaylist.setId(id);
            }

            printInformation("Se ha añadido la playlist: [" + newPlaylist.getId() + "] " + newPlaylist.getName());
            return true;
        } catch (SQLException e) {
            printError("No se ha podido añadir la playlist");
            return false;
        }
    }

    public static void buildDatabase() {
        try {
            executeSqlScript(getConnection(), new File(App.DIR_SQL, "crear.sql"));
            printInformation("Se ha creado la base de datos");
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


    public static boolean check() {
        try {
            Path path = Paths.get(DATABASE.getAbsolutePath());
            if (Files.exists(path)) {
                printInformation("La base de datos ya existe.");
                printWarningInput("Desea elminarla [S/N]:");
                Scanner sc = new Scanner(System.in);
                char c = sc.nextLine().charAt(0);
                if (c == 's' || c == 'S') {
                    Files.delete(path);
                    printWarning("Se ha borrado la base de datos");
                } else {
                    return true;
                }
            }
            buildDatabase();
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public static void printWarning(String message) {
        message = " WARNING: " + message + " ";
        System.out.println(ANSI.YELLOW_BACKGROUND + ANSI.BLACK + message + ANSI.RESET);
    }

    public static void printInformation(String message) {
        message = String.format(" INFORMATION: %s ", message);
        System.out.println(ANSI.BLUE_BACKGROUND + ANSI.BLACK + message + ANSI.RESET);
    }

    public static void printWarningInput(String message) {
        message = String.format(" WARNING: %s ", message);
        System.out.print(ANSI.YELLOW_BACKGROUND + ANSI.BLACK + message + ANSI.RESET + " ");
    }

    public static void printError(String message) {
        message = String.format(" ERROR: %s ", message);
        System.out.println(ANSI.RED_BACKGROUND + ANSI.BLACK + message + ANSI.RESET);
    }

    public static HashMap<Integer, User> getAllUsers() {
        HashMap<Integer, User> users = new HashMap<>();
        try (Statement stmt = getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("nombre_usuario"),
                        rs.getString("nombre"),
                        rs.getString("correo")
                );
                users.put(u.getId(), u);
            }
            return users;
        } catch (SQLException e) {
            printError("No se han podido cargar los usuarios de la base de datos");
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public static HashMap<Integer, Song> getAllSongs() {
        HashMap<Integer, Song> songs = new HashMap<>();
        try (Statement stmt = getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM canciones");
            while (rs.next()) {
                Song s = new Song(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("artista"),
                        rs.getInt("duracion"),
                        rs.getInt("anio")
                );
                songs.put(s.getId(), s);
            }
            return songs;
        } catch (SQLException e) {
            printError("No se han podido cargar las canciones de la base de datos");
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public static HashMap<Integer, Playlist> getAllPlaylists() {
        HashMap<Integer, Playlist> playlists = new HashMap<>();
        try (Statement stmt = getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM listas_reproduccion");
            while (rs.next()) {
                int id = rs.getInt("id");
                Playlist s = new Playlist(
                        id,
                        rs.getString("nombre_lista"),
                        rs.getInt("id_usuario"),
                        getAllSongsInPlaylist(id)
                );
                playlists.put(s.getId(), s);
            }
            return playlists;
        } catch (SQLException e) {
            printError("No se han podido cargar las playlist de la base de datos");
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public static ArrayList<Integer> getAllSongsInPlaylist(int playlistId) {
        String query = "SELECT id_cancion FROM listas_canciones WHERE id_lista = ?";
        ArrayList<Integer> songs = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, playlistId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                songs.add(rs.getInt("id_cancion"));
            }
            return songs;
        } catch (SQLException e) {
            printError("No se han podido cargar las canciones de las playlist de la base de datos");
            return new ArrayList<>();
        }
    }

    public static boolean addSongToPlaylist(int playlistId, int songId) {
        String query = "INSERT INTO listas_canciones VALUES(?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, playlistId);
            ps.setInt(2, songId);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            printError("No se ha podido añadir la canción a la playlist en la base de datos");
            return false;
        }
    }

    public static boolean deletePlaylist(int playlistId) {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(String.format("DELETE FROM listas_canciones WHERE id_lista = %d", playlistId));
            stmt.executeUpdate(String.format("DELETE FROM listas_reproduccion WHERE id = %d", playlistId));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
