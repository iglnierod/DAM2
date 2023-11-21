/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.aldComputerService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public abstract class Computer {

    private String serialNumber;
    private String brand;
    private String model;
    private int ramGB;

    public Computer(String serialNumber, String brand, String model) {
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.model = model;
        this.ramGB = 0;
    }

    public Computer(String serialNumber, String brand, String model, int ramGB) {
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.model = model;
        this.ramGB = ramGB;
    }

    public int getRamGB() {
        return ramGB;
    }

    public void setRamGB(int ramGB) {
        this.ramGB = ramGB;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public static HashMap<String, Computer> addDefaults() {

        Laptop laptop = new Laptop(50, 19, "Laptop-0", "Lenovo", "ThinkPad", 8);
        Server server = new Server(4, 2000, "Server-0", "AMD", "Server-0", 128);
        PersonalComputer pc = new PersonalComputer("Nvidia RTX-3060", "Intel I7-8700K", true, "Pc-01", "OMEN", "OBELISK", 16);

        HashMap<String, Computer> computers = new HashMap<>();

        computers.put(laptop.getSerialNumber(), laptop);
        computers.put(server.getSerialNumber(), server);
        computers.put(pc.getSerialNumber(), pc);

        return computers;
    }

    @Override
    public String toString() {
        return "Computer{"
                + "serialNumber='" + serialNumber + '\''
                + ", brand='" + brand + '\''
                + ", model='" + model + '\''
                + '}';
    }
}
