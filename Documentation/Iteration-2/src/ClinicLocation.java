public class ClinicLocation {
    private String clinicId;
    private String name;
    private String address;
    private String phoneNumber;
    public ClinicLocation(String clinicId, String name, String address, String phoneNumber) {
        this.clinicId = clinicId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getClinicId() {
        return clinicId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


}