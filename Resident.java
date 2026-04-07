package OOP_Project;

// Resident.java
import java.util.ArrayList;

public class Resident extends User implements IReportable {
    private Room room;

    public Resident(String userID, String name, char gender, int age, String noPhone, Room room) {
        super(userID, name, gender, age, noPhone);
        this.room = room;
    }

    public Room getRoom() { return room; }

    @Override
    public String displayDetails() {
        return String.format("Resident Name: %s (ID: %s), Gender: %c, Room: %s, Phone: %s", name, userID, gender, room.toString(), noPhone);
    }

    @Override
    public IncidentReport fileIncidentReport(String description, String location, char severity, ArrayList<IncidentReport> reportList) {
        String reportID = "INC" + (reportList.size() + 1);
        IncidentReport report = new IncidentReport(reportID, this.userID, description, location, severity);
        reportList.add(report);
        System.out.println("Incident report filed successfully by resident " + this.name);
        return report;
    }
}
