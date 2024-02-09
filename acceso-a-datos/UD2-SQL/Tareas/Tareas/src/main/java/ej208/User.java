package ej208;

import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String user;
    private String email;
    private ArrayList<Integer> playlists;
    public User() {
        this.playlists = new ArrayList<>();
    }

    public User(int id, String username, String user, String email) {
        this.id = id;
        this.username = username;
        this.user = user;
        this.email = email;
        this.playlists = new ArrayList<>();
    }

    public User(String username, String user, String email) {
        this.id = -1;
        this.username = username;
        this.user = user;
        this.email = email;
        this.playlists = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", user='" + user + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
