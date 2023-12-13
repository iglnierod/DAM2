/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.FrontControllerJFrame;
import model.aldComputerService.AldComputerService;
import view.MainJFrame;

/**
 *
 * @author dides
 */
public class Main {

    public static void main(String[] args) {
        MainJFrame mainview = new MainJFrame();
        AldComputerService model = new AldComputerService();
        FrontControllerJFrame fc = new FrontControllerJFrame(mainview, model);
        mainview.setVisible(true);
    }
}
