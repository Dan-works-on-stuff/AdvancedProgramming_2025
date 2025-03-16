import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Schedule {
    private String[] solution;

    public Schedule(Airport airport, Flight[] flights) {
        Arrays.sort(flights, Comparator.comparing(Flight::getStartTime));
        solution = new String[flights.length];
        int runwayCount = airport.getRunways().length;

        LocalTime[] runwayAvailability = new LocalTime[runwayCount];
        Arrays.fill(runwayAvailability, LocalTime.MIN);

        for (int i = 0; i < flights.length; i++) {
            Flight current = flights[i];
            boolean scheduled = false;

            for (int r = 0; r < runwayCount; r++) {
                if (!runwayAvailability[r].isAfter(current.getStartTime())) {
                    solution[i] = "Aircraft " + current.getAircraft().getModel() + " to runway " + (r + 1) + ", from " + current.getStartTime() + " to " + current.getEndTime();
                    runwayAvailability[r] = current.getEndTime();
                    scheduled = true;
                    break;
                }
            }

            if (!scheduled) {
                solution[i] = "Aircraft " + current.getAircraft().getModel() + " could not be scheduled" + ", from " + current.getStartTime() + " to " + current.getEndTime();
            }
        }
    }

    public void getSolution() {
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }
    }

}
