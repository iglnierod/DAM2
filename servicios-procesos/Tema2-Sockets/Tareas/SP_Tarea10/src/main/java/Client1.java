import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            OutputStream outputStream = socket.getOutputStream();

            String message = "Hola desde el Cliente 1";
            outputStream.write(message.getBytes());

            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);

            if (bytesRead > 0) {
                String response = new String(buffer, 0, bytesRead);
                System.out.println("Respuesta del servidor: " + response);
            }

            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

