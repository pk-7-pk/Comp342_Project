import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AvailabilityCsvExporter {

    public void write(String filePath, List<TimeSlot> slots) {
        try {
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            writer.println("First Name,Last Name,Title,Specialty,Date,Start,End");
            for (TimeSlot slot : slots) {
                writer.println(String.join(",", toRow(slot)));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not write file: " + filePath);
        }
    }
    private String[] toRow(TimeSlot slot) {
        HealthCareProfessional h = slot.getProfessional();
        String[] row = {
            h.getFirstName(),
            h.getLastName(),
            h.getTitle(),
            h.getSpecialties().get(0).getName(),
            slot.getDate().toString(),
            slot.getStartTime().toString(),
            slot.getEndTime().toString()
        };
        return row;
    } }