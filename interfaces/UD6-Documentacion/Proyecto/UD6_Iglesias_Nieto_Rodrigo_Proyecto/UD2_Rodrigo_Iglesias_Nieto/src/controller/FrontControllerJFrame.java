/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import clock.AlarmEvent;
import clock.IAlarmListener;
import controller.manageAldData.computers.ManageAldComputersController;
import controller.manageAldData.ManageAldDataController;
import controller.reports.GenerateReportController;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import model.aldComputerService.AldComputerService;
import view.MainJFrame;
import view.aldComputerService.ManageAldComputersDialog;
import view.aldComputerService.ManageAldDataDialog;
import view.notifications.NotificationsDialog;
import view.reports.GenerateReportDialog;
import javax.help.*;

/**
 *
 * @author dides
 */
public class FrontControllerJFrame {

    private MainJFrame view;
    private AldComputerService model;
    private NotificationsDialog nd;

    public FrontControllerJFrame(MainJFrame view, AldComputerService model) {
        this.view = view;
        this.model = model;
        this.view.setQuitMenuItemListener(this.setQuitMenuItemActionListener());
        this.view.setManageDataMenuItemListener(setManageDataMenuItemActionListener());
        this.view.setManageComputersMenuItemListener(this.setManageComputersMenuItemActionListener());
        //this.view.setNotificationsMenuItemListener(this.setNotificationsMenuItemActionListener());
        //this.view.setClockBeanIAlarmListener(this.setClockBeanAlarmListener());
        this.view.setGenerateReportMenuItemListener(this.setGenerateReportMenuItemActionListener());

        // HELP
        this.setHelpListener();
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

    private ActionListener setManageDataMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageAldDataDialog mad = new ManageAldDataDialog(view, true);
                ManageAldDataController madc = new ManageAldDataController(mad, model);
                mad.setVisible(true);
            }
        };
        return al;
    }

    private ActionListener setManageComputersMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageAldComputersDialog macd = new ManageAldComputersDialog(view, true);
                ManageAldComputersController macc = new ManageAldComputersController(macd, model);
                macd.setVisible(true);
            }
        };
        return al;
    }

    /*private ActionListener setNotificationsMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nd = new NotificationsDialog(view, true);
                nd.setClockButtonListener(setSetClockButtonActionListener());
                nd.setDisableAlarmListener(setDisableAlarmButtonActionListener());
                nd.setMode24(view.isClockMode24());

                if (view.isAlarmEnabled()) {
                    nd.setAlertHourSpinner(view.getAlertHour());
                    nd.setAlertMinuteSpinner(view.getAlertMinutes());
                    nd.setMessage(view.getAlarmMessage());
                } else {
                    LocalTime currentTime = LocalTime.now();
                    int hour = currentTime.getHour();
                    int minutes = currentTime.getMinute();
                    nd.setAlertHourSpinner(hour);
                    nd.setAlertMinuteSpinner(minutes);
                }

                nd.setVisible(true);
            }
        };
        return al;
    }*/

    private ActionListener setGenerateReportMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerateReportDialog grd = new GenerateReportDialog(view, false);
                GenerateReportController grc = new GenerateReportController(grd);
                grd.setVisible(true);
            }
        };
        return al;
    }

    private void setHelpListener() {
        File helpFile = new File("help" + File.separator + "help_set.hs");
        try {
            URL hsURL = helpFile.toURI().toURL();
            HelpSet helpSet = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpSet.createHelpBroker();
            hb.enableHelpKey(view.getConentPane(), "app", helpSet);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // ==== CLOCK CONTROLLER ====
    /*private IAlarmListener setClockBeanAlarmListener() {
        IAlarmListener listener = new IAlarmListener() {
            @Override
            public void captureAlarm(AlarmEvent ev) {
                view.setAlertEnable(false);
                view.removeClockBeanAlarmListener(this);
                JOptionPane.showMessageDialog(view, view.getAlarmMessage(), "ALARM!", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        return listener;
    }

    private ActionListener setSetClockButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Set Clock button");
                // TODO: set clock config
                System.out.println("Mode:" + nd.isMode24());
                System.out.println("Hour: " + nd.getHourAlertTime());
                System.out.println("Minute: " + nd.getMinuteAlertTime());
                System.out.println("Message: " + nd.getMessage());

                view.setClockMode(nd.isMode24());
                view.setAlertHour(nd.getHourAlertTime());
                view.setAlertMinutes(nd.getMinuteAlertTime());
                view.setAlertMessage(nd.getMessage());
                view.setAlertEnable(true);
            }
        };
        return al;
    }

    private ActionListener setDisableAlarmButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Disable Alarm button");
                if (view.isAlarmEnabled()) {
                    view.setAlertEnable(false);
                    JOptionPane.showMessageDialog(view, "The alarm has been disabled", "Alarm information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        return al;
    }*/
}
