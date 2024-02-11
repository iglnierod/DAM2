package ej208;

import java.io.File;
import java.util.Scanner;

public class App {
    public static final String PATH_PROJECT = new File("").getAbsolutePath();
    public static final File DIR_SQL = new File(PATH_PROJECT, "gestor_musica");
    private Users users;
    private User currentUser;
    private Songs songs;
    private Playlists playlists;
    private File dataFile;

    public App() {
        dataFile = new File(DIR_SQL, "canciones-usuarios.json");
        new App(dataFile);
    }

    public App(File dataFile) {
        boolean isDataLoaded = DatabaseManager.check();
        this.dataFile = dataFile;
        this.users = new Users();
        this.songs = new Songs();
        this.playlists = new Playlists();

        if (!isDataLoaded) {
            loadData();
        }

        this.playlists.assignPlaylistsToUsers(this.users);

        login();
    }

    private void login() {
        System.out.println("Inicio de sesión");
        System.out.print("Nombre de usuario: ");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        User user = users.get(userName);
        if (user != null) {
            this.currentUser = user;
            DatabaseManager.printInformation("Se ha iniciado sesión como " + userName);
            printMenu();
        } else {
            DatabaseManager.printError("El usuario no existe");
            login();
        }
    }

    private void printMenu() {
        int option;
        do {
            System.out.println("\n1. Cargar canciones desde JSON");
            System.out.println("2. Insertar canción");
            System.out.println("3. Crear usuario");
            System.out.println("4. Crear playlist");
            System.out.println("5. Añadir canción a playlist");
            System.out.println("6. Eliminar playlist");
            System.out.println("7. Ver datos");
            System.out.println("8. Exportar datos a XML");
            System.out.println("9. Salir");
            System.out.print("Opción: ");
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (option) {
                case 1 -> loadData();
                case 2 -> addSong();
                case 3 -> createUser();
                case 4 -> createPlaylist();
                case 5 -> addSongToPlaylist();
                case 6 -> deletePlaylist();
                case 7 -> printData();
                case 8 -> exportDataToXML();
                case 9 -> System.out.println("Saliendo...");
            }
        } while (option != 9);
    }

    // MENU OPTIONS
    private void loadData() {
        DatabaseManager.printInformation("Leyendo fichero JSON: " + this.dataFile.getAbsolutePath());
        FileHandler.loadData(this.dataFile, this.songs, this.users);
    }

    private void addSong() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Añadir canción:");
        System.out.print("Título: ");
        String title = sc.nextLine();
        System.out.print("Artista: ");
        String artist = sc.nextLine();
        System.out.print("Duración (segundos): ");
        int length = sc.nextInt();
        sc.nextLine();
        System.out.print("Año lanzamiento (YYYY): ");
        int year = sc.nextInt();
        sc.nextLine();

        this.songs.add(new Song(title, artist, length, year));
    }

    private void createUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Crear usuario:");
        System.out.print("Nombre de usuario: ");
        String username = sc.nextLine();
        System.out.print("Nombre: ");
        String user = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        this.users.add(new User(username, user, email));
    }

    private void createPlaylist() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Crear lista de reproducción:");
        System.out.print("Nombre: ");
        String name = sc.nextLine();

        this.playlists.add(
                new Playlist(name, this.currentUser.getId()),
                this.currentUser
        );
    }

    private void addSongToPlaylist() {
        System.out.println("Añadir cancion a playlist:");
        String songsList = this.songs.getSongsList();
        if (songsList == null) {
            return;
        }
        System.out.print("Introduzca el [id] de la canción: ");
        Scanner sc = new Scanner(System.in);
        int songId = sc.nextInt();
        sc.nextLine();
        System.out.println("Seleccione a que playlist añadirla: ");
        String playlistsList = this.playlists.getPlaylistsList(this.currentUser.getId());
        if (playlistsList == null) {
            return;
        }
        System.out.println(playlistsList);
        System.out.print("Introduza el [id] de la playlist: ");
        int playlistId = sc.nextInt();
        System.out.println();
        sc.nextLine();
        Playlist p = this.playlists.getPlaylist(playlistId, this.currentUser.getId());
        if (p == null) {
            return;
        }
        p.addSong(songId);
    }

    private void deletePlaylist() {
        System.out.println("Eliminar una playlist:");
        String playlistsList = this.playlists.getPlaylistsList(this.currentUser.getId());
        if (playlistsList == null) {
            return;
        }
        System.out.println(playlistsList);
        System.out.print("Introduza el [id] de la playlist: ");
        Scanner sc = new Scanner(System.in);
        int playlistId = sc.nextInt();
        System.out.println();
        sc.nextLine();
        DatabaseManager.printWarningInput("Desea eliminar la playlist con id [" + playlistId + "] (S/N): ");
        char c = sc.nextLine().charAt(0);
        if (c == 's' || c == 'S') {
            this.playlists.delete(playlistId, this.currentUser);
        }
    }

    private void printData() {
        this.songs.printAllSongs();
        System.out.println();
        this.users.printAllUsers();
        System.out.println();
        this.playlists.printAllPlaylists();
    }

    private void exportDataToXML() {
        DatabaseManager.printInformation("Se están exportando los datos a XML...");
        FileHandler.exportDataToXML(this.songs, this.users, this.playlists);
        DatabaseManager.printInformation("Se han exportado los datos al archivo: " + new File(DIR_SQL, "data.xml").getAbsolutePath());
    }
}
