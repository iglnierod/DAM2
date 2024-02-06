package ej208;

import java.util.HashMap;

public class Songs {
    private HashMap<Integer, Song> songs;

    public Songs() {
        this.songs = new HashMap<>();
    }

    public Songs(HashMap<Integer, Song> songs) {
        this.songs = songs;
    }

    public Song get(int id) {
        return songs.get(id);
    }

    public void add(Song newSong) {
        this.songs.put(newSong.getId(), newSong);
        if(DatabaseManager.addSong(newSong))
            System.out.println("Añadida cancion a la bd");
    }

    public void remove(int id) {
        this.songs.remove(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Song s : songs.values())
            sb.append(s.toString()).append("\n");
        return sb.toString();
    }
}
