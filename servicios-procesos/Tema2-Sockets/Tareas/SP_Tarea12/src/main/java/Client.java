import java.io.*;
import java.net.*;

public class Client {

    // PROBAR APLICACIÓN CON FICHERO pom.xml
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectado al servidor.");

            DataInputStream dataInput = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Introduce el nombre del archivo: ");
            String fileName = reader.readLine();
            dataOutput.writeUTF(fileName);

            String response = dataInput.readUTF();
            if (response.equals("EXISTS")) {
                FileOutputStream fos = new FileOutputStream("recibido_" + fileName);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                InputStream inFile = socket.getInputStream();

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inFile.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }

                bos.close();
                fos.close();
                System.out.println("Archivo recibido: " + "recibido_" + fileName);
            } else {
                System.out.println("El archivo no existe en el servidor.");
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

