import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int port = 8080;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor conectado, esperando a por clientes");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket);

            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);

            String clientMessage = in.readLine();
            System.out.println("Cliente dice: " + clientMessage);

            String htmlContent = readFile(new File("web.html"));

            String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\"" + htmlContent;
            outputStream.write(response.getBytes());

            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFile(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null)
                sb.append(line);
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
