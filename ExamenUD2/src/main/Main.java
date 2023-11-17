package main;

import controller.FrontController;
import model.BookingModel;
import model.PersonModel;
import view.FrontViewJFrame;

public class Main {
    
    public static void main(String[] args) {
        FrontViewJFrame fvf = new FrontViewJFrame();
        PersonModel model = new PersonModel();
        FrontController fc = new FrontController(fvf, model);
        fvf.setVisible(true);
    }
}