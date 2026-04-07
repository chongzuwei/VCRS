package OOP_Project;

// IReportable.java
import java.util.ArrayList;

public interface IReportable {
    IncidentReport fileIncidentReport(String description, String location, char severity, ArrayList<IncidentReport> reportList);
}
