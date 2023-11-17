package view.booking.info;

import view.booking.BookingDataJDialog;

public class BookingInfoJDialog extends javax.swing.JDialog {
    
    public BookingInfoJDialog(BookingDataJDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roomExtrasbuttonGroup = new javax.swing.ButtonGroup();
        bookingHolderLabel = new javax.swing.JLabel();
        roomTypeComboBox = new javax.swing.JComboBox<>();
        bookingHolderTextField = new javax.swing.JTextField();
        roomTypeLabel = new javax.swing.JLabel();
        smokerCheckBox = new javax.swing.JCheckBox();
        wifiCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bookingHolderLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bookingHolderLabel.setText("Booking Holder:");

        roomTypeComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roomTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Double" }));
        roomTypeComboBox.setEnabled(false);

        bookingHolderTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bookingHolderTextField.setEnabled(false);

        roomTypeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roomTypeLabel.setText("Room Type:");

        roomExtrasbuttonGroup.add(smokerCheckBox);
        smokerCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        smokerCheckBox.setText("Smoker");

        roomExtrasbuttonGroup.add(wifiCheckBox);
        wifiCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        wifiCheckBox.setText("Wi-Fi");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bookingHolderLabel)
                            .addComponent(roomTypeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(roomTypeComboBox, 0, 290, Short.MAX_VALUE)
                            .addComponent(bookingHolderTextField)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wifiCheckBox)
                            .addComponent(smokerCheckBox))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookingHolderLabel)
                    .addComponent(bookingHolderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomTypeLabel))
                .addGap(18, 18, 18)
                .addComponent(smokerCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wifiCheckBox)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setBookingHolderTextFieldEnabled(boolean bool) {
        this.bookingHolderTextField.setEnabled(bool);
    }
    
    public void setRoomTypeComboBoxEnabled(boolean bool) {
        this.roomTypeComboBox.setEnabled(bool);
    }
    
    public void setSmokerCheckBoxEnabled(boolean bool) {
        this.smokerCheckBox.setEnabled(bool);
    }
    
    public void setWifiCheckBoxEnabled(boolean bool) {
        this.wifiCheckBox.setEnabled(bool);
    }
    
    public void setSmokerCheckBoxSelected(boolean selected) {
        this.smokerCheckBox.setSelected(selected);
    }
    
    public void setWifiCheckBoxSelected(boolean selected) {
        this.wifiCheckBox.setSelected(selected);
    }
    
    public void setSelectedItemRoomTypeComboBox(String roomType) {
        this.roomTypeComboBox.addItem(roomType);
    }
    
    public void setBookingHolderTextFieldText(String holder) {
        this.bookingHolderTextField.setText(holder);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookingHolderLabel;
    private javax.swing.JTextField bookingHolderTextField;
    private javax.swing.ButtonGroup roomExtrasbuttonGroup;
    private javax.swing.JComboBox<String> roomTypeComboBox;
    private javax.swing.JLabel roomTypeLabel;
    private javax.swing.JCheckBox smokerCheckBox;
    private javax.swing.JCheckBox wifiCheckBox;
    // End of variables declaration//GEN-END:variables
}
