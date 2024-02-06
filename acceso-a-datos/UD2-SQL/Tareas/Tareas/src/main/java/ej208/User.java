package ej208;

public class User {
    private int id;
    private String username;
    private String user;
    private String email;

    public User() {

    }

    public User(int id, String username, String user, String email) {
        this.id = id;
        this.username = username;
        this.user = user;
        this.email = email;
    }

    public User(String username, String user, String email) {
        this.id = -1;
        this.username = username;
        this.user = user;
        this.email = email;
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
