/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.FrontJFrameController;
import model.taskManager.TaskList;
import view.MainJFrame;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class Main {
    public static void main(String[] args) {
        MainJFrame mainview = new MainJFrame();
        TaskList taskModel = new TaskList();
        FrontJFrameController fc = new FrontJFrameController(mainview, taskModel);
        mainview.setVisible(true);
    }
}
