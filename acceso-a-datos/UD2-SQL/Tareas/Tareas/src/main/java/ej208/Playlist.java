package ej208;

public class Playlist {
    private int id;
    private String name;
    private User creator;
    private Songs songs;

    public Playlist(String name, User creator) {
        this.id = -1;
        this.name = name;
        this.creator = creator;
        this.songs = new Songs();
    }

    public Playlist(String name, User creator, Songs songs) {
        this.id = -1;
        this.name = name;
        this.creator = creator;
        this.songs = songs;
    }

    public void addSong(Song newSong) {
        this.songs.add(newSong);
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Songs getSongs() {
        return songs;
    }

    public void setSongs(Songs songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", songs=" + songs +
                '}';
    }
}
