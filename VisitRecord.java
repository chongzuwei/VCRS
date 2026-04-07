package OOP_Project;

// VisitRecord.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VisitRecord {
    private String recordID;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String visitPurpose;
    private Visitor visitor;
    private Resident resident;

    public VisitRecord(String recordID, Visitor visitor, Resident resident, String visitPurpose) {
        this.recordID = recordID;
        this.visitor = visitor;
        this.resident = resident;
        this.visitPurpose = visitPurpose;
        this.checkInTime = LocalDateTime.now();
        this.checkOutTime = null;
    }
    
    public Visitor getVisitor() { return visitor; }
    public Resident getResident() { return resident; }
    public LocalDateTime getCheckOutTime() { return checkOutTime; }
    public void logCheckOut() { this.checkOutTime = LocalDateTime.now(); }
    
    public String generateReport() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String checkInStr = checkInTime.format(formatter);
        String checkOutStr = (checkOutTime != null) ? checkOutTime.format(formatter) : "Still Checked In";
        
        return String.format("Record ID: %s\n - Visitor: %s\n - Resident: %s\n - Purpose: %s\n - Check-in: %s\n - Check-out: %s\n",
                recordID, visitor.getName(), resident.getName(), visitPurpose, checkInStr, checkOutStr);
    }
}