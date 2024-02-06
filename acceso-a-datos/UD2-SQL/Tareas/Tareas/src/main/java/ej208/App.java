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
    private File dataFile;

    public App() {
        dataFile = new File(DIR_SQL, "canciones-usuarios.json");
        new App(dataFile);
    }

    public App(File dataFile) {
        this.dataFile = dataFile;
        this.users = new Users();
        this.songs = new Songs();

        DatabaseManager.buildDatabase();
//        if (DatabaseManager.databaseExists()) {
//            printMenu();
//        } else {
//            DatabaseManager.buildDatabase();
//        }
    }

    private void login() {

    }

    private void printMenu() {
        int option;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n1. Cargar canciones desde JSON");
            System.out.println("2. Insertar canción");
            System.out.println("9. Salir");
            System.out.print("Opción: ");
            option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (option) {
                case 1 -> loadData(); // Cargar canciones desde JSON
                case 2 -> addSong(); // Insertar canción
                case 9 -> System.out.println("Saliendo...");
            }
        } while (option != 9);
    }

    // MENU OPTIONS
    private void loadData() {
        System.out.println("Leyendo fichero json: " + this.dataFile.getAbsolutePath());
        FileHandler.loadData(this.dataFile, this.songs, this.users);
        //
        System.out.println("Songs:");
        System.out.println(songs);
        System.out.println("Users:");
        System.out.println(users);
    }

    private void addSong() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
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
}
