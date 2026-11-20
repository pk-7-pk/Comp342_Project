public class ClinicSystem {
    private static ClinicSystem instance = null;

    private PatientRegistry patients;
    private ProfessionalRegistry professionals;
    private AppointmentBook appointments;
    private ServiceCatalog services;
    private SpecialtyCatalog specialties;
    private ClinicRegistry clinics;
    private AvailabilityRegistry availability;

    private ClinicSystem() {
        patients = new PatientRegistry();
        professionals = new ProfessionalRegistry();
        appointments = new AppointmentBook();
        services = new ServiceCatalog();
        specialties = new SpecialtyCatalog();
        clinics = new ClinicRegistry();
        availability = new AvailabilityRegistry();
    }

    public static ClinicSystem getInstance() {
        if (instance == null) {
            instance = new ClinicSystem();
        }
        return instance;
    }

    public PatientRegistry getPatients() {
        return patients;
    }

    public ProfessionalRegistry getProfessionals() {
        return professionals;
    }

    public AppointmentBook getAppointments() {
        return appointments;
    }

    public ServiceCatalog getServices() {
        return services;
    }

    public SpecialtyCatalog getSpecialties() {
        return specialties;
    }

    public ClinicRegistry getClinics() {
        return clinics;
    }

    public AvailabilityRegistry getAvailability() {
        return availability;
    }}