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
public class AldComputerService {
    
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
        computers = new HashMap<>();
    }

    public void addComputer(Computer computer) {
        this.computers.put(computer.getSerialNumber(), computer);
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
