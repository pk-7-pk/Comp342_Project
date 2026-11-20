public class Service {
    private String serviceCode;
    private String name;
    private double fee;
    private boolean isInsured;
    private boolean requiresReferral;
    private int defaultDurationMinutes;

    public Service(String serviceCode, String name, double fee, boolean isInsured, boolean requiresReferral, int defaultDurationMinutes) {
        this.serviceCode = serviceCode;
        this.name = name;
        this.fee = fee;
        this.isInsured = isInsured;
        this.requiresReferral = requiresReferral;
        this.defaultDurationMinutes = defaultDurationMinutes;
    }

    public boolean isReferralRequired() {
        return requiresReferral;
    }

    public int getDefaultDuration() {
        return defaultDurationMinutes;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public String getName() {
        return name;
    }

    public double getFee() {
        return fee;
    }

    public boolean isInsured() {
        return isInsured;
    }
}