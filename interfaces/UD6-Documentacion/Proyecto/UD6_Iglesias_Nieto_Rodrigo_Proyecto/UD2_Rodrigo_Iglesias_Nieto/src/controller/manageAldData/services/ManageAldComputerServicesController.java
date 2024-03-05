/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manageAldData.services;

import model.aldComputerService.AldComputerService;
import model.aldComputerService.computers.Computer;
import view.aldComputerService.ManageAldComputersDialog;

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
    }

    public void addListeners(){}
    
    
}
