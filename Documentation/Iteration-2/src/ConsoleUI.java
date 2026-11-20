import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleUI {
    private Scanner scanner = new Scanner(System.in);
    private BookAppointmentController bookingController = new BookAppointmentController();
    private AvailabilityController availabilityController = new AvailabilityController();
    private CsvController csvController = new CsvController();

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1": importCsv(); break;
                    case "2": exportCsv(); break;
                    case "3": makeAvailability(); break;
                    case "4": searchAvailability(); break;
                    case "5": bookAppointment(); break;
                    case "0": running = false; break;
                    default: System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input  try again");
            }
        }
    }

    private void printMenu() {
        System.out.print("Choose an option: ");
        System.out.println();
        System.out.println("1: Import availabilities from CSV");
        System.out.println("2: Export availabilities to CSV");
        System.out.println("3: Make availability");
        System.out.println("4: Search availability");
        System.out.println("5: Book an appointment");
        System.out.println("0: Exit the system");
    }

    private String ask(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private void importCsv() {
        int count = csvController.importAvailability(ask("CSV file path: "));
        if (count > 0) {
            System.out.println(count + " time slots imported ");
        }
    }

    private void exportCsv() {
        String path = ask("CSV file path: ");
        csvController.exportAvailability(path);
        System.out.println("Availabilities exported to " + path);
    }

    private void makeAvailability() {
        String professionalId = ask("Professional ID: ");
        String clinicId = ask("Clinic ID: ");
        System.out.println("Repetition: 1 = Once, 2 = Daily, 3 = Weekly, 4 = Monthly:");
        String type = ask("Choose repetition: ");
        LocalDate startDate = LocalDate.parse(ask("Start date (yyyy-mm-dd): "));
        LocalDate endDate = LocalDate.parse(ask("End date (yyyy-mm-dd): "));
        Frequency frequency = Frequency.ONCE;
        Set<DayOfWeek> days = new HashSet<DayOfWeek>();
        int dayOfMonth = 0;

        if (type.equals("2")) {
            frequency = Frequency.DAILY;
        } else if (type.equals("3")) {
            frequency = Frequency.WEEKLY;
            String[] parts = ask("Days of week (1 = Mon, 2 = Tue, 3 = Wed, 4 = Thu, 5 = Fri, 6 = Sat, 7 = Sun ): ").split(",");
            for (String part : parts) {
                days.add(DayOfWeek.of(Integer.parseInt(part.trim())));
            }
        } else if (type.equals("4")) {
            frequency = Frequency.MONTHLY;
            dayOfMonth = Integer.parseInt(ask("Day of month (1-31): "));
        }

        LocalTime startTime = LocalTime.parse(ask("Start time (hr:m) ex: 09:00: "));
        LocalTime endTime = LocalTime.parse(ask("End time (hr:m) ex: 17:00: "));
        int slotLength = Integer.parseInt(ask("Slot length in minutes: "));

        RecurrencePattern pattern = new RecurrencePattern(frequency, days, dayOfMonth);
        Availability a = availabilityController.makeAvailability(professionalId, clinicId, startDate,
                endDate, pattern, startTime, endTime, slotLength);
        if (a != null) {
            System.out.println("Availability " + a.getAvailabilityId() + " created with " + a.getSlots().size() + " time slots.");
        }
    }

    private void searchAvailability() {
        printSlots(availabilityController.searchAvailability(askQuery()));
    }

    private AvailabilityQuery askQuery() {
        String professionalId = ask("Professional ID (press Enter for any): ");
        String specialty = ask("Specialty (press Enter for any): ");
        LocalDate from = LocalDate.parse(ask("From date (yyyy-mm-dd): "));
        LocalDate to = LocalDate.parse(ask("To date (yyyy-mm-dd): "));
        if (professionalId.isEmpty()) {
            professionalId = null;
        }
        if (specialty.isEmpty()) {
            specialty = null;
        }
        return new AvailabilityQuery(professionalId, specialty, from, to);
    }

    private void printSlots(List<TimeSlot> slots) {
        if (slots.isEmpty()) {
            System.out.println("No availability found.");
        }
        for (TimeSlot slot : slots) {
            HealthCareProfessional hp = slot.getProfessional();
            System.out.println(slot.getSlotId() + "  " + slot.getDate() + "  " + slot.getStartTime() + "-" + slot.getEndTime() + "  " + hp.getTitle() + " " + hp.getFirstName() + " " + hp.getLastName() + " (" + hp.getProfessionalId() + ")");
        }
    }

    private void bookAppointment() {
        String patientId = ask("Patient ID: ");
        String channelChoice = ask("Booking channel (1 = Walk-in, 2 = Phone): ");
        BookingChannel channel = BookingChannel.WALK_IN;
        if (channelChoice.equals("2")) {
            channel = BookingChannel.PHONE;
        }
        bookingController.startBooking(patientId, channel);
        if (bookingController.getPatient() == null) {
            return;
        }

        bookingController.selectService(ask("Service code: "));
        if (bookingController.getService() == null) {
            return;
        }

        List<TimeSlot> slots = bookingController.searchAvailability(askQuery());
        printSlots(slots);
        if (slots.isEmpty()) {
            return;
        }

        String slotId = ask("Slot ID to book: ");
        String reason = ask("Reason for the visit: ");
        Appointment a = bookingController.confirmAppointment(slotId, reason);
        if (a != null) {
            System.out.println("Appointment " + a.getAppointmentId() + " confirmed for " + a.getPatient().getFullName() + " on " + a.getDateTime().toLocalDate() + " at " + a.getDateTime().toLocalTime());
        }
    }
}