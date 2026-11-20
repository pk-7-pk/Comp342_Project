import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AvailabilityController {

    public Availability makeAvailability(String professionalId, String clinicId, LocalDate startDate,
                                         LocalDate endDate, RecurrencePattern pattern, LocalTime startTime,
                                         LocalTime endTime, int slotLengthMinutes) {
        ClinicSystem system = ClinicSystem.getInstance();
        HealthCareProfessional professional = system.getProfessionals().findById(professionalId);
        ClinicLocation location = system.getClinics().findById(clinicId);
        if (professional == null || location == null) {
            System.out.println("Error: unknown professional or clinic");
            return null;
        }
        Availability avail = new Availability(professional, location, startDate, endDate, pattern,
                startTime, endTime, slotLengthMinutes);
        List<TimeSlot> slots = avail.generateSlots();

        for (TimeSlot slot : slots) {
            for (TimeSlot existing : system.getAvailability().getAllSlots()) {
                if (existing.overlaps(slot)) {
                    System.out.println("Error: overlaps existing availability on " + slot.getDate()
                            + " at " + slot.getStartTime());
                    return null; }
            }
        }

        system.getAvailability().addAvailability(avail);
        for (TimeSlot slot : slots) {
            system.getAvailability().addSlot(slot);
        }
        return avail;
    }

    public List<TimeSlot> searchAvailability(AvailabilityQuery q) {
        return ClinicSystem.getInstance().getAvailability().search(q);
    } }