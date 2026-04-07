package OOP_Project;

// IncidentReport.java
// IncidentReport.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IncidentReport {
    private String reportID;
    private String reporterID;
    private String description;
    private String location;
    private LocalDateTime timeReported;
    private char severity;
    private String status;

    public IncidentReport(String reportID, String reporterID, String description, String location, char severity) {
        this.reportID = reportID;
        this.reporterID = reporterID;
        this.description = description;
        this.location = location;
        this.severity = severity;
        this.timeReported = LocalDateTime.now();
        this.status = "New";
    }

    public String getReporterID() { return reporterID; }
    public String getReportID() { return reportID; }
    
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Report " + reportID + " status updated to: " + newStatus);
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Report ID: %s, Status: %s, Severity: %c\n  - Location: %s\n  - Time: %s\n  - Description: %s",
                reportID, status, severity, location, timeReported.format(formatter), description);
    }
}