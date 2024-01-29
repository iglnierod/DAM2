import java.io.*;
import java.net.*;

public class Server {
    private static final int puerto = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor conectado, esperando a por clientes");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket);

            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);

            out.println("Se ha conectado al servidor.");
            out.println("Introduzca dos números para realizar una operación");

            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            out.print("Introduzca el primer número: ");
            inputStream = clientSocket.getInputStream();
            System.out.println(inputStream);
            Double n = Double.parseDouble(in.readLine());
            out.print("Introduzca el segundo número: ");
            Double m = Double.parseDouble(in.readLine());

            out.println(sumar(n, m));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String sumar(double n, double m) {
        return String.valueOf(n + m);
    }

    private static String restar(double n, double m) {
        return String.valueOf(n - m);
    }

    private static String multiplicar(double n, double m) {
        return String.valueOf(n * m);
    }

    private static String dividir(double n, double m) {
        return String.valueOf(n / m);
    }
}
