/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.aldComputerService;

import java.util.HashMap;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class AldComputerService {

    private final String[] COMPUTER_TYPE = new String[]{"Computer", "Laptop", "Personal Computer", "Server"};

    private String name;
    private String address;
    private String telephoneNumber;
    private int numberOfEmployees;
    private HashMap<String, Computer> computers = new HashMap<>();

    public AldComputerService() {
        this.name = "";
        this.address = "";
        this.telephoneNumber = "";
        this.numberOfEmployees = 0;
        computers = Computer.addDefaults();
        printComputers();
    }

    public void addComputer(Computer computer) {
        if (this.computers.get(computer.getSerialNumber()) == null) {
            this.computers.put(computer.getSerialNumber(), computer);
        } else {
            this.computers.replace(computer.getSerialNumber(), computer);
        }
        printComputers();
    }

    public void editComputer(Computer computer) {
        computers.replace(computer.getSerialNumber(), computer);
        printComputers();
    }

    public void deleteComputer(String key) {
        computers.remove(key);
        printComputers();
    }

    public void printComputers() {
        System.out.println("MODEL COMPUTERS:");
        for (Computer pc : computers.values()) {
            System.out.println(pc);
        }
    }

    public String getComputerType(int i) {
        return this.COMPUTER_TYPE[i];
    }

    public String[] getComputerType() {
        return this.COMPUTER_TYPE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public HashMap<String, Computer> getComputers() {
        return computers;
    }

    public void setComputers(HashMap<String, Computer> computers) {
        this.computers = computers;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n"
                + "Address: " + address + "\n"
                + "Telephone Number: " + telephoneNumber + "\n"
                + "Number of Employees: " + numberOfEmployees;
    }

}
