/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.comboBox;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.comboBox.ComboBoxDialog;

/**
 *
 * @author dides
 */
public class ComboBoxController {

    private final ComboBoxDialog view;

    public ComboBoxController(ComboBoxDialog view) {
        this.view = view;
        this.initColorComboBoxes();
        this.view.addSetColorButtonActionListener(this.addSetColorButtomActionListener());
    }

    private void initColorComboBoxes() {
        for (int c = 0; c <= 255; c++) {
            String color = Integer.toString(c);
            view.addRedComboBoxItem(color);
            view.addGreenComboBoxItem(color);
            view.addBlueComboBoxItem(color);
        }
    }

    private ActionListener addSetColorButtomActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r= Integer.parseInt(view.getRedComboBoxItem());
                int g=Integer.parseInt(view.getGreenComboBoxItem());
                int b=Integer.parseInt(view.getBlueComboBoxItem());
                Color c=new Color(r,g,b);
                view.setPanelColor(c);
            }
        };
        return al;
    }

}
