package ej208;

import java.util.ArrayList;

public class Playlist {
    private int id;
    private String name;
    private int user;
    private ArrayList<Integer> songs;

    public Playlist(String name, int user) {
        this.id = -1;
        this.name = name;
        this.user = user;
        this.songs = new ArrayList<>();
    }

    public Playlist(String name, int user, ArrayList<Integer> songs) {
        this.id = -1;
        this.name = name;
        this.user = user;
        this.songs = songs;
    }

    public Playlist(int id, String name, int user, ArrayList<Integer> songs) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.songs = songs;
    }

    public void addSong(int songId) {
        this.songs.add(songId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setSongs(ArrayList<Integer> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator=" + user +
                ", songs=" + songs +
                '}';
    }
}
