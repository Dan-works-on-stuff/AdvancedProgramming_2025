import java.time.LocalTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Aircraft A1 = new Airliner("A1","vrabie",50,30);
        Aircraft A2 = new Airliner("A2","randunica",50,40);
        Drone D1 = new Drone("D1","cioara",10,4);
        Freighter F1 = new Freighter("F1","bufnita",50,80);
        CargoCapable[] Planes ={F1, D1};
        Flight flight1 = new Flight(A1, LocalTime.of(9, 0), LocalTime.of(10, 30));
        Flight flight2 = new Flight(A2, LocalTime.of(9, 30), LocalTime.of(12, 0));
        Flight flight3 = new Flight(D1, LocalTime.of(10, 30), LocalTime.of(12, 0));
        Flight flight4 = new Flight(A1, LocalTime.of(9, 30), LocalTime.of(10, 0));  // Overlaps A1, A2
        Flight flight5 = new Flight(F1, LocalTime.of(9, 30), LocalTime.of(10, 30));  // Overlaps A1, A2, F4
        Flight flight6 = new Flight(A2, LocalTime.of(10, 0), LocalTime.of(11, 0));    // Overlaps A1, A2, F5
        Flight flight7 = new Flight(D1, LocalTime.of(10, 30), LocalTime.of(11, 30)); // Overlaps A2, D1, F6
        Flight flight8 = new Flight(A1, LocalTime.of(11, 0), LocalTime.of(12, 0));    // Overlaps A2, D1, F7
        Flight flight9 = new Flight(F1, LocalTime.of(11, 30), LocalTime.of(12, 0));   // Overlaps A2, D1, F8

// Peak overlap example at 10:15 AM:
// - A1 (9:00-10:30)
// - A2 (9:30-12:00)
// - F5 (9:30-10:30)
// - F6 (10:00-11:00)
        Flight[] flights = {flight1, flight2, flight3, flight4, flight5, flight6, flight7, flight8, flight9};
        Airport airport = new Airport(new String[]{"R1", "R2", "R3", "R4"});
        Schedule solutiaVaRog = new Schedule(airport, flights);
        solutiaVaRog.getSolution();
    }
}