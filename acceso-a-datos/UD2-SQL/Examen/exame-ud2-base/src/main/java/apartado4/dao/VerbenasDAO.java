package apartado4.dao;

import java.sql.Connection;

public interface VerbenasDAO {
    public void create(String location, String date, String orchestra);

    public void print();

    public void edit(int id, String location, String date, String orchestra);

    public void delete(int id);
}
