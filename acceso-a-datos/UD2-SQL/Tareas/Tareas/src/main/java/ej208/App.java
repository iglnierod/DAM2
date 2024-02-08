package ej208;

import java.io.File;
import java.sql.SQLOutput;
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
        this.dataFile = dataFile;
        this.users = new Users();
        this.songs = new Songs();
        this.playlists = new Playlists();

        boolean isDataLoaded = DatabaseManager.check();
        if (!isDataLoaded) {
            loadData();
        }
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
        }
    }

    private void printMenu() {
        int option;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n1. Cargar canciones desde JSON");
            System.out.println("2. Insertar canción");
            System.out.println("3. Crear usuario");
            System.out.println("4. Crear lista de reproducción");
            System.out.println("9. Salir");
            System.out.print("Opción: ");
            option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (option) {
                case 1 -> loadData(); // Cargar canciones desde JSON
                case 2 -> addSong(); // Insertar canción
                case 3 -> createUser(); // Crear usuario
                case 4 -> createPlaylist(); // Crear lista de reproducción
                case 9 -> System.out.println("Saliendo...");
            }
        } while (option != 9);
    }

    private void createPlaylist() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Crear lista de reproducción:");
        System.out.print("Nombre: ");
        String name = sc.nextLine();

        this.playlists.add(new Playlist(name, this.currentUser));
    }

    // MENU OPTIONS
    private void loadData() {
        DatabaseManager.printInformation("Leyendo fichero json: " + this.dataFile.getAbsolutePath());
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
}
