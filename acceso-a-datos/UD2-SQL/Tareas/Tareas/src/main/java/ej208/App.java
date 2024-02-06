package ej208;

import java.io.File;
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
        printMenu();
    }

    private void printMenu() {
        int option;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n1. Cargar canciones desde JSON");
            System.out.println("9. Salir");
            System.out.print("Opción: ");
            option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (option) {
                case 1:
                    System.out.println("Leyendo fichero json: " + this.dataFile.getAbsolutePath());
                    FileHandler.loadData(this.dataFile, this.songs, this.users);
                    //
                    System.out.println("Songs:");
                    System.out.println(songs);
                    System.out.println("Users:");
                    System.out.println(users);
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (option != 9);
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Songs getSongs() {
        return songs;
    }

    public void setSongs(Songs songs) {
        this.songs = songs;
    }

    public File getDataFile() {
        return dataFile;
    }

    public void setDataFile(File dataFile) {
        this.dataFile = dataFile;
    }
}
