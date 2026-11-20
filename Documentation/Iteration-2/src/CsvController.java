import java.util.List;

public class CsvController {

    public int importAvailability(String filePath) {
        AvailabilityCsvImporter importer = new AvailabilityCsvImporter();
        List<TimeSlot> slots = importer.read(filePath);
        for (TimeSlot slot : slots) {
            ClinicSystem.getInstance().getAvailability().addSlot(slot);
        }
        return slots.size();
    }

    public void exportAvailability(String filePath) {
        AvailabilityCsvExporter exporter = new AvailabilityCsvExporter();
        List<TimeSlot> slots = ClinicSystem.getInstance().getAvailability().getAllSlots();
        exporter.write(filePath, slots);
    } }