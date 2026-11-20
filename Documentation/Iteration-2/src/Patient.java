import java.time.LocalDate;

public class Patient {
    private String patientId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String ramqNumber;

    public Patient(String patientId, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String ramqNumber) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.ramqNumber = ramqNumber;
    }

    public String getFullName() {
        return firstName + "  " + lastName;
    }

    public String getPatientId() {
        return patientId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
 
    public String getRamqNumber() {
        return ramqNumber;
    }}
    