package ej208;

import java.util.HashMap;

public class Playlists {
    private HashMap<Integer, Playlist> playlists;

    public Playlists() {
        playlists = new HashMap<>();
    }

    public void add(Playlist newPlaylist) {
        if (DatabaseManager.addPlaylist(newPlaylist)) {
            this.playlists.put(newPlaylist.getId(), newPlaylist);
        }
    }
}
