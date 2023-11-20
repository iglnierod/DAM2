/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.aldComputerService;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.aldComputerService.Computer;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class ManageAldComputersDialog extends javax.swing.JDialog {

    /**
     * Creates new form ManageAldComputers
     */
    public ManageAldComputersDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        manageComputersTabbedPane = new javax.swing.JTabbedPane();
        computersPanel = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        computersTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        computerDataPanel = new javax.swing.JPanel();
        serialNumberLabel = new javax.swing.JLabel();
        serialNumberTextField = new javax.swing.JTextField();
        brandLabel = new javax.swing.JLabel();
        brandTextField = new javax.swing.JTextField();
        modelLabel = new javax.swing.JLabel();
        modelTextField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        computersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serial number", "Brand", "Model"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableScrollPane.setViewportView(computersTable);

        addButton.setText("Add");

        computerDataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Computer data"));
        computerDataPanel.setToolTipText("");

        serialNumberLabel.setText("Serial number:");

        brandLabel.setText("Brand:");

        modelLabel.setText("Model:");

        saveButton.setText("Save");

        cancelButton.setText("Cancel");

        javax.swing.GroupLayout computerDataPanelLayout = new javax.swing.GroupLayout(computerDataPanel);
        computerDataPanel.setLayout(computerDataPanelLayout);
        computerDataPanelLayout.setHorizontalGroup(
            computerDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, computerDataPanelLayout.createSequentialGroup()
                .addGroup(computerDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(computerDataPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saveButton))
                    .addGroup(computerDataPanelLayout.createSequentialGroup()
                        .addGroup(computerDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(computerDataPanelLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(computerDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(brandLabel)
                                    .addComponent(modelLabel)))
                            .addGroup(computerDataPanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(serialNumberLabel)))
                        .addGap(18, 18, 18)
                        .addGroup(computerDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serialNumberTextField)
                            .addComponent(brandTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                            .addComponent(modelTextField))))
                .addGap(28, 28, 28))
        );
        computerDataPanelLayout.setVerticalGroup(
            computerDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(computerDataPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(computerDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serialNumberLabel)
                    .addComponent(serialNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(computerDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brandLabel)
                    .addComponent(brandTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(computerDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modelLabel)
                    .addComponent(modelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(computerDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        editButton.setText("Edit");

        javax.swing.GroupLayout computersPanelLayout = new javax.swing.GroupLayout(computersPanel);
        computersPanel.setLayout(computersPanelLayout);
        computersPanelLayout.setHorizontalGroup(
            computersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(computersPanelLayout.createSequentialGroup()
                .addGap(0, 43, Short.MAX_VALUE)
                .addGroup(computersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(computersPanelLayout.createSequentialGroup()
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addButton))
                    .addComponent(computerDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        computersPanelLayout.setVerticalGroup(
            computersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(computersPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(computersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(editButton))
                .addGap(18, 18, 18)
                .addComponent(computerDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        manageComputersTabbedPane.addTab("Computers", computersPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(manageComputersTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(manageComputersTabbedPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setAddButtonActionListener(ActionListener listener) {
        this.addButton.addActionListener(listener);
    }

    public void setCancelButtonActionListener(ActionListener listener) {
        this.cancelButton.addActionListener(listener);
    }

    public void setEditButtonActionListener(ActionListener listener) {
        this.editButton.addActionListener(listener);
    }

    public void setSaveButtonActionListener(ActionListener listener) {
        this.saveButton.addActionListener(listener);
    }

    public void setEnabledSerialnumberTextField(boolean bool) {
        this.serialNumberTextField.setEnabled(bool);
    }

    public void setEnabledBrandTextField(boolean bool) {
        this.brandTextField.setEnabled(bool);
    }

    public void setEnabledModelTextField(boolean bool) {
        this.modelTextField.setEnabled(bool);
    }

    public void setEnabledSerialNumberTextField(boolean bool) {
        this.serialNumberTextField.setEnabled(bool);
    }

    /*public void addRowToComputersTable(HashMap<Integer, Computer> computers) {
        Collection<Computer> computersCollection = computers.values();
        DefaultTableModel model = (DefaultTableModel) computersTable.getModel();
        Object[] row;
        for (Computer computer : computersCollection) {
            row = new Object[]{computer.getSerialNumber(), computer.getBrand(), computer.getModel()};
            model.addRow(row);
        }
        computersTable.setModel(model);
    }*/
    public void setComputersTableModel(TableModel model) {
        this.computersTable.setModel(model);
    }

    public void setComputersPanelVisible(boolean bool) {
        computerDataPanel.setVisible(bool);
    }

    public TableModel getComputersTableModel() {
        return computersTable.getModel();
    }

    public String getSerialNumberText() {
        return this.serialNumberTextField.getText();
    }

    public String getBrandText() {
        return this.brandTextField.getText();
    }

    public String getModelText() {
        return this.modelTextField.getText();
    }

    public void setSerialNumberText(String txt) {
        this.serialNumberTextField.setText(txt);
    }

    public void setBrandText(String txt) {
        this.brandTextField.setText(txt);
    }

    public void setModelText(String txt) {
        this.modelTextField.setText(txt);
    }

    public void clearComputersPanelTextFields() {
        this.serialNumberTextField.setText("");
        this.brandTextField.setText("");
        this.modelTextField.setText("");
    }

    public boolean isComputersTableRowSelected() {
        return computersTable.getSelectedRow() != -1;
    }

    public ArrayList<String> getComputersTableSelectedRow() {
        ArrayList<String> row = new ArrayList<>();
        if (isComputersTableRowSelected()) {
            int selectedRow = this.computersTable.getSelectedRow();
            for (int i = 0; i < 3; i++) {
                row.add((String) computersTable.getValueAt(selectedRow, i));
            }
            return row;
        }

        return null;
    }

    public void setComputersTableEnabled(boolean bool) {
        this.computersTable.setEnabled(bool);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel brandLabel;
    private javax.swing.JTextField brandTextField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel computerDataPanel;
    private javax.swing.JPanel computersPanel;
    private javax.swing.JTable computersTable;
    private javax.swing.JButton editButton;
    private javax.swing.JTabbedPane manageComputersTabbedPane;
    private javax.swing.JLabel modelLabel;
    private javax.swing.JTextField modelTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel serialNumberLabel;
    private javax.swing.JTextField serialNumberTextField;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
