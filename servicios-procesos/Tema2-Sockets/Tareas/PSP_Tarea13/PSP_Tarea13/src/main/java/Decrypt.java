import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Decrypt {
    public static void main(String[] args) {
        StringBuilder mensajeEncriptado = new StringBuilder();
        try {
            File archivo = new File("mensaje_encriptado.txt");
            Scanner scanner = new Scanner(archivo);
            while (scanner.hasNextLine()) {
                mensajeEncriptado.append(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: no se pudo encontrar el archivo.");
            return;
        }

        System.out.println("Posibles mensajes desencriptados:");
        for (int i = 0; i < 27; i++) {
            System.out.println("Salto " + i + ": " + desencriptarCesar(mensajeEncriptado.toString(), i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione el mensaje desencriptado (introduzca el número del salto): ");
        int saltoSeleccionado = scanner.nextInt();
        System.out.println("Mensaje desencriptado seleccionado: " + desencriptarCesar(mensajeEncriptado.toString(), saltoSeleccionado));
    }

    public static String desencriptarCesar(String mensajeEncriptado, int salto) {
        StringBuilder mensajeDesencriptado = new StringBuilder();
        for (int i = 0; i < mensajeEncriptado.length(); i++) {
            char caracter = mensajeEncriptado.charAt(i);
            if (Character.isLetter(caracter)) {
                char base = Character.isUpperCase(caracter) ? 'A' : 'a';
                caracter = (char) (((caracter - base - salto + 27) % 27) + base); // 27 = número de caracteres permitidos (espacio, dígitos, alfabeto)
            }
            mensajeDesencriptado.append(caracter);
        }
        return mensajeDesencriptado.toString();
    }
}
