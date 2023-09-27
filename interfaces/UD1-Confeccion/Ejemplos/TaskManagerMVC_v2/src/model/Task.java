/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class Task {
    // ATRIBUTES
    private String description;
    private boolean done;

    // CONSTRUCTOR
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    // GETTER
    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    // SETTER
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
    
}
