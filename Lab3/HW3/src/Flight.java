import java.time.LocalTime;

public class Flight {
    private LocalTime startTime;
    private LocalTime endTime;
    private int id;
    Aircraft aircraft;
    public Flight(Aircraft a, LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
        this.aircraft = a;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public int getId() {
        return id;
    }
    public Aircraft getAircraft() {
        return aircraft;
    }
}
