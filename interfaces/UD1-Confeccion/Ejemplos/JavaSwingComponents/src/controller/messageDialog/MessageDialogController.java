/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.messageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.messageDialog.MessageDialogDialog;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class MessageDialogController {

    private final MessageDialogDialog view;

    public MessageDialogController(MessageDialogDialog view) {
        this.view = view;
        this.view.addMessageDialogActionListener(addMessageDialogActionListener());
        this.view.addConfirmDialogActionListener(addConfirmDialogActionListener());
    }

    private ActionListener addMessageDialogActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(view, "Demo text");
            }
        };

        return al;
    }
    
        private ActionListener addConfirmDialogActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(view, "Aceptar o Cancelar?");
                System.out.println("ConfirmDialog return value: " + result);
            }
        };

        return al;
    }
}
