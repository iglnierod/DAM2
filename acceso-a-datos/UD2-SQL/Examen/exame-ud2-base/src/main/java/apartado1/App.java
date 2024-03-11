package apartado1;

import util.ANSI;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class App {
    private Connection con;
    private Set<String> originAirports;
    private Set<String> destinyAirports;

    public App() {
        con = getConnection();
        originAirports = new LinkedHashSet<>();
        destinyAirports = new LinkedHashSet<>();
        printMenu();
    }

    private Connection getConnection() {
        String url = String.format("jdbc:mysql://%s:%s/%s", "127.0.0.1", "3306", "flights");
        try {
            Connection connection = DriverManager.getConnection(url, "root", "abc123.");
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("USE flights;");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void printMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce a cantidade de viaxeiros: ");
        int travelersNum = sc.nextInt();
        sc.nextLine();
        String airports = getOriginAirports();
        System.out.println("\nAeroportos de orixe: " + airports);
        System.out.print("Selecciona o aeroporto de orixe: ");
        String originAirport = sc.nextLine();
        airports = getDestinyAirports(originAirport);
        System.out.println("\nAeroportos de destino desde " + originAirport + ": " + airports);
        System.out.print("Seleccione o aeroporto de destino: ");
        String destinationAirport = sc.nextLine();
        System.out.println("\nVoo seleccionado: " + getSelectedFlight(originAirport, destinationAirport));
        System.out.println();
        checkSeats(originAirport, destinationAirport, travelersNum);
    }

    private void checkSeats(String originAirport, String destinationAirport, int travelersNum) {
        // empty seats query
        String query = """
                select count(s.id)
                from seats s\s
                join flights f on s.flight_id = f.id\s
                where f.origin = ?\s
                	and f.destination = ?\s
                	and assigned = 0;
                """;
        int emptySeats = -1;
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, originAirport);
            ps.setString(2, destinationAirport);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                emptySeats = rs.getInt(1);
            }
            System.out.println("Asentos libres: " + emptySeats);
            if (travelersNum > emptySeats) {
                ANSI.printRedBg("Non hai suficientes asentos para os viaxeiros.");
                return;
            }

            ArrayList<Integer> seatsToReassign = new ArrayList<>();
            query = """
                    select s.number
                    from seats s
                    join flights f
                    on s.flight_id = f.id
                    where s.assigned = 0
                    and f.origin = ?
                    and f.destination = ?
                    limit ?;
                    """;
            PreparedStatement ps2 = con.prepareStatement(query);
            ps2.setString(1, originAirport);
            ps2.setString(2, destinationAirport);
            ps2.setInt(3, travelersNum);
            rs = ps2.executeQuery();
            while (rs.next()) {
                seatsToReassign.add(rs.getInt(1));
            }
            System.out.println("Asentos asignados: " + reassignSeats(seatsToReassign, originAirport, destinationAirport));
            System.out.println("Asentos restantes: " + (emptySeats - seatsToReassign.size()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String reassignSeats(ArrayList<Integer> seatsToReassign, String origin, String destination) {
        StringBuilder sb = new StringBuilder();
        try (Statement stmt = con.createStatement()) {
            for (Integer seatID : seatsToReassign) {
                stmt.executeUpdate(String.format("update seats set assigned = 1 where number = %d and flight_id in " +
                        "(select id from flights where origin = '%s' and destination = '%s');", seatID, origin, destination));
                sb.append(seatID).append(" ");
            }
            return sb.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getSelectedFlight(String originAirport, String destinationAirport) {
        //get plane id
        String flightID = "";
        String query = "SELECT id FROM flights WHERE origin = ? and destination = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, originAirport);
            ps.setString(2, destinationAirport);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flightID = rs.getString(1);
            }
            // get company name
            query = """
                    select p.company from planes p join flights f on p.id = f.plane_id\s
                    where f.origin = ? and f.destination = ?
                    """;
            PreparedStatement ps2 = con.prepareStatement(query);
            ps2.setString(1, originAirport);
            ps2.setString(2, destinationAirport);
            rs = ps2.executeQuery();
            String companyName = "company";
            while (rs.next()) {
                companyName = rs.getString(1);
            }
            return String.format("[%s] %s-%s (%s)", flightID, originAirport, destinationAirport, companyName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getDestinyAirports(String origin) {
        StringBuilder sb = new StringBuilder();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(String.format("select destination from flights where origin = '%s'", origin));
            while (rs.next()) {
                String airport = rs.getString(1);
                sb.append(airport).append(" ");
                destinyAirports.add(airport);
            }
            return sb.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getOriginAirports() {
        StringBuilder sb = new StringBuilder();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT origin FROM flights");
            while (rs.next()) {
                String airport = rs.getString(1);
                sb.append(airport).append(" ");
                originAirports.add(airport);
            }
            return sb.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
