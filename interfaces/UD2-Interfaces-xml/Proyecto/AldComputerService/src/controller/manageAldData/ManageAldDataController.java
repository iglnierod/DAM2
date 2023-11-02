/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manageAldData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.aldComputerService.AldComputerService;
import view.aldComputerService.ManageAldDataDialog;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class ManageAldDataController {

    private final ManageAldDataDialog view;
    private final AldComputerService model;

    public ManageAldDataController(ManageAldDataDialog view, AldComputerService model) {
        this.view = view;
        this.model = model;
        addAllListeners();
        initComponents();
    }

    private void addAllListeners() {
        this.view.setSaveButtonActionListener(setSaveButtonListener());
        this.view.setEditButtonActionListener(setEditButtonListener());
        this.view.setCancelButtonActionListener(setCancelButtonListner());
    }

    public void initComponents() {
        if (model.getName().isEmpty()) {
            view.setEnabledSaveButton(true);
            view.setEnabledEditButton(false);
        } else {
            loadData();
            view.setEnabledSaveButton(false);
            view.setEnabledEditButton(true);
            view.setEnabledUserInputs(false);
        }
    }

    private void loadData() {
        view.setNameText(model.getName());
        view.setAddressText(model.getAddress());
        view.setTelephoneNumberText(model.getTelephoneNumber());
        view.setNumberOfEmployeesValue(model.getNumberOfEmployees());
    }
    
    private ActionListener setSaveButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setName(view.getNameText());
                model.setAddress(view.getAddressText());
                model.setTelephoneNumber(view.getTelephoneNumberText());
                model.setNumberOfEmployees(view.getNumberOfEmployeesValue());
                System.out.println("~~~~~~~~ Se ha guardado el modelo: ~~~~~~~~");
                System.out.println(model);
                initComponents();
            }
        };
        return al;
    }

    private ActionListener setEditButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setEnabledSaveButton(true);
                view.setEnabledUserInputs(true);
            }
        };
        return al;
    }

    private ActionListener setCancelButtonListner() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        };
        return al;
    }
}
