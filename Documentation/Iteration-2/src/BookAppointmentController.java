import java.util.List;

public class BookAppointmentController {
    private Patient patient;
    private Service service;
    private BookingChannel channel;

    public void startBooking(String patientId, BookingChannel channel) {
        this.patient = ClinicSystem.getInstance().getPatients().findById(patientId);
        this.channel = channel;
        if (patient == null) {
            System.out.println("Error: no patient with this ID " + patientId);
        }
    }
    public void selectService(String serviceCode) {
        this.service = ClinicSystem.getInstance().getServices().findByCode(serviceCode);
        if (service == null) {
            System.out.println("Error: no service with this cod " + serviceCode);
        }
    }

    public List<TimeSlot> searchAvailability(AvailabilityQuery q) {
        return ClinicSystem.getInstance().getAvailability().search(q);
    }

    public Appointment confirmAppointment(String slotId, String reason) {
        ClinicSystem system = ClinicSystem.getInstance();
        TimeSlot slot = system.getAvailability().findSlot(slotId);
        if (slot == null || !slot.isAvailable()) { System.out.println("Error: slot " + slotId + " is not available right now");
            return null;
        }
        Appointment a = new Appointment(patient, slot, service, reason, channel);
        slot.book();
        system.getAppointments().add(a);
        return a;
    }
    public Patient getPatient() {
        return patient;
    }
    public Service getService() {
        return service;
    } }