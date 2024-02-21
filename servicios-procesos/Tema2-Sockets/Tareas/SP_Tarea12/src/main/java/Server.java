import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            while (true) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado");

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                //Server code...
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();

                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);

                if (bytesRead > 0) {
                    String message = new String(buffer, 0, bytesRead);
                    System.out.println("Mensaje recibido del cliente: " + message);

                    String confirmationMessage = "Mensaje recibido. Vuelva pronto";
                    outputStream.write(confirmationMessage.getBytes());
                }

                inputStream.close();
                outputStream.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
