/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manageAldData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
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
        this.addDefaultComputers();
    }

    private void addListeners() {
        this.view.setAddButtonActionListener(setAddButtonListener());
        this.view.setCancelButtonActionListener(setCancelButtonListener());
        this.view.setSaveButtonActionListener(setSaveButtonListener());
        this.view.setEditButtonActionListener(setEditButtonListener());
    }

    private void addDefaultComputers() {
        DefaultTableModel tableModel = (DefaultTableModel) this.view.getComputersTableModel();
        Collection<Computer> computers = this.model.getComputers().values();
        for (Computer computer : computers) {
            Object[] data = new Object[]{computer.getSerialNumber(), computer.getBrand(), computer.getModel()};
            tableModel.addRow(data);
        }
        this.view.setComputersTableModel(tableModel);
    }

    private void addComputer(Computer computer) {
        DefaultTableModel tableModel = (DefaultTableModel) this.view.getComputersTableModel();
        Object[] data = new Object[]{computer.getSerialNumber(), computer.getBrand(), computer.getModel()};
        tableModel.addRow(data);

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
                ArrayList<String> row = view.getComputersTableSelectedRow();
                if (row == null) {
                    return;
                }

                view.setSerialNumberText(row.get(0));
                view.setBrandText(row.get(1));
                view.setModelText(row.get(2));
                view.setComputersPanelVisible(true);
                view.setComputersTableEnabled(false);
            }
        };
        return al;
    }

    private ActionListener setCancelButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setComputersTableEnabled(true);
                view.clearComputersPanelTextFields();
                view.setComputersPanelVisible(false);
            }
        };
        return al;
    }

    private ActionListener setSaveButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serialNumber = view.getSerialNumberText();
                String brand = view.getBrandText();
                String pcModel = view.getModelText();

                if (serialNumber.isEmpty() && brand.isEmpty() && pcModel.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Es necesario llenar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Computer newComputer = new Laptop(serialNumber, brand, pcModel);
                model.addComputer(newComputer);
                addComputer(newComputer);
                view.setComputersTableEnabled(true);
                view.setComputersPanelVisible(false);
                //JOptionPane.showMessageDialog(view, "Ordenador añadido correctamente", "", JOptionPane.INFORMATION_MESSAGE);
                view.clearComputersPanelTextFields();
                
            }
        };
        return al;
    }

}
