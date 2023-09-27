package ej114;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean sesionIniciada = false;
        while (sesionIniciada == false) {
            Scanner sc = new Scanner(System.in);
            ManejoUsuarios manejador = new ManejoUsuarios();
            String usuario;
            String pwd;
            System.out.print("Usuario: ");
            usuario = sc.nextLine();
            System.out.print("Contraseña: ");
            pwd = sc.nextLine();
            sesionIniciada = manejador.comprobarUsuario(usuario, pwd);
        }
    }
}
