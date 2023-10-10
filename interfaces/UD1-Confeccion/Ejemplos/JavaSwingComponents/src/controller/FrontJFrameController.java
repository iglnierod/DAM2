/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.messageDialog.MessageDialogController;
import controller.radioButton.RadioButtonController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainJFrame;
import view.messageDialog.MessageDialogDialog;
import view.radioButton.RadioButtonDialog;

/**
 *
 * @author dides
 */
public class FrontJFrameController {

    private MainJFrame view;

    public FrontJFrameController(MainJFrame view) {
        this.view = view;
        this.view.setQuitMenuItemListener(this.setQuitMenuItemActionListener());
        this.view.setMessageDialogMenuItemListener(setMessageDialogMenuItemActionListener());
        this.view.setRadioButtonDialogMenuItemListener(setRadioButtonMenuItemActionListener());
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

    private ActionListener setMessageDialogMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageDialogDialog md = new MessageDialogDialog(view, true);
                MessageDialogController mdc = new MessageDialogController(md);
                md.setVisible(true);
            }
        };
        return al;
    }

    private ActionListener setRadioButtonMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RadioButtonDialog rbd = new RadioButtonDialog(view, true);
                RadioButtonController rbc = new RadioButtonController(rbd);
                rbd.setVisible(true);
            }
        };
        return al;
    }
}
