package ej208;

import java.util.HashMap;

public class Users {
    private HashMap<Integer, User> users;

    public Users() {
        users = new HashMap<>();
    }

    public Users(HashMap<Integer, User> users) {
        this.users = users;
    }

    public User get(int id) {
        return this.users.get(id);
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
}