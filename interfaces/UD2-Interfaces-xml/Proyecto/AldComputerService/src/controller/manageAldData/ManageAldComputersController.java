/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manageAldData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.table.DefaultTableModel;
import model.aldComputerService.AldComputerService;
import model.aldComputerService.Computer;
import model.aldComputerService.Laptop;
import model.aldComputerService.Server;
import view.aldComputerService.ManageAldComputersDialog;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class ManageAldComputersController {

    private final ManageAldComputersDialog view;
    private final AldComputerService model;

    public ManageAldComputersController(ManageAldComputersDialog view, AldComputerService model) {
        this.view = view;
        this.model = model;
        view.setComputersPanelVisible(false);
        this.addListeners();
    }

    private void addListeners() {
        this.view.setAddButtonActionListener(setAddButtonListener());
        this.view.setCancelButtonActionListener(setCancelButtonListener());
    }

    private void addComputers() {
        Laptop laptop = new Laptop(50, 19, "Laptop-0", "Lenovo", "ThinkPad", 8);
        Server server = new Server(4, 2000, "Server-0", "AMD", "Server-0", 128);
    }
    
    private void addRowsToTable() {
        DefaultTableModel tableModel = (DefaultTableModel) this.view.getComputersTableModel();
        Collection<Computer> computersCollection = model.getComputers().values();
        Object[] row;
        for (Computer computer : computersCollection) {
            row = new Object[]{computer.getSerialNumber(), computer.getBrand(), computer.getModel()};
            tableModel.addRow(row);
        }
        this.view.setComputersTableModel(tableModel);
    }

    private ActionListener setAddButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setComputersPanelVisible(true);
            }
        };
        return al;
    }

    private ActionListener setEditButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Agregar lógica para el botón "Edit" aquí
            }
        };
        return al;
    }

    private ActionListener setCancelButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.clearComputersPanelFields();
                view.setComputersPanelVisible(false);
            }
        };
        return al;
    }

    private ActionListener setSaveButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Agregar lógica para el botón "Save" aquí
            }
        };
        return al;
    }

}
