/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.TaskModel;
import view.TaskView;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class FrontController {
    // ATRIBUTES
    private final TaskModel model;
    private final TaskView view;
    
    //CONSTRUCTOR
    public FrontController(TaskModel model, TaskView view) {
        this.model = model;
        this.view = view;
        this.setTaskViewController();
    }
    
    private void  setTaskViewController() {
        TaskViewController tc = new TaskViewController(model, view);
        tc.setVisible();
    }
    
    
}
