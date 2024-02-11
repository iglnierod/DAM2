package ej208;

import query.ANSI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Playlists {
    private HashMap<Integer, Playlist> playlists;

    public Playlists() {
        playlists = getAllPlaylists();
    }

    public void assignPlaylistsToUsers(Users users) {
        for (Playlist p : playlists.values()) {
            User u = users.get(p.getUser());
            u.addToPlaylists(p.getId());
        }
    }

    public void add(Playlist newPlaylist, User user) {
        if (DatabaseManager.addPlaylist(newPlaylist)) {
            int newPlaylistId = newPlaylist.getId();
            this.playlists.put(newPlaylistId, newPlaylist);
            user.addToPlaylists(newPlaylistId);
        }
    }

    public Playlist getPlaylist(int playlistId, int currentUserId) {
        Playlist p = this.playlists.get(playlistId);
        if (p == null) {
            DatabaseManager.printError("La playlist con id: " + playlistId + " no existe");
        } else if (p.getUser() != currentUserId) {
            p = null;
            DatabaseManager.printError("No se ha podido añadir la canción a la playlist");
        }
        return p;
    }

    public Playlist getPlaylist(int playlistId) {
        return this.playlists.get(playlistId);
    }

    public static HashMap<Integer, Playlist> getAllPlaylists() {
        return DatabaseManager.getAllPlaylists();
    }

    public String getPlaylistsList(int userId) {
        ArrayList<Playlist> userPlaylists = getPlaylistsByUser(userId);
        if (userPlaylists.isEmpty()) {
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

    public void delete(int playlistId, User user) {
        if (this.playlists.get(playlistId).getUser() == user.getId()) {
            if (DatabaseManager.deletePlaylist(playlistId)) {
                this.playlists.remove(playlistId);
                user.deletePlaylist(playlistId);
                DatabaseManager.printWarning("Se ha eliminado la playlist");
                return;
            }
        }
        DatabaseManager.printError("No se ha podido eliminar la playlist");
    }

    public void printAllPlaylists() {
        System.out.println(ANSI.WHITE_BACKGROUND + ANSI.BLACK + "PLAYLISTS: " + ANSI.RESET);
        System.out.println(ANSI.WHITE_BACKGROUND + ANSI.BLACK + String.format(" %-10s %-25s %10s ", "ID", "NOMBRE", "USER") + ANSI.RESET);
        for(Playlist p : playlists.values()) {
            System.out.println(ANSI.WHITE_BACKGROUND + ANSI.BLACK + String.format(" %-10s %-25s %10s ", p.getId(), p.getName(), p.getUser()) + ANSI.RESET);
        }
    }

    public Collection<Playlist> getPlaylistsCollection() {
        return this.playlists.values();
    }
}
