package controller.booking;

import controller.booking.info.BookingInfoController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import model.BookingModel;
import model.PersonModel;
import model.RoomModel;
import view.booking.BookingDataJDialog;
import view.booking.info.BookingInfoJDialog;

public class BookingDataController {

    private final BookingDataJDialog view;
    private final PersonModel model;

    public BookingDataController(BookingDataJDialog view, PersonModel model) {
        this.view = view;
        this.model = model;
        this.view.añadirPersona();
        this.view.setShowDetailsButtonListener(setShowDetailsButtonActionListener());
    }

    private ActionListener setShowDetailsButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookingInfoJDialog bid = new BookingInfoJDialog(view, true);
                PersonModel model = view.getBookingHolder();
                BookingInfoController bic = new BookingInfoController(bid, model);
                bid.setVisible(true);
            }
        };
        return al;
    }
}
