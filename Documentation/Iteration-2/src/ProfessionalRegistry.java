import java.util.HashMap;
import java.util.Map;

public class ProfessionalRegistry {
    private Map<String, HealthCareProfessional> professionals = new HashMap<String, HealthCareProfessional>();

    public HealthCareProfessional findById(String id) {
        return professionals.get(id);
    }

    public HealthCareProfessional findByName(String first, String last) {
        for (HealthCareProfessional hp : professionals.values()) {
            if (hp.getFirstName().equalsIgnoreCase(first) && hp.getLastName().equalsIgnoreCase(last)) {
                return hp;
            }
        }
        return null;
    }
    public void add(HealthCareProfessional hp) {
        professionals.put(hp.getProfessionalId(), hp);
    } }