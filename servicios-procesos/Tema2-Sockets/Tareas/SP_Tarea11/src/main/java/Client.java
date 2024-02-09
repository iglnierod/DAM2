import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client implements Runnable {
    private Contador c;
    private String name;

    public Client(String name, Contador c) {
        this.name = name;
        this.c = c;
    }

    @Override
    public void run() {
        if (!Contador.connectionFailed) {
            try {
                Socket socket = new Socket("localhost", 8080);
                OutputStream outputStream = socket.getOutputStream();
                // Conexión exitosa
                c.connected(name);

                String message = "Hola desde el " + name;
                outputStream.write(message.getBytes());

                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);

                if (bytesRead > 0) {
                    String response = new String(buffer, 0, bytesRead);
                    System.out.println(name + ": Respuesta del servidor: " + response);
                }

                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                Contador.connectionFailed = true;
            }
        }
    }

    public static void main(String[] args) {
        Contador c = new Contador();
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(new Client("Cliente " + i, c));
            thread.start();
        }

        System.out.println("========== ESTADISTICAS ==========");
        System.out.println("Lista de clientes conectados:");
        System.out.println(c.getClientsConnected());
        System.out.println();
        System.out.println("Primer cliente conectado: " + c.getFirstConnected());
        System.out.println("Ultimo cliente conectado: "+ c.getLastConnected());
        System.out.println("Total clientes conectados: " + c.getTotalConnections());
    }
}
