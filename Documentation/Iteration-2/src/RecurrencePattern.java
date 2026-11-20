import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class RecurrencePattern {
    private Frequency frequency;
    private Set<DayOfWeek> daysOfWeek;
    private int dayOfMonth; //only used when frequency is monthly

    public RecurrencePattern(Frequency frequency, Set<DayOfWeek> daysOfWeek, int dayOfMonth) {
        this.frequency = frequency;
        this.daysOfWeek = daysOfWeek;
        this.dayOfMonth = dayOfMonth;
    }

    public boolean occursOn(LocalDate date) {
        switch (frequency) {
            case ONCE:   //for once the date range is a single day
            case DAILY:
                return true;
            case WEEKLY:
                return daysOfWeek.contains(date.getDayOfWeek());
            case MONTHLY:
                return date.getDayOfMonth() == dayOfMonth;
            default:
                return false;
        }
    }
    public Frequency getFrequency() {
        return frequency;
    } }