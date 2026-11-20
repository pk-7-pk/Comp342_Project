import java.util.HashMap;
import java.util.Map;

public class SpecialtyCatalog {
    private Map<String, Specialty> specialties = new HashMap<String, Specialty>();

    public Specialty findByName(String name) {
        return specialties.get(name.toLowerCase());
    }

    public void add(Specialty s) {
        specialties.put(s.getName().toLowerCase(), s);
    } }