package ej208.song;

import ej208.DatabaseManager;
import query.ANSI;

import java.util.Collection;
import java.util.HashMap;

public class Songs {
    private HashMap<Integer, Song> songs;

    public Songs() {
        this.songs = getAllSongs();
    }

    public Songs(HashMap<Integer, Song> songs) {
        this.songs = songs;
    }

    public Song get(int id) {
        return songs.get(id);
    }

    public void add(Song newSong) {
        if (DatabaseManager.addSong(newSong)) {
            this.songs.put(newSong.getId(), newSong);
        }
    }

    public void remove(int id) {
        this.songs.remove(id);
    }

    public String getSongsList() {
        Collection<Song> songs = this.songs.values();
        if (songs.isEmpty()) {
            DatabaseManager.printError("No hay ninguna canción.");
            return null;
        }

        StringBuilder songsList = new StringBuilder();
        for (Song s : songs) {
            System.out.printf("%s%s[%2d] %-20s - %-15s (%4d)%s\n", ANSI.PURPLE_BACKGROUND, ANSI.BLACK, s.getId(), s.getTitle(), s.getArtist(), s.getYear(), ANSI.RESET);
        }
        return songsList.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Song s : songs.values())
            sb.append(s.toString()).append("\n");
        return sb.toString();
    }

    public void printAllSongs() {
        System.out.println(ANSI.WHITE_BACKGROUND + ANSI.BLACK + "CANCIONES: " + ANSI.RESET);
        System.out.printf("%s%s %-10s %-30s %-25s %10s %10s %s%n", ANSI.WHITE_BACKGROUND, ANSI.BLACK, "ID", "TITULO", "ARTISTA", "DURACION", "AÑO", ANSI.RESET);
        for (Song s : songs.values()) {
            System.out.printf("%s%s %-10s %-30s %-25s %10s %10s %s%n", ANSI.WHITE_BACKGROUND, ANSI.BLACK, s.getId(), s.getTitle(), s.getArtist(), s.getLength(), s.getYear(), ANSI.RESET);
        }
    }

    public Collection<Song> getSongsCollection() {
        return this.songs.values();
    }

    public static HashMap<Integer, Song> getAllSongs() {
        return DatabaseManager.getAllSongs();
    }
}
