package view;

import java.awt.event.ActionListener;

public class FrontViewJFrame extends javax.swing.JFrame {


    public FrontViewJFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        quitMenuItem = new javax.swing.JMenuItem();
        bookingMenu = new javax.swing.JMenu();
        bookingMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setText("File");

        quitMenuItem.setText("Quit");
        fileMenu.add(quitMenuItem);

        jMenuBar1.add(fileMenu);

        bookingMenu.setText("Booking");

        bookingMenuItem.setText("Show...");
        bookingMenu.add(bookingMenuItem);

        jMenuBar1.add(bookingMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setQuitMenuItemListener(ActionListener listener) {
        this.quitMenuItem.addActionListener(listener);
    }

    public void setBookingMenuItemListener(ActionListener listener){
        this.bookingMenuItem.addActionListener(listener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu bookingMenu;
    private javax.swing.JMenuItem bookingMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem quitMenuItem;
    // End of variables declaration//GEN-END:variables
}
