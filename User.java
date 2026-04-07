package OOP_Project;

// User.java
public abstract class User {
    protected String userID;
    protected String name;
    protected char gender;
    protected String noPhone;
    protected int age;

    public User(String userID, String name, char gender, int age, String noPhone) {
        this.userID = userID;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.noPhone = noPhone;
    }

    public String getUserID() { return userID; }
    public String getName() { return name; }
    public abstract String displayDetails();
}