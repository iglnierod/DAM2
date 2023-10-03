/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.CalculatorJDialogController;
import controller.FrontJFrameController;
import view.MainJFrame;
import view.calculator.CalculatorJDialog;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class Main {
    public static void main(String[] args) {
        MainJFrame mainView = new MainJFrame();
        FrontJFrameController fc = new FrontJFrameController(mainView);
        mainView.setVisible(true);
    }
}
