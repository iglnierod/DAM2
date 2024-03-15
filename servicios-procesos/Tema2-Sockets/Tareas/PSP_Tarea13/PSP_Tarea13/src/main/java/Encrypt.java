import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encrypt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el mensaje a encriptar: ");
        String mensaje = scanner.nextLine();
        System.out.print("Introduce el salto: ");
        int salto = scanner.nextInt();
        scanner.nextLine();

        String mensajeEncriptado = encriptarCesar(mensaje, salto);

        try {
            FileWriter writer = new FileWriter("mensaje_encriptado.txt");
            writer.write(mensajeEncriptado);
            writer.close();
            System.out.println("Mensaje encriptado guardado en mensaje_encriptado.txt");
        } catch (IOException e) {
            System.out.println("Error al guardar el mensaje encriptado: " + e.getMessage());
        }
    }

    public static String encriptarCesar(String mensaje, int salto) {
        StringBuilder mensajeEncriptado = new StringBuilder();
        for (int i = 0; i < mensaje.length(); i++) {
            char caracter = mensaje.charAt(i);
            if (Character.isLetter(caracter)) {
                char base = Character.isUpperCase(caracter) ? 'A' : 'a';
                caracter = (char) (((caracter - base + salto) % 27) + base); // 27 = número de caracteres permitidos (espacio, dígitos, alfabeto)
            }
            mensajeEncriptado.append(caracter);
        }
        return mensajeEncriptado.toString();
    }
}
