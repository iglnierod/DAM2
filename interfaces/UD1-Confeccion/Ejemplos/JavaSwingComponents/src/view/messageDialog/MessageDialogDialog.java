/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.messageDialog;

import java.awt.event.ActionListener;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class MessageDialogDialog extends javax.swing.JDialog {

    /**
     * Creates new form MessageDialogDialog
     */
    public MessageDialogDialog(java.awt.Frame parent, boolean modal) {
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

        messageDialogLabel = new javax.swing.JLabel();
        showMessageDialogButton = new javax.swing.JButton();
        showConfirmDialogButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        messageDialogLabel.setText("Message Dialog");

        showMessageDialogButton.setText("Show Message Dialog");
        showMessageDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showMessageDialogButtonActionPerformed(evt);
            }
        });

        showConfirmDialogButton.setText("Show Confirm Dialog");
        showConfirmDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showConfirmDialogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(messageDialogLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(173, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(showConfirmDialogButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(showMessageDialogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(messageDialogLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(showMessageDialogButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showConfirmDialogButton)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showMessageDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showMessageDialogButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showMessageDialogButtonActionPerformed

    private void showConfirmDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showConfirmDialogButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showConfirmDialogButtonActionPerformed

    public void addMessageDialogActionListener(ActionListener listener) {
        this.showMessageDialogButton.addActionListener(listener);
    }

    public void addConfirmDialogActionListener(ActionListener listener) {
        this.showConfirmDialogButton.addActionListener(listener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel messageDialogLabel;
    private javax.swing.JButton showConfirmDialogButton;
    private javax.swing.JButton showMessageDialogButton;
    // End of variables declaration//GEN-END:variables
}
