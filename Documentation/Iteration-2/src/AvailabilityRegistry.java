import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AvailabilityRegistry {
    private List<Availability> availabilities = new ArrayList<Availability>();
    private Map<String, TimeSlot> slots = new LinkedHashMap<String, TimeSlot>();

    public void addAvailability(Availability a) {
        availabilities.add(a);
    }

    public void addSlot(TimeSlot s) {
        slots.put(s.getSlotId(), s);
    }

    public TimeSlot findSlot(String id) {
        return slots.get(id);
    }

    public List<TimeSlot> search(AvailabilityQuery q) {
        List<TimeSlot> result = new ArrayList<TimeSlot>();
        for (TimeSlot slot : slots.values()) {
            if (slot.isAvailable() && q.matches(slot)) {
                result.add(slot);
            }
        }
        return result;
    }

    public List<TimeSlot> getAllSlots() {
        return new ArrayList<TimeSlot>(slots.values());
    } }