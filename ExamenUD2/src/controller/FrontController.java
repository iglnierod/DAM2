package controller;

import controller.booking.BookingDataController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.BookingModel;
import model.PersonModel;
import view.FrontViewJFrame;
import view.booking.BookingDataJDialog;

public class FrontController {
    
    private FrontViewJFrame view;
    private PersonModel model;
    
     public FrontController(FrontViewJFrame view, PersonModel model) {
        this.view = view;
        this.model = model;
        this.view.setQuitMenuItemListener(this.setQuitMenuItemActionListener());
        this.view.setBookingMenuItemListener(this.setBookingMenuItemActionListener());
        
    }

    private ActionListener setQuitMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                System.exit(0);
            }
        };
        return al;
    }
    
    private ActionListener setBookingMenuItemActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookingDataJDialog mdd = new BookingDataJDialog(view, true);
                BookingDataController mdc = new BookingDataController(mdd, model);
                mdd.setVisible(true);
            }
        };
        return al;
    }
}
