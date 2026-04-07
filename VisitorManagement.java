package OOP_Project;

// VisitorManagement.java
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VisitorManagement {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<VisitRecord> visitRecords = new ArrayList<>();
    private static ArrayList<IncidentReport> incidentReports = new ArrayList<>();
    private static Room[] rooms = new Room[18];

    public static void main(String[] args) {
        initializeData();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {
            displayMainMenu();
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        guardMenu(sc);
                        break;
                    case 2:
                        residentMenu(sc);
                        break;
                    case 3:
                        System.out.println("Exiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
        sc.close();
    }

    public static void initializeData() {
        rooms[0] = new Room(101); rooms[1] = new Room(102); rooms[2] = new Room(103);
        rooms[3] = new Room(104); rooms[4] = new Room(105); rooms[5] = new Room(106);
        rooms[6] = new Room(201); rooms[7] = new Room(202); rooms[8] = new Room(203);
        rooms[9] = new Room(204); rooms[10] = new Room(205); rooms[11] = new Room(206);
        rooms[12] = new Room(301); rooms[13] = new Room(302); rooms[14] = new Room(303);
        rooms[15] = new Room(304); rooms[16] = new Room(305); rooms[17] = new Room(306);
        
        users.add(new Resident("R001", "Alice", 'F', 30, "111-2222", rooms[0]));
        users.add(new Resident("R002", "Bob", 'M', 45, "333-4444", rooms[1]));
        users.add(new Guard("G001", "Charlie", 'M', 50, "555-6666"));

        System.out.println("System Initialized with dummy data and 18 rooms.\nInitialization of GuardID: G001, ResidentID: R001, R002");
    }

    public static void guardMenu(Scanner sc) {
        System.out.print("Enter Guard ID to login: ");
        String guardId = sc.nextLine();
        User user = findUserById(guardId);

        if (user == null || !(user instanceof Guard)) {
            System.out.println("Invalid Guard ID or access denied. Returning to main menu.");
            return;
        }
        Guard guard = (Guard) user;

        System.out.println("\nWelcome, " + guard.getName());
        int choice = 0;
        while (choice != 10) {
            System.out.println("\n--- Guard Menu ---");
            System.out.println("1. Register Visitor (Check-in)");
            System.out.println("2. Check-out Visitor");
            System.out.println("3. View Daily Visit Log");
            System.out.println("4. View All Residents");
            System.out.println("5. Add New Resident");
            System.out.println("6. Delete Resident");
            System.out.println("7. File Incident Report");
            System.out.println("8. View All Incident Reports");
            System.out.println("9. Update Incident Report Status");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");
            try {
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1: registerVisitor(sc); break;
                    case 2: checkOutVisitor(sc); break;
                    case 3: viewVisitLog(); break;
                    case 4: viewResidentList(); break;
                    case 5: addResident(sc); break;
                    case 6: deleteResident(sc); break;
                    case 7: fileIncidentReport(sc, guard); break;
                    case 8: viewIncidentReports(); break;
                    case 9: updateIncidentStatus(sc); break;
                    case 10: System.out.println("Logging out..."); break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }

    public static void residentMenu(Scanner sc) {
        System.out.print("Enter Resident ID to login: ");
        String residentId = sc.nextLine();
        User user = findUserById(residentId);

        if (user == null || !(user instanceof Resident)) {
            System.out.println("Invalid Resident ID or access denied. Returning to main menu.");
            return;
        }
        Resident resident = (Resident) user;
        
        System.out.println("\nWelcome, " + resident.getName());
        int choice = 0;
        while (choice != 4) {
            System.out.println("\n--- Resident Menu ---");
            System.out.println("1. View My Visitor History");
            System.out.println("2. File Incident Report");
            System.out.println("3. View All Incident Reports");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
             try {
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1: viewResidentVisitorHistory(resident); break;
                    case 2: fileIncidentReport(sc, resident); break;
                    case 3: viewIncidentReports(); break;
                    case 4: System.out.println("Logging out..."); break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }

    public static void displayMainMenu() {
        System.out.println("\n--- Visitor Management System ---");
        System.out.println("1. Login as Guard");
        System.out.println("2. Login as Resident");
        System.out.println("3. Exit");
    }

    public static void registerVisitor(Scanner sc) {
        System.out.println("\n--- New Visitor Registration ---");
        System.out.print("Enter Visitor Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Visitor IC Number: ");
        String ic = sc.nextLine();
        
        int roomNumber = 0;
        try {
            System.out.print("Enter Room Number to visit: ");
            roomNumber = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid room number.");
            return;
        }

        System.out.print("Enter purpose of visit: ");
        String purpose = sc.nextLine();
        
        Resident resident = null;
        for (User user : users) {
            if (user instanceof Resident && ((Resident) user).getRoom().getNoRoom() == roomNumber) {
                resident = (Resident) user;
                break;
            }
        }

        if (resident == null) {
            System.out.println("Error: No resident found for room '" + roomNumber + "'. Please check the room number.");
            return;
        }
        
        String visitorId = "V" + (users.size() + 1);
        Visitor newVisitor = new Visitor(visitorId, name, 0, "N/A", ic);
        users.add(newVisitor);

        String recordId = "VR" + (visitRecords.size() + 1);
        VisitRecord newRecord = new VisitRecord(recordId, newVisitor, resident, purpose);
        visitRecords.add(newRecord);

        System.out.println("Visitor " + name + " checked in successfully to visit " + resident.getName() + " in room " + resident.getRoom().toString() + "!");
    }

    public static void checkOutVisitor(Scanner sc) {
        System.out.print("Enter Visitor's IC number to check-out: ");
        String ic = sc.nextLine();

        VisitRecord recordToCheckout = null;
        for (VisitRecord record : visitRecords) {
            if (record.getVisitor().getNoIC().equalsIgnoreCase(ic) && record.getCheckOutTime() == null) {
                recordToCheckout = record;
                break;
            }
        }

        if (recordToCheckout != null) {
            recordToCheckout.logCheckOut();
            System.out.println("Visitor " + recordToCheckout.getVisitor().getName() + " checked out successfully.");
        } else {
            System.out.println("No active (still checked-in) visit record found for this IC number.");
        }
    }

    public static void viewVisitLog() {
        System.out.println("\n--- Daily Visit Log ---");
        if (visitRecords.isEmpty()) {
            System.out.println("No visits recorded today.");
            return;
        }
        for (VisitRecord record : visitRecords) {
            System.out.println(record.generateReport());
        }
    }
    
    public static void viewResidentVisitorHistory(Resident resident) {
        System.out.println("\n--- Visitor History for " + resident.getName() + " ---");
        boolean found = false;
        for (VisitRecord record : visitRecords) {
            if (record.getResident().getUserID().equals(resident.getUserID())) {
                System.out.println(record.generateReport());
                found = true;
            }
        }
        if (!found) {
            System.out.println("You have no visitor history.");
        }
    }

    public static void fileIncidentReport(Scanner sc, IReportable reporter) {
        System.out.println("\n--- File New Incident Report ---");
        System.out.print("Enter Location of Incident: ");
        String location = sc.nextLine();
        System.out.print("Enter Description of Incident: ");
        String description = sc.nextLine();
        
        char severity = ' ';
        while (severity != 'L' && severity != 'M' && severity != 'H') {
            System.out.print("Enter Severity (L-Low, M-Medium, H-High): ");
            String input = sc.nextLine().toUpperCase();
            if (!input.isEmpty()) {
                severity = input.charAt(0);
            }
            if (severity != 'L' && severity != 'M' && severity != 'H') {
                System.out.println("Invalid severity. Please enter L, M, or H.");
            }
        }
        
        reporter.fileIncidentReport(description, location, severity, incidentReports);
    }
    
    public static void viewIncidentReports() {
        System.out.println("\n--- All Filed Incident Reports ---");
        if (incidentReports.isEmpty()) {
            System.out.println("There are no incident reports to show.");
            return;
        }

        for (IncidentReport report : incidentReports) {
            User reporter = findUserById(report.getReporterID());
            String reporterName = (reporter != null) ? reporter.getName() : "Unknown Reporter";
            String reporterRole = (reporter instanceof Guard) ? "Guard" : "Resident";

            System.out.println("----------------------------------------");
            System.out.printf("Reported By: %s (%s, ID: %s)\n", reporterName, reporterRole, report.getReporterID());
            System.out.println(report.toString());
        }
        System.out.println("----------------------------------------");
    }

    public static void updateIncidentStatus(Scanner sc) {
    if (incidentReports.isEmpty()) {
        System.out.println("No incident reports available to update.");
        return;
    }
    System.out.println("\n--- Update Incident Status ---");
    // List all incident reports with a number label
    for (int i = 0; i < incidentReports.size(); i++) {
        IncidentReport report = incidentReports.get(i);
        User reporter = findUserById(report.getReporterID());
        String reporterName = (reporter != null) ? reporter.getName() : "Unknown Reporter";
        String reporterRole = (reporter instanceof Guard) ? "Guard" : "Resident";
        System.out.println((i + 1) + ". Reported By: " + reporterName + " (" + reporterRole + ", ID: " + report.getReporterID() + ")");
        System.out.println(report.toString());
        System.out.println("----------------------------------------");
    }
    System.out.print("Select the incident number to update (1-" + incidentReports.size() + "): ");
    int idx;
    try {
        idx = sc.nextInt();
        sc.nextLine();
        if (idx < 1 || idx > incidentReports.size()) {
            System.out.println("Invalid selection.");
            return;
        }
    } catch (InputMismatchException e) {
        System.out.println("Invalid input.");
        sc.nextLine();
        return;
    }
    IncidentReport selectedReport = incidentReports.get(idx - 1);

    System.out.println("Select new status:");
    System.out.println("1. In Progress");
    System.out.println("2. Resolved");
    System.out.println("3. Closed");
    System.out.print("Enter your choice (1-3): ");
    int statusChoice;
    try {
        statusChoice = sc.nextInt();
        sc.nextLine();
    } catch (InputMismatchException e) {
        System.out.println("Invalid input.");
        sc.nextLine();
        return;
    }
    String newStatus;
    switch (statusChoice) {
        case 1: newStatus = "In Progress"; break;
        case 2: newStatus = "Resolved"; break;
        case 3: newStatus = "Closed"; break;
        default:
            System.out.println("Invalid status choice.");
            return;
    }
    selectedReport.updateStatus(newStatus);
    System.out.println("Incident status updated to: " + newStatus);
}

    public static void addResident(Scanner sc) {
        System.out.println("\n--- Add New Resident ---");
        try {
            System.out.print("Enter Resident Name: ");
            String name = sc.nextLine();
            
            char gender = ' ';
            while (gender != 'M' && gender != 'F') {
                System.out.print("Enter Gender (M/F): ");
                String input = sc.nextLine().toUpperCase();
                if (!input.isEmpty()) {
                    gender = input.charAt(0);
                }
                if (gender != 'M' && gender != 'F') {
                    System.out.println("Invalid input. Please enter 'M' for Male or 'F' for Female.");
                }
            }

            System.out.print("Enter Phone Number: ");
            String phone = sc.nextLine();
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            ArrayList<Room> availableRooms = getAvailableRooms();
            if (availableRooms.isEmpty()) {
                System.out.println("Error: No available rooms to assign. Cannot add new resident.");
                return;
            }
            System.out.println("Available Rooms:");
            for (int i = 0; i < availableRooms.size(); i++) {
                System.out.println((i + 1) + ". Room " + availableRooms.get(i).toString());
            }
            System.out.print("Select a room (1-" + availableRooms.size() + "): ");
            int roomChoice = sc.nextInt();
            sc.nextLine();
            if (roomChoice < 1 || roomChoice > availableRooms.size()) {
                System.out.println("Invalid room selection.");
                return;
            }
            Room selectedRoom = availableRooms.get(roomChoice - 1);

            int residentCount = 0;
            for (User user : users) {
                if (user instanceof Resident) {
                    residentCount++;
                }
            }
            String newId = "R" + String.format("%03d", residentCount + 1);

            Resident newResident = new Resident(newId, name, gender, age, phone, selectedRoom);
            users.add(newResident);
            System.out.println("Successfully added new resident: " + newResident.displayDetails());

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Age must be a number. Please try again.");
            sc.nextLine();
        }
    }
    
    public static void deleteResident(Scanner sc) {
        System.out.println("\n--- Delete Resident ---");
        viewResidentList();
        
        System.out.print("\nEnter the ID of the resident to delete: ");
        String idToDelete = sc.nextLine();
        
        User residentToDelete = null;
        for (User user : users) {
            if (user.getUserID().equalsIgnoreCase(idToDelete) && user instanceof Resident) {
                residentToDelete = user;
                break;
            }
        }
        
        if (residentToDelete != null) {
            String residentName = residentToDelete.getName();
            users.remove(residentToDelete);
            System.out.println("Success: Resident " + residentName + " (ID: " + idToDelete + ") has been deleted.");
        } else {
            System.out.println("Error: Resident ID not found or the ID does not belong to a resident.");
        }
    }

    public static void viewResidentList() {
        System.out.println("\n--- List of All Residents ---");
        boolean found = false;
        
        for (User user : users) {
            if (user instanceof Resident) {
                System.out.println("- " + user.displayDetails());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No residents are currently registered in the system.");
        }
    }
    
    public static User findUserById(String id) {
        for (User user : users) {
            if (user.getUserID().equalsIgnoreCase(id)) {
                return user;
            }
        }
        return null;
    }

    public static boolean isRoomAssigned(Room roomToCheck) {
        for (User user : users) {
            if (user instanceof Resident) {
                Resident resident = (Resident) user;
                if (resident.getRoom().equals(roomToCheck)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Room> getAvailableRooms() {
        ArrayList<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (!isRoomAssigned(room)) {
                available.add(room);
            }
        }
        return available;
    }
}