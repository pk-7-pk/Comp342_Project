import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeSlot {
    private static int counter = 0;

    private String slotId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private SlotStatus status;
    private HealthCareProfessional professional;
    private ClinicLocation location;

    public TimeSlot(LocalDate date, LocalTime startTime, LocalTime endTime, HealthCareProfessional professional, ClinicLocation location)    {
        counter++;
        this.slotId = "S" + counter;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.professional = professional;
        this.location = location;
        this.status = SlotStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return status == SlotStatus.AVAILABLE;
    }

    public void book() {
        status = SlotStatus.BOOKED;
    }

    public void release() {
        status = SlotStatus.AVAILABLE;
    }

    //2 slots overlap if they are for the same professionall on the same day, and if the time interbal overlaps
    public boolean overlaps(TimeSlot other) {
        return professional == other.professional && date.equals(other.date) && startTime.isBefore(other.endTime) && other.startTime.isBefore(endTime);
    }

    public int getDurationMinutes() {
        return (int) Duration.between(startTime, endTime).toMinutes();
    }

    public String getSlotId() {
        return slotId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public HealthCareProfessional getProfessional() {
        return professional;
    }

    public ClinicLocation getLocation() {
        return location;
    }}