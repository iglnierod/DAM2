package view.booking;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import model.PersonModel;
import model.RoomModel;

public class BookingDataJDialog extends javax.swing.JDialog {

    public BookingDataJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bookingHolderLabel = new javax.swing.JLabel();
        bookigHolderComboBox = new javax.swing.JComboBox<>();
        showDetailsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bookingHolderLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bookingHolderLabel.setText("Booking Holder:");

        bookigHolderComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        showDetailsButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        showDetailsButton.setText("Show booking details...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(bookingHolderLabel)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(showDetailsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bookigHolderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookingHolderLabel)
                    .addComponent(bookigHolderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(showDetailsButton)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setShowDetailsButtonListener(ActionListener listener) {
        this.showDetailsButton.addActionListener(listener);
    }
    
    public void añadirPersona(){
        PersonModel persona = new PersonModel();
        
        persona.setName("Pedro");
        
        RoomModel habitacion = new RoomModel("Single", true,true);
        persona.setRoom(habitacion);
        this.bookigHolderComboBox.addItem(persona);
    }

    public void addItemsToComboBox(Set<PersonModel> bookings) {
        for (PersonModel person : bookings) {
            //this.bookigHolderComboBox.addItem(person.toString());
        }
    }

    public PersonModel getBookingHolder() {
        return (PersonModel) bookigHolderComboBox.getSelectedItem();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<PersonModel> bookigHolderComboBox;
    private javax.swing.JLabel bookingHolderLabel;
    private javax.swing.JButton showDetailsButton;
    // End of variables declaration//GEN-END:variables
}
