/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.aldComputerService;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class Laptop extends Computer {
    
    private int batteryWHR;
    private int displayInches;

    public Laptop(int batteryWHR, int displayInches, String serialNumber, String brand, String model, int ramGB) {
        super(serialNumber, brand, model, ramGB);
        this.batteryWHR = batteryWHR;
        this.displayInches = displayInches;
    }

    public int getBatteryWHR() {
        return batteryWHR;
    }

    public void setBatteryWHR(int batteryWHR) {
        this.batteryWHR = batteryWHR;
    }

    public int getDisplayInches() {
        return displayInches;
    }

    public void setDisplayInches(int displayInches) {
        this.displayInches = displayInches;
    }
    
    
}