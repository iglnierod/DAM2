/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class BookingModel {

    private HashMap<String, PersonModel> bookings;

    public BookingModel() {
        
    }

    public BookingModel(HashMap<String, PersonModel> bookings) {
        this.bookings = bookings;
    }

    public HashMap<String, PersonModel> getBookings() {
        return bookings;
    }

    public void setBookings(HashMap<String, PersonModel> bookings) {
        this.bookings = bookings;
    }
    
    public PersonModel findRoom(String person) {
        return bookings.get(person);
    }
}
