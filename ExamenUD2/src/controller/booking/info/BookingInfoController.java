package controller.booking.info;

import model.BookingModel;
import model.PersonModel;
import view.booking.info.BookingInfoJDialog;

public class BookingInfoController {

    private BookingInfoJDialog view;
    private PersonModel model;
    
    public BookingInfoController(BookingInfoJDialog bid, PersonModel model) {
        this.view = bid;
        this.model = model;
        this.view.setBookingHolderTextFieldText(this.getBookingHolder());
        this.view.setSmokerCheckBoxSelected(this.getSmoker());
        //disableView();
    }
    
    private void disableView() {
        this.view.setBookingHolderTextFieldEnabled(false);
        this.view.setRoomTypeComboBoxEnabled(false);
        this.view.setSmokerCheckBoxEnabled(false);
        this.view.setWifiCheckBoxEnabled(false);
    }
    
    private String getBookingHolder(){
        return this.model.getName();
    }
    
    private boolean getSmoker(){
        return this.model.getRoom().isIsSmoker();
    }
    
  
}
