/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.checkBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.checkBox.CheckBoxDialog;

/**
 *
 * @author dides
 */
public class CheckBoxController {

    private final CheckBoxDialog view;

    public CheckBoxController(CheckBoxDialog view) {
        this.view = view;
        this.view.addGetOptionButtonActionListener(this.addGetSelectedOptionActionListener());
    }

    private ActionListener addGetSelectedOptionActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOptions = "";
                selectedOptions = view.getSelectedOptions();
                System.out.println("Selected option: " + selectedOptions);
            }

        };
        return al;

    }

}
