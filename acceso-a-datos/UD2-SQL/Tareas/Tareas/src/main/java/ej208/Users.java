package ej208;

import query.ANSI;

import java.util.Collection;
import java.util.HashMap;

public class Users {
    private HashMap<Integer, User> users;

    public Users() {
        this.users = getAllUsers();
    }

    public Users(HashMap<Integer, User> users) {
        if (users == null) {
            this.users = new HashMap<>();
        } else {
            this.users = users;
        }
    }

    public User get(int id) {
        return this.users.get(id);
    }

    public User get(String username) {
        for (User u : users.values()) {
            if (u.getUsername().equals(username))
                return u;
        }
        return null;
    }

    public void add(User newUser) {
        if (DatabaseManager.addUser(newUser)) {
            this.users.put(newUser.getId(), newUser);
        }
    }

    public void remove(int id) {
        this.users.remove(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User u : users.values())
            sb.append(u.toString()).append("\n");
        return sb.toString();
    }

    public static HashMap<Integer, User> getAllUsers() {
        return DatabaseManager.getAllUsers();
    }

    public void printAllUsers() {
        System.out.println(ANSI.WHITE_BACKGROUND + ANSI.BLACK + "USUARIOS: " + ANSI.RESET);
        System.out.println(ANSI.WHITE_BACKGROUND + ANSI.BLACK + String.format(" %-10s %-25s %-20s %-20s", "ID", "NOMBRE DE USUARIO", "NOMBRE", "EMAIL") + ANSI.RESET);

        for (User u : this.users.values()) {
            System.out.println(ANSI.WHITE_BACKGROUND + ANSI.BLACK + String.format(" %-10s %-25s %-20s %-20s", u.getId(), u.getUsername(), u.getUser(), u.getEmail()) + ANSI.RESET);
        }
    }

    public Collection<User> getUsersCollection() {
        return this.users.values();
    }
}
