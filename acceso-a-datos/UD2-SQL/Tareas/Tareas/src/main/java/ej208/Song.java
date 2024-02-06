package ej208;

public class Song {
    private int id;
    private String title;
    private String artist;
    private int length;
    private int year;

    public Song(int id, String title, String artist, int length, int year) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.length = length;
        this.year = year;
    }

    public Song(String title, String artist, int length, int year) {
        this.id = -1;
        this.title = title;
        this.artist = artist;
        this.length = length;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Song{" + "id=" + id + ", title='" + title + '\'' + ", artist='" + artist + '\'' + ", length=" + length + ", year=" + year + '}';
    }
}
