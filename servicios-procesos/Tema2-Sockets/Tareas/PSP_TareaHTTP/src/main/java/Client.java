import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String serverAddress = "localhost"; // Dirección IP del servidor
        final int port = 8080; // Puerto del servidor

        try {
            Socket socket = new Socket(serverAddress, port);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Enviar una solicitud HTTP GET al servidor
            out.println("GET / HTTP/1.1");
            out.println("Host: " + serverAddress);
            out.println(); // Línea en blanco indica el final de la solicitud

            // Leer la respuesta del servidor
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            // Cerrar streams y socket
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
