/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.FrontController;
import controller.TaskViewController;
import model.TaskModel;
import view.TaskView;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class Main {
    public static void main(String[] args) {
        TaskModel model = new TaskModel();
        TaskView view = new TaskView();
        FrontController controller = new FrontController(model, view);
    }
}
