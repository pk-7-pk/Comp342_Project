import java.util.HashMap;
import java.util.Map;

public class PatientRegistry {
    private Map<String, Patient> patients = new HashMap<String, Patient>();

    public Patient findById(String id) {
        return patients.get(id);
    }

    public void add(Patient p) {
        patients.put(p.getPatientId(), p);
    } }