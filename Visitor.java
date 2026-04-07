package OOP_Project;

// Visitor.java
public class Visitor extends User {
    private String visitorID;
    private String noIC;

    public Visitor(String userID, String name, int age, String noPhone, String noIC) {
        super(userID, name, 'U', age, noPhone);
        this.visitorID = userID;
        this.noIC = noIC;
    }

    public String getNoIC() { return noIC; }

    @Override
    public String displayDetails() {
        return String.format("Visitor Name: %s (ID: %s), IC: %s", name, userID, noIC);
    }
}
