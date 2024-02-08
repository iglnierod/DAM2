package ej208;

import java.util.HashMap;

public class Playlists {
    private HashMap<Integer, Playlist> playlists;

    public Playlists() {
        playlists = getAllPlaylists();
    }

    public void add(Playlist newPlaylist) {
        if (DatabaseManager.addPlaylist(newPlaylist)) {
            this.playlists.put(newPlaylist.getId(), newPlaylist);
        }
    }

    public static HashMap<Integer, Playlist> getAllPlaylists() {
        return DatabaseManager.getAllPlaylists();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Playlist s : playlists.values())
            sb.append(s.toString()).append("\n");
        return sb.toString();
    }
}
