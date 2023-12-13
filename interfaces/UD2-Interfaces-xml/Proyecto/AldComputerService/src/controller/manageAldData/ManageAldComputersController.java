/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manageAldData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.aldComputerService.AldComputerService;
import model.aldComputerService.Computer;
import model.aldComputerService.Laptop;
import view.aldComputerService.ManageAldComputersDialog;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class ManageAldComputersController {

    private final ManageAldComputersDialog view;
    private final AldComputerService model;

    private boolean isEdit;

    public ManageAldComputersController(ManageAldComputersDialog view, AldComputerService model) {
        this.view = view;
        this.model = model;
        this.view.setComputersPanelVisible(false);
        this.addListeners();
        this.addDefaultComputers();
        this.view.setComputerTypeComboBoxModel(this.addComputerTypeComboBoxModel());
        this.isEdit = false;
    }

    private void addListeners() {
        this.view.setAddButtonActionListener(setAddButtonListener());
        this.view.setCancelButtonActionListener(setCancelButtonListener());
        this.view.setSaveButtonActionListener(setSaveButtonListener());
        this.view.setEditButtonActionListener(setEditButtonListener());
        this.view.setDeleteButtonActionListener(setDeleteButtonListener());
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

    private DefaultComboBoxModel<String> addComputerTypeComboBoxModel() {
        DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<>();
        for (String item : this.model.getComputerType()) {
            cbModel.addElement(item);
        }
        return cbModel;
    }

    private void addComputer(Computer computer) {
        DefaultTableModel tableModel = (DefaultTableModel) this.view.getComputersTableModel();
        Object[] data = new Object[]{computer.getSerialNumber(), computer.getBrand(), computer.getModel()};
        tableModel.addRow(data);

        this.view.setComputersTableModel(tableModel);
    }

    private void editComputer(String serialNumber, String brand, String model) {
        Computer computer = new Laptop(serialNumber, brand, model);

        this.view.editSelectedRow(computer);
        this.model.editComputer(computer);
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

                isEdit = true;
            }
        };
        return al;
    }

    private ActionListener setDeleteButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> row = view.getComputersTableSelectedRow();
                if (row == null) {
                    return;
                }

                String key = row.get(0);

                int option = JOptionPane.showConfirmDialog(view, "Estás seguro que quieres eliminar el ordenador: " + key, "CONFIRMAR", JOptionPane.WARNING_MESSAGE);
                if (option == 0) {
                    view.deleteSelectedRow();
                    model.deleteComputer(key);
                }
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

                if (isEdit == false) {
                    Computer newComputer = new Laptop(serialNumber, brand, pcModel);
                    model.addComputer(newComputer);
                    addComputer(newComputer);
                } else {
                    editComputer(serialNumber, brand, pcModel);
                    isEdit = false;
                }

                view.setComputersTableEnabled(true);
                view.setComputersPanelVisible(false);
                //JOptionPane.showMessageDialog(view, "Ordenador añadido correctamente", "", JOptionPane.INFORMATION_MESSAGE);
                view.clearComputersPanelTextFields();

            }
        };
        return al;
    }

}
