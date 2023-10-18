/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.salonPalace.SalonPalaceController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainJFrame;
import view.salonPalace.SalonPalaceDialog;

/**
 *
 * @author dides
 */
public class FrontControllerJFrame {

    private MainJFrame view;

    public FrontControllerJFrame(MainJFrame view) {
        this.view = view;
        this.view.setQuitMenuItemListener(this.setQuitMenuItemActionListener());
        this.view.setSalonPalaceMenuItemListener(setSalonPalaceMenuItemActionListener());
    }

    private ActionListener setQuitMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                System.exit(0);
            }
        };
        return al;
    }

    private ActionListener setSalonPalaceMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalonPalaceDialog spd = new SalonPalaceDialog(view, true);
                SalonPalaceController mdc = new SalonPalaceController(spd);
                spd.setVisible(true);
            }
        };
        return al;
    }

}
