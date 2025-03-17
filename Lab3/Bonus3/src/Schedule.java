import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Schedule {
    private String[] solution;

    public Schedule(Airport airport, Flight[] flights) {
        Arrays.sort(flights, Comparator.comparing(Flight::getStartTime));
        solution = new String[flights.length];
        int runwayCount = airport.getRunways().length;
        int[] runwayFrequency = new int[runwayCount];
        LocalTime[] runwayAvailability = new LocalTime[runwayCount];
        Arrays.fill(runwayAvailability, LocalTime.MIN);
        LocalTime[] AvailableRunways = new LocalTime[runwayCount];

        for (int i = 0; i < flights.length; i++) {
            Flight current = flights[i];
            List<Integer> availableRunways = new ArrayList<>();
            for (int r = 0; r < runwayCount; r++) {
                if (!runwayAvailability[r].isAfter(current.getStartTime())) {
                    availableRunways.add(r); // Track indices of available runways
                }
            }

            if (availableRunways.isEmpty()) {
                solution[i] = "Aircraft " + current.getAircraft().getModel()
                        + " could not be scheduled"
                        + ", from " + current.getStartTime()
                        + " to " + current.getEndTime();
                continue;
            }

            int selectedRunway = -1;
            int minUsage = Integer.MAX_VALUE;

            for (int r : availableRunways) {
                if (runwayFrequency[r] < minUsage) {
                    minUsage = runwayFrequency[r];
                    selectedRunway = r;
                } else if (runwayFrequency[r] == minUsage && r < selectedRunway) {
                    // Tiebreaker: smaller index
                    selectedRunway = r;
                }
            }

            solution[i] = "Aircraft " + current.getAircraft().getModel()
                    + " to runway " + (selectedRunway + 1)
                    + ", from " + current.getStartTime()
                    + " to " + current.getEndTime();
            runwayAvailability[selectedRunway] = current.getEndTime();
            runwayFrequency[selectedRunway]++; // Increment usage count
        }
        solution[solution.length -1] += '\n';
        for (int i = 0; i < runwayCount; i++) {
            solution[solution.length -1] += runwayFrequency[i];
            solution[solution.length -1] += " ";
        }
    }


    public void getSolution() {
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }
    }
}
