/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainJFrame;
import view.calculator.CalculatorJDialog;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class FrontJFrameController {
    private MainJFrame view = new MainJFrame();

    public FrontJFrameController(MainJFrame view) {
        this.view = view;
        this.view.setExitMenuItemListener(setExitMenuItemActionListener());
        this.view.setCalculatorMenuItemListener(setCalculatorItemActionListener());
    }
    
    private ActionListener setExitMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        };
        return al;
    }

    private ActionListener setCalculatorItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorJDialog calculator = new CalculatorJDialog(view, true);
                CalculatorJDialogController controller = new CalculatorJDialogController(calculator);
                calculator.setVisible(true);
            }
        };
        return al;
    }
}
