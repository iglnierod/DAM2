package apartado3;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class App {
    private final String DATABASE_PATH = new File("").getAbsolutePath() + "\\src\\main\\java\\apartado3\\todo.sqlite";

    public App() throws SQLException {
        printMenu();
    }

    private Connection getConnection() {
        try {
            String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS todo(
                    	id int primary key,
                    	description varchar(100) not null
                    );
                    """);
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void printMenu() throws SQLException {
        int option;
        do {
            System.out.println("\n1. Engadir tarefa");
            System.out.println("2. Ver tarefas");
            System.out.println("3. Marcar tarefa como completada");
            System.out.println("4. Cambiar a prioridade dunha tarefa");
            System.out.println("5. Saír");
            System.out.print("\nIntroduce a opción a seleccionar: ");
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (option) {
                case 1 -> addTask();
                case 2 -> showTasks();
                case 3 -> completeTask();
                case 4 -> changePriority();
                case 5 -> System.out.println("Saliendo...");
            }
        } while (option != 5);
    }


    private void changePriority() throws SQLException {
        System.out.print("Introduce a tarefa á cal desexas modificarlle a prioridade: ");
        Scanner sc = new Scanner(System.in);
        int taskID = sc.nextInt(); //3
        sc.nextLine();
        System.out.print("\nIndica a prioridade que lle queres asignar: ");
        int newPriority = sc.nextInt(); //1
        sc.nextLine();
        Connection con = getConnection();
        try {
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(String.format("UPDATE todo SET id = %d where id = %d", -1, taskID));
            if (taskID < newPriority) {
                stmt.executeUpdate(String.format("UPDATE todo SET id = id-1 where id > %d and id <= %d;", taskID, newPriority));
            } else {
                ResultSet rs = stmt.executeQuery("SELECT id FROM todo WHERE id >=1 AND id<4 ORDER BY id DESC");
                while(rs.next()) {
                    stmt.executeUpdate(String.format("UPDATE todo SET id = id+1 where id >= %d and id < %d;", newPriority, taskID));
                }
            }
            stmt.executeUpdate(String.format("UPDATE todo SET id = %d where id = -1", newPriority));
            con.commit();
            System.out.println("Orde modificada correctamente");
        } catch (SQLException e) {
            System.out.println(e);
            con.rollback();
        }
    }

    private void completeTask() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce a tarefa para marcar como completada: ");
        int taskID = sc.nextInt();
        sc.nextLine();
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(String.format("DELETE FROM todo WHERE id = %d", taskID));
            updateIDs(taskID);
            System.out.println("Tarefa marcada como completada.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateIDs(int taskID) {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(String.format("UPDATE todo SET id = id-1 WHERE id > %d", taskID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showTasks() {
        System.out.println("Lista de tarefas");
        try (Statement stmt = getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM todo ORDER BY id ASC;");
            while (rs.next()) {
                System.out.printf("%d. %s\n", rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addTask() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce a descripción da tarefa: ");
        String description = sc.nextLine();
        try (Connection con = getConnection()) {
            Statement stmt = con.createStatement();
            PreparedStatement ps = con.prepareStatement("INSERT INTO todo VALUES(?,?)");
            int lastID = getLastRow();
            ps.setInt(1, ++lastID);
            ps.setString(2, description);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getLastRow() throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id FROM todo;");
            int count = 0;
            while (rs.next()) {
                count++;
            }
            return count;
        }
    }
}
