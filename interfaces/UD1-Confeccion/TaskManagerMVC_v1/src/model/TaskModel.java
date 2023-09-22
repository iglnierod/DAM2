/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class TaskModel {

    // ATRIBUTOS
    private List<Task> tasks;

    // CONSTRUCTOR
    public TaskModel() {
        this.tasks = new ArrayList<>();
    }

    // METODOS
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    // GETTER
    public List<Task> getAllTasks() {
        return this.tasks;
    }

}
