
import java.time.LocalDate;

public class AvailabilityQuery {
    

    private String professionalId; 
    private String specialty;      
    private LocalDate fromDate;
    private LocalDate toDate;

    public AvailabilityQuery(String professionalId, String specialty, LocalDate fromDate, LocalDate toDate) {
        this.professionalId = professionalId;
        this.specialty = specialty;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public boolean matches(TimeSlot slot) {
        if (slot.getDate().isBefore(fromDate) || slot.getDate().isAfter(toDate)) {
            return false;
        }
        if (professionalId != null
                && !professionalId.equals(slot.getProfessional().getProfessionalId())) {
            return false;
        }
        if (specialty != null) {
            Specialty s = ClinicSystem.getInstance().getSpecialties().findByName(specialty);
            if (s == null || !slot.getProfessional().hasSpecialty(s)) {
                return false;
            }
        }
        return true;
    }
}


