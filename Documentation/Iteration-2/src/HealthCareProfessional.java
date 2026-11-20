import java.util.ArrayList;
import java.util.List;

public class HealthCareProfessional {
    

    private static int counter = 0;

    private String professionalId;
    private String title;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private List<Specialty> specialties = new ArrayList<Specialty>();


    public HealthCareProfessional(String title, String firstName, String lastName, String licenseNumber) {
        counter++;
        this.professionalId = "P" + counter;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
    }

    public boolean hasSpecialty(Specialty s) {
        return specialties.contains(s);
    }

    public void addSpecialty(Specialty s) {
        specialties.add(s);
    }

    public String getProfessionalId() {
        return professionalId;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

}
