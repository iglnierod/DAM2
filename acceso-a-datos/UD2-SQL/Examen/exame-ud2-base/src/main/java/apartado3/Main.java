package apartado3;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // FALLA AL ORDENAR AVECES
            App app = new App();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
