import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Availability {
    private static int counter = 0;
    private String availabilityId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int slotLengthMinutes;
    private HealthCareProfessional professional;
    private ClinicLocation location;
    private RecurrencePattern pattern;
    private List<TimeSlot> slots = new ArrayList<TimeSlot>();

    public Availability(HealthCareProfessional professional, ClinicLocation location, LocalDate startDate, LocalDate endDate, RecurrencePattern pattern, LocalTime startTime, LocalTime endTime, int slotLengthMinutes) {
        counter++;
        this.availabilityId = "Avail." + counter;
        this.professional = professional;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pattern = pattern;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotLengthMinutes = slotLengthMinutes;
    }

    //here we generate the slots based on the availability information. We check each date in the range, and if it matches the recurrence pattern, we create slots for that day
    //cuts time window into slots of slotlengthminutes
    public List<TimeSlot> generateSlots() {
        slots = new ArrayList<TimeSlot>();
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            if (pattern.occursOn(date)) {
                LocalTime start = startTime;
                while (!start.plusMinutes(slotLengthMinutes).isAfter(endTime)) {
                    LocalTime end = start.plusMinutes(slotLengthMinutes);
                    TimeSlot slot = new TimeSlot(date, start, end, professional, location);
                    slots.add(slot);
                    start = end;
                }
            }
            date = date.plusDays(1);
        }
        return slots;
    }

    public String getAvailabilityId() {
        return availabilityId;
    }

    public List<TimeSlot> getSlots() {
        return slots;
    }

    public HealthCareProfessional getProfessional() {
        return professional;
    }

    public ClinicLocation getLocation() {
        return location;
    }

    public RecurrencePattern getPattern() {
        return pattern;
    }





}
