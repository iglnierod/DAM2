/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.aldComputerService;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class Server extends Computer {

    private int processors;
    private double storageGB;

    public Server(String serialNumber, String brand, String model, ComputerType computerType) {
        super(serialNumber, brand, model, computerType);
    }

    public Server(int processors, double storageGB, String serialNumber, String brand, String model, ComputerType computerType) {
        super(serialNumber, brand, model, computerType);
        this.processors = processors;
        this.storageGB = storageGB;
    }

    public int getProcessors() {
        return processors;
    }

    public void setProcessors(int processors) {
        this.processors = processors;
    }

    public double getStorageGB() {
        return storageGB;
    }

    public void setStorageGB(double storageGB) {
        this.storageGB = storageGB;
    }

}
