import java.util.HashMap;
import java.util.Map;

public class ClinicRegistry {
    private Map<String, ClinicLocation> clinics = new HashMap<String, ClinicLocation>();

    public ClinicLocation findById(String id) {
        return clinics.get(id);
    }

    public void add(ClinicLocation c) {
        clinics.put(c.getClinicId(), c);
    }}