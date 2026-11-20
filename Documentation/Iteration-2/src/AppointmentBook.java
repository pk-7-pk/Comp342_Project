import java.util.HashMap;
import java.util.Map;

public class AppointmentBook {
    private Map<String, Appointment> appointments = new HashMap<String, Appointment>();

    public Appointment findById(String id) {
        return appointments.get(id);
    }

    public void add(Appointment a) {
        appointments.put(a.getAppointmentId(), a);
    } }