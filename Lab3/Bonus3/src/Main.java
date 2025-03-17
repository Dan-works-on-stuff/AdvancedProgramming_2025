import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int NUM_FLIGHTS = 10;
    private static final int NUM_AIRCRAFT = 10;
    public static List<Flight> flights = new ArrayList<>();

    public static void main(String[] args) {
//        Aircraft A1 = new Airliner("A1","vrabie",50,30);
//        Aircraft A2 = new Airliner("A2","randunica",50,40);
//        Drone D1 = new Drone("D1","cioara",10,4);
//        Freighter F1 = new Freighter("F1","bufnita",50,80);
//        CargoCapable[] Planes ={F1, D1};
//        Flight flight1 = new Flight(A1, LocalTime.of(9, 0), LocalTime.of(10, 30));
//        Flight flight2 = new Flight(A2, LocalTime.of(9, 30), LocalTime.of(12, 0));
//        Flight flight3 = new Flight(D1, LocalTime.of(10, 30), LocalTime.of(12, 0));
//        Flight flight4 = new Flight(A1, LocalTime.of(9, 30), LocalTime.of(10, 0));
//        Flight flight5 = new Flight(F1, LocalTime.of(9, 30), LocalTime.of(10, 30));
//        Flight flight6 = new Flight(A2, LocalTime.of(10, 0), LocalTime.of(11, 0));
//        Flight flight7 = new Flight(D1, LocalTime.of(10, 30), LocalTime.of(11, 30));
//        Flight flight8 = new Flight(A1, LocalTime.of(11, 0), LocalTime.of(12, 0));
//        Flight flight9 = new Flight(F1, LocalTime.of(11, 30), LocalTime.of(12, 0));
        TestDataGenerator();

        Airport airport = new Airport(new String[]{"R1", "R2", "R3"});
        Flight[] flightsArray = new Flight[NUM_FLIGHTS];
        for (int i = 0; i < NUM_FLIGHTS; i++) {
            flightsArray[i]= flights.get(i);
        }
        Schedule solutiaVaRog = new Schedule(airport, flightsArray);
        solutiaVaRog.getSolution();
    }

    public static void TestDataGenerator(){
        List<Aircraft> aircraftList = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < NUM_AIRCRAFT; i++) {
            int type = rand.nextInt(3); // 0=Airliner, 1=Drone, 2=Freighter
            switch (type) {
                case 0 -> aircraftList.add(new Airliner("A" + i, "Airline" + i, rand.nextInt(150) + 50, rand.nextInt(100)));
                case 1 -> aircraftList.add(new Drone("D" + i, "Operator" + i, rand.nextInt(50) + 10, rand.nextInt(10)));
                case 2 -> aircraftList.add(new Freighter("F" + i, "Cargo" + i, rand.nextInt(200) + 50, rand.nextInt(100)));
            }
        }

        for (int i = 0; i < NUM_FLIGHTS; i++) {
            Aircraft aircraft = aircraftList.get(rand.nextInt(aircraftList.size()));

            LocalTime start = LocalTime.of(rand.nextInt(24), rand.nextInt(60));

            // Random duration (30-180 mins)
            int duration = 30 + rand.nextInt(150);
            LocalTime end = start.plusMinutes(duration);

            // Clamp end time to 23:59 if it overflows
            if (end.isBefore(start) || end.equals(LocalTime.MIN)) {
                end = LocalTime.MAX; // 23:59:59.999999999
            }

            flights.add(new Flight(aircraft, start, end));
        }
    }
}