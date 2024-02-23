/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manageAldData.computers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.aldComputerService.AldComputerService;
import model.aldComputerService.computers.Computer;
import model.aldComputerService.computers.ComputerType;
import model.aldComputerService.computers.Laptop;
import model.aldComputerService.computers.PersonalComputer;
import model.aldComputerService.computers.Server;
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
        this.view.setEnabledServiceTabbedPane(false);
    }

    private void addListeners() {
        this.view.setAddButtonActionListener(setAddButtonListener());
        this.view.setCancelButtonActionListener(setCancelButtonListener());
        this.view.setSaveButtonActionListener(setSaveButtonListener());
        this.view.setEditButtonActionListener(setEditButtonListener());
        this.view.setDeleteButtonActionListener(setDeleteButtonListener());
        this.view.setComputersTableListSelectionListener(setComputersTableListSelectionListener());
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
        for (ComputerType type : ComputerType.values()) {
            cbModel.addElement(type.toString());
            System.out.println(type);
        }
        return cbModel;
    }

    private void addComputer(Computer computer) {
        DefaultTableModel tableModel = (DefaultTableModel) this.view.getComputersTableModel();
        Object[] data = new Object[]{computer.getSerialNumber(), computer.getBrand(), computer.getModel()};
        tableModel.addRow(data);

        this.view.setComputersTableModel(tableModel);
    }

    private void editComputer(String serialNumber, String brand, String pcModel, ComputerType computerType) {
        Computer c = model.getComputer(serialNumber);
        c.setBrand(brand);
        c.setModel(pcModel);
        c.setComputerType(computerType);

        this.view.editSelectedRow(c);
        this.model.editComputer(c);
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
                Computer computer = model.getComputer(row.get(0));
                view.setComputerTypeComboBoxSelected(computer.getComputerType().ordinal());
                view.setSerialNumberText(computer.getSerialNumber());
                view.enableSerialNumberTextField(false);
                view.setBrandText(computer.getBrand());
                view.setModelText(computer.getModel());
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
                view.enableSerialNumberTextField(true);
            }
        };
        return al;
    }

    private ActionListener setSaveButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComputerType computerType = ComputerType.valueOf(view.getComputerTypeComboBoxSelected());
                String serialNumber = view.getSerialNumberText().trim();
                String brand = view.getBrandText().trim();
                String pcModel = view.getModelText().trim();

                if (serialNumber.isEmpty() || brand.isEmpty() || pcModel.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Es necesario llenar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (isEdit == false) {
                    Computer computer = null;
                    switch (computerType) {
                        case LAPTOP:
                            computer = new Laptop(serialNumber, brand, pcModel, computerType);
                            break;
                        case PERSONAL_COMPUTER:
                            computer = new PersonalComputer(serialNumber, brand, pcModel, computerType);
                            break;
                        case SERVER:
                            computer = new Server(serialNumber, brand, pcModel, computerType);
                            break;
                        default:
                            System.err.println("Error while creating pc");
                    }
                    if (model.addComputer(computer)) {
                        addComputer(computer);
                    } else {
                        JOptionPane.showMessageDialog(view, "No se ha podido añadir el ordenador", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                } else {
                    editComputer(serialNumber, brand, pcModel, computerType);
                    isEdit = false;
                }

                view.setComputersTableEnabled(true);
                view.setComputersPanelVisible(false);
                //JOptionPane.showMessageDialog(view, "Ordenador añadido correctamente", "", JOptionPane.INFORMATION_MESSAGE);
                view.clearComputersPanelTextFields();
                view.enableSerialNumberTextField(true);
            }
        };
        return al;
    }

    private ListSelectionListener setComputersTableListSelectionListener() {
        ListSelectionListener listener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
            }
        };
        return listener;
    }
}
