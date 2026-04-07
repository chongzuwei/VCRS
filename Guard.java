package OOP_Project;

// Guard.java
import java.util.ArrayList;

public class Guard extends User implements IReportable {
    private String guardID;

    public Guard(String userID, String name, char gender, int age, String noPhone) {
        super(userID, name, gender, age, noPhone);
        this.guardID = userID;
    }

    @Override
    public String displayDetails() {
        return String.format("Guard Name: %s (ID: %s), Gender: %c", name, guardID, gender);
    }

    @Override
    public IncidentReport fileIncidentReport(String description, String location, char severity, ArrayList<IncidentReport> reportList) {
        String reportID = "INC" + (reportList.size() + 1);
        IncidentReport report = new IncidentReport(reportID, this.guardID, description, location, severity);
        reportList.add(report);
        System.out.println("Incident report filed successfully by guard " + this.name);
        return report;
    }
}