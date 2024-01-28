package ej203;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class CrearBaseDatos {
    public static Connection crearBD(String url, String fileName) {
        try {
            url+=fileName;
            System.out.println(url);
            File SQLiteFile = new File(fileName);
            if (SQLiteFile.exists()) {
                System.out.println("La base de datos ya existe.");
                System.out.print("¿Desea eliminarla [s/n]: ");
                Scanner sc = new Scanner(System.in);
                char c = sc.nextLine().charAt(0);
                if (c == 's' || c == 'S') {
                    if (SQLiteFile.delete()) {
                        System.out.println("Se ha eliminado la base de datos: " + fileName);
                    } else {
                        System.out.println("No se pudo eliminar la base de datos.");
                    }
                }
            }
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
