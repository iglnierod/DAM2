/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.aldComputerService.service;

import java.security.Timestamp;

/**
 *
 * @author rodri
 */
public class Service {

    private ServiceType serviceType;
    private String description;
    private String comments;
    private Timestamp registerDate;
    private String contact;
    private int phoneContact;
    private boolean repaired;
    private Timestamp serviceDate;
    private float price;

    public Service() {
    }

    public Service(ServiceType serviceType, String description, String comments,
            Timestamp registerDate, String contact, int phoneContact, boolean repaired,
            Timestamp serviceDate, float price) {
        this.serviceType = serviceType;
        this.description = description;
        this.comments = comments;
        this.registerDate = registerDate;
        this.contact = contact;
        this.phoneContact = phoneContact;
        this.repaired = repaired;
        this.serviceDate = serviceDate;
        this.price = price;
    }

    public Service(ServiceType serviceType, String description, String comments, 
            Timestamp registerDate, String contact, int phoneContact) {
        this.serviceType = serviceType;
        this.description = description;
        this.comments = comments;
        this.registerDate = registerDate;
        this.contact = contact;
        this.phoneContact = phoneContact;
        
        this.repaired = false;
        this.serviceDate = null;
        this.price = 0;
    }

    
    
    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(int phoneContact) {
        this.phoneContact = phoneContact;
    }

    public boolean isRepaired() {
        return repaired;
    }

    public void setRepaired(boolean repaired) {
        this.repaired = repaired;
    }

    public Timestamp getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Timestamp serviceDate) {
        this.serviceDate = serviceDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Repair{" + "repairType=" + serviceType + ", description=" + description
                + ", comments=" + comments + ", registerDate=" + registerDate
                + ", contact=" + contact + ", phoneContact=" + phoneContact
                + ", repaired=" + repaired + ", repairDate=" + serviceDate
                + ", price=" + price + '}';
    }

}
