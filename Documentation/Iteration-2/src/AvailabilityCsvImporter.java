import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityCsvImporter {
    private static int patientCounter = 0;

    public List<TimeSlot> read(String filePath) {
        ClinicSystem system = ClinicSystem.getInstance();
        List<TimeSlot> slots = new ArrayList<TimeSlot>();
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8));
            String header = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",", -1);
                for (int i = 0; i < cols.length; i++) {
                    cols[i] = cols[i].trim();
                    if (cols[i].equals("–") || cols[i].equals("-")) {
                        cols[i] = "";
                    }
                }

                if (header.contains("Service Code") && cols.length == 6) {
                    system.getServices().add(new Service(cols[0], cols[1], Double.parseDouble(cols[2]),
                            Boolean.parseBoolean(cols[3]), Boolean.parseBoolean(cols[4]),
                            Integer.parseInt(cols[5])));
                    count++;

                } else if (header.contains("Professional") && cols.length == 7) {
                    String[] proName = cols[0].split(" ", 2);
                    HealthCareProfessional hp = system.getProfessionals().findByName(proName[0], proName[1]);
                    LocalDate date = LocalDate.parse(cols[1]);
                    LocalTime start = LocalTime.parse(cols[2]);
                    TimeSlot slot = null;
                    for (TimeSlot s : system.getAvailability().getAllSlots()) {
                        if (s.getProfessional() == hp && s.getDate().equals(date) && s.getStartTime().equals(start)) {
                            slot = s;
                        }
                    }
                    if (slot != null && slot.isAvailable()) {
                        String[] patientName = cols[4].split(" ", 2);
                        patientCounter++;
                        Patient p = new Patient("PT" + patientCounter, patientName[0], patientName[1], null, cols[5], cols[6]);
                        system.getPatients().add(p);
                        System.out.println(p.getPatientId() + "  " + p.getFullName());
                        Appointment a = new Appointment(p, slot, null, "", null);
                        slot.book();
                        system.getAppointments().add(a);
                        count++;
                    } else {
                        System.out.println("Skipped (no free matching slot): " + line);
                    }

                } else if (header.contains("Telephone") && cols.length == 7) {
                    patientCounter++;
                    Patient p = new Patient("PT" + patientCounter, cols[0], cols[1], null, cols[2], cols[3]);
                    system.getPatients().add(p);
                    System.out.println(p.getPatientId() + "  " + p.getFullName());
                    count++;

                } else if (cols.length == 7) {
                    slots.add(parseRow(cols));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Could not read file: " + filePath);
        }
        if (count > 0) {
            System.out.println(count + " rows imported.");
        }
        return slots;
    }

    private TimeSlot parseRow(String[] cols) {
        ClinicSystem system = ClinicSystem.getInstance();
        String firstName = cols[0];
        String lastName = cols[1];
        String title = cols[2];
        String specialtyName = cols[3];
        LocalDate date = LocalDate.parse(cols[4]);
        LocalTime start = LocalTime.parse(cols[5]);
        LocalTime end = LocalTime.parse(cols[6]);

        Specialty specialty = system.getSpecialties().findByName(specialtyName);
        if (specialty == null) {
            specialty = new Specialty(specialtyName, "");
            system.getSpecialties().add(specialty);
        }

        HealthCareProfessional professional = system.getProfessionals().findByName(firstName, lastName);
        if (professional == null) {
            professional = new HealthCareProfessional(title, firstName, lastName, "");
            system.getProfessionals().add(professional);
        }
        if (!professional.hasSpecialty(specialty)) {
            professional.addSpecialty(specialty);
        }

        ClinicLocation location = system.getClinics().findById("C1");
        if (location == null) {
            location = new ClinicLocation("C1", "Main Clinic", "", "");
            system.getClinics().add(location);
        }
        return new TimeSlot(date, start, end, professional, location);
    }
}