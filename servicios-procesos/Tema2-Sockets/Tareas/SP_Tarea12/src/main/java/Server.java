import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server conectado, esperando clientes...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostName());

            Thread t = new Thread(new ClientHandler(clientSocket));
            t.start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInput = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutput = new DataOutputStream(clientSocket.getOutputStream());

            String fileName = dataInput.readUTF();
            System.out.println("Recibida petición de archivo: " + fileName);

            File file = new File(fileName);
            if (file.exists() && !file.isDirectory()) {
                dataOutput.writeUTF("EXISTS");

                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                DataOutputStream outFile = new DataOutputStream(clientSocket.getOutputStream());

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                    outFile.write(buffer, 0, bytesRead);
                }

                bufferedInputStream.close();
                outFile.close();
                System.out.println("Archivo enviado: " + fileName);
            } else {
                dataOutput.writeUTF("NOT_EXISTS");
                System.out.println("Archivo no encontrado: " + fileName);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
