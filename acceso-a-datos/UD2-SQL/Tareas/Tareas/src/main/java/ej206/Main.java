package ej206;

import java.util.Scanner;

public class Main {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    public static final String USER = "root";
    public static final String PASS = "abc123.";
    public static final String BD = "empleados2";


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        do {
            printMenu();
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (opcion) {
                case 1:
                    System.out.print("Introduzca la primera letra del nombre: ");
                    String primeraLetra = sc.nextLine();
                    System.out.println();
                    ConsultaNombres.empiezaPor(primeraLetra);
                    break;
                case 2:
                    System.out.print("Introduzca el ID del empleado: ");
                    String id = sc.nextLine();
                    System.out.println();
                    BorradoEmpleados.eliminarID(id);
                    break;
                case 3:
                    System.out.print("Introduzca una comision: ");
                    String comision = sc.nextLine();
                    System.out.println();
                    ConsultaComision.tiene(comision);
                    break;
                case 4:
                    System.out.print("Introduzca el ID del departamento: ");
                    id = sc.nextLine();
                    System.out.println();
                    BorradoDepartamento.eliminarID(id);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("ERROR: Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Consultar por letra del nombre");
        System.out.println("2. Eliminar empleado por ID");
        System.out.println("3. Consultar por comisión");
        System.out.println("4. Eliminar departamento por ID");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
