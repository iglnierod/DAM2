package chrome;

import java.io.*;
import java.net.Socket;

public class ServerSocket {
    public static void main(String[] args) {
        final int portNumber = 8080;

        try {
            java.net.ServerSocket serverSocket = new java.net.ServerSocket(portNumber);
            System.out.println("Server conectado, esperando clientes...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket);

            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);

            String clientMessage = in.readLine();
            System.out.println("Cliente dice: " + clientMessage);

            String htmlContent = readHtmlFromFile(new File("web.html"));
            String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n" + htmlContent;
            outputStream.write(response.getBytes());

            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readHtmlFromFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
