package OOP_Project;

// Room.java
public class Room {
    private int noRoom;

    public Room(int noRoom) {
        this.noRoom = noRoom;
    }

    public int getNoRoom() { return noRoom; }

    @Override
    public String toString() { 
        return String.valueOf(noRoom); 
    }
}