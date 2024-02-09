package ej208;

import query.ANSI;

import java.util.ArrayList;
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

    public Playlist getPlaylist(int playlistId) {
        Playlist p = this.playlists.get(playlistId);
        if(p == null) {
            DatabaseManager.printError("La playlist con id: " + playlistId + " no existe");
        }
        return p;
    }

    public static HashMap<Integer, Playlist> getAllPlaylists() {
        return DatabaseManager.getAllPlaylists();
    }

    public String getPlaylistsList(int userId) {
        ArrayList<Playlist> userPlaylists = getPlaylistsByUser(userId);
        if (userPlaylists.size() == 0) {
            DatabaseManager.printError("No tiene ninguna playlist");
            return null;
        }
        StringBuilder playlistsList = new StringBuilder();
        for (Playlist p : userPlaylists) {
            playlistsList.append(String.format("%s%s[%2d] %-20s %s\n", ANSI.PURPLE_BACKGROUND, ANSI.BLACK, p.getId(), p.getName(), ANSI.RESET));
        }
        return playlistsList.toString();
    }

    private ArrayList<Playlist> getPlaylistsByUser(int userId) {
        ArrayList<Playlist> userPlaylists = new ArrayList<>();
        for (Playlist p : playlists.values())
            if (p.getUser() == userId)
                userPlaylists.add(p);
        return userPlaylists;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Playlist s : playlists.values())
            sb.append(s.toString()).append("\n");
        return sb.toString();
    }

    public void delete(int playlistId) {
        if(DatabaseManager.deletePlaylist(playlistId)) {
            this.playlists.remove(playlistId);
            DatabaseManager.printWarning("Se ha eliminado la playlist");
        } else {
            DatabaseManager.printError("No se ha podido eliminar la playlist");
        }
    }
}
