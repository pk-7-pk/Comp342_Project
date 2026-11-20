import java.util.HashMap;
import java.util.Map;

public class ServiceCatalog {
    private Map<String, Service> services = new HashMap<String, Service>();

    public Service findByCode(String code) {
        return services.get(code.toUpperCase());
    }

    public void add(Service s) {
        services.put(s.getServiceCode().toUpperCase(), s);
    } }