package apartado4;

import apartado4.dao.VerbenasDAO;
import apartado4.dao.VerbenasDAOMySQL;
import apartado4.dao.VerbenasDAOSQLite;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    private final String PROJECT_PATH = new File("").getAbsolutePath();
    private static final DBM DB_MANAGER = DBM.SQLITE;
    private final String DATABASE_PATH = PROJECT_PATH + "\\src\\main\\java\\apartado4\\verbenas.sqlite";

    private VerbenasDAO dao;

    public App() {
        switch (DB_MANAGER) {
            case MYSQL -> dao = new VerbenasDAOMySQL(getConnection());
            case SQLITE -> dao = new VerbenasDAOSQLite(getConnection());
        }
        executeSqlScript();
        printMenu();
    }

    private void printMenu() {
        int option;
        do {
            System.out.println("\n1. Engadir verbena");
            System.out.println("2. Ver verbenas");
            System.out.println("3. Actualizar información dunha verbena");
            System.out.println("4. Eliminar verbena");
            System.out.println("5. Saír");
            System.out.print("\nIntroduce a opción a seleccionar: ");
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (option) {
                case 1 -> addVerbena();
                case 2 -> showVerbenas();
                case 3 -> editVerbena();
                case 4 -> deleteVerbena();
                case 5 -> System.out.println("Saliendo...");
            }
        } while (option != 5);
    }

    private void deleteVerbena() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Indica o ID da verbena a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        dao.delete(id);
    }

    private void editVerbena() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Indica o ID da verbena a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("- Lugar: ");
        String location = sc.nextLine();
        System.out.print("- Data (DD/MM/YYYY): ");
        String date = sc.nextLine();
        System.out.print("- Orquesta: ");
        String orchestra = sc.nextLine();
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, inputFormat);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dao.edit(id, location, localDate.format(outputFormat), orchestra);
    }

    private void showVerbenas() {
        dao.print();
    }

    private void addVerbena() {
        Scanner sc = new Scanner(System.in);
        System.out.print("- Lugar: ");
        String location = sc.nextLine();
        System.out.print("- Data (DD/MM/YYYY): ");
        String date = sc.nextLine();
        System.out.print("- Orquesta: ");
        String orchestra = sc.nextLine();
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, inputFormat);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dao.create(location, localDate.format(outputFormat), orchestra);
    }

    private Connection getConnection() {
        if (DB_MANAGER == DBM.MYSQL) {
            String url = String.format("jdbc:mysql://%s:%s", "127.0.0.1", "3306");
            try {
                Connection connection = DriverManager.getConnection(url, "root", "abc123.");
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS fiestas");
                stmt.executeUpdate("USE fiestas;");
                return connection;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
                return DriverManager.getConnection(url);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void executeSqlScript() {
        Connection con = getConnection();
        File file;
        if (DB_MANAGER == DBM.MYSQL) {
            file = new File(PROJECT_PATH, "\\assets\\verbenas_mysql.sql");
        } else {
            file = new File(PROJECT_PATH, "\\assets\\verbenas_sqlite.sql");
        }
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(getText(file));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getText(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
