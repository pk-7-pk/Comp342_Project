import java.time.LocalDateTime;

public class Appointment {
    private static int counter = 0;
    private String appointmentId;
    private LocalDateTime dateTime;
    private int durationMinutes;
    private String reason;
    private AppointmentStatus status;
    private BookingChannel bookingChannel;
    private Patient patient;
    private Service service;
    private TimeSlot slot;

    public Appointment(Patient p, TimeSlot slot, Service sv, String reason, BookingChannel channel) {
        counter++;
        this.appointmentId = "A" + counter;
        this.patient = p;
        this.slot = slot;
        this.service = sv;
        this.reason = reason;
        this.bookingChannel = channel;
        this.dateTime = LocalDateTime.of(slot.getDate(), slot.getStartTime());
        this.durationMinutes = slot.getDurationMinutes();
        this.status = AppointmentStatus.CONFIRMED;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Patient getPatient() {
        return patient;
    } }