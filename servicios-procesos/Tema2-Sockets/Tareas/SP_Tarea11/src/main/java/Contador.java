import java.util.ArrayList;

public class Contador {
    private int n;
    private ArrayList<String> clientsConnected;
    public static boolean connectionFailed = false;

    public Contador() {
        this.n = 0;
        this.clientsConnected = new ArrayList<>();
    }

    public synchronized void connected(String clientName) {
        n++;
        clientsConnected.add(clientName);
    }

    public ArrayList<String> getClientsConnected() {
        return clientsConnected;
    }

    public String getLastConnected() {
        return clientsConnected.get(clientsConnected.size()-1);
    }

    public int getTotalConnections() {
        return clientsConnected.size();
    }

    public String getFirstConnected() {
        return clientsConnected.get(0);
    }

    public int getN() {
        return n;
    }
}
