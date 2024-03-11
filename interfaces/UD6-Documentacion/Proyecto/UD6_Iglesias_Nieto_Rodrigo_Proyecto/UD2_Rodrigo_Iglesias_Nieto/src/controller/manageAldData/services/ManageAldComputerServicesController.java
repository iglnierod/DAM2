/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manageAldData.services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.aldComputerService.AldComputerService;
import model.aldComputerService.computers.Computer;
import view.aldComputerService.ManageAldComputersDialog;
import view.aldComputerService.service.AddServiceDialog;

/**
 *
 * @author rodri
 */
public class ManageAldComputerServicesController {

    private final ManageAldComputersDialog view;
    private final AldComputerService model;
    private final Computer computer;

    public ManageAldComputerServicesController(ManageAldComputersDialog view, AldComputerService model, Computer computer) {
        this.view = view;
        this.model = model;
        this.computer = computer;
        addListeners();
    }

    public void addListeners() {
        this.view.setServiceForLabelText(computer.getSerialNumber());
        this.view.setContactText("");
        this.view.setAddServiceButtonListener(setAddServiceButtonListener());
    }

    private ActionListener setAddServiceButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddServiceDialog addServiceDialog = new AddServiceDialog(null, true);
                addServiceDialog.setVisible(true);
            }
        };
        return al;
    }
    
    
}
