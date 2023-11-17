package model;

public class PersonModel {

    private String name;
    private RoomModel room;
    
    public PersonModel() {
        super();
    }

    // Constructor
    public PersonModel(String name, RoomModel room) {
        this.name = name;
        this.room = room;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomModel getRoom() {
        return room;
    }

    public void setRoom(RoomModel room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return name; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
