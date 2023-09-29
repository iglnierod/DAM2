import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DemoProcess {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> commands = new ArrayList<>();
        commands.add("notepad.exe");
        ProcessBuilder pb = new ProcessBuilder(commands);
        Process process = pb.start();

        List<String> commands2 = new ArrayList<>();
        commands2.add("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        commands2.add("www.twitter.com");
        ProcessBuilder pb2 = new ProcessBuilder(commands);
        Process process2 = pb.start();
        process.waitFor();
    }
}
