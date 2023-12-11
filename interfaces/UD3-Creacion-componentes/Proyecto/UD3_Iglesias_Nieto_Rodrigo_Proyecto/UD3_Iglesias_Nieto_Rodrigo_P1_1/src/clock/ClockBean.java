/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * Representa un reloj digital que hereda de JLabel.
 * Proporciona funcionalidades como poner la hora, establecer alarmas,
 * notificar cuando se activan las alarmas.
 * El reloj se puede configurar para modo 24H o AM/PM.
 * @author iglesias_nieto_rodrigo
 */
public class ClockBean extends JLabel implements ActionListener, Serializable {

    /**
     * Establece el modo del reloj: true = 24H, false = 12H
     */
    public boolean mode24;

    /**
     * Estado de la alarma
     */
    public boolean enableAlarm;

    /**
     * Minutos de la alarma
     */
    public int minutesAlarm;

    /**
     * Hora de la alarma
     */
    public int hourAlarm;
    
    private final Timer t;
    private Calendar calendar;
    private IAlarmListener receptor;
    private String message;
    private final String[] hours = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
    private final String[] minutes = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
    private final String[] seconds = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
    private final String[] AMPM = {"AM", "PM"};

    /**
     * Método que devuelve el texto del mensaje de alerta
     * @return Mensaje de la alarma
     */
    public String getMessage() {
        return message;
    }

    /**
     * Método que establece el mensaje de la alerta
     * @param message Mensaje de la alerta
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Método que devuelve el estado de la alarma
     * @return true si la alarma está activa
     */
    public boolean isEnableAlarm() {
        return enableAlarm;
    }

    /**
     * Método que establece el estado de la alarma
     * @param enableAlarm Estado de la alarma
     */
    public void setEnableAlarm(boolean enableAlarm) {
        this.enableAlarm = enableAlarm;
    }

    /**
     * Método que devuelve los minutos de la alarma
     * @return número entero con los minutos de la alarma
     */
    public int getMinutesAlarm() {
        return minutesAlarm;
    }

    /**
     * Método que establece los minutos de la alarma
     * @param minutesAlarm Minutos de la alarma
     */
    public void setMinutesAlarm(int minutesAlarm) {
        this.minutesAlarm = minutesAlarm;
    }

    /**
     * Método que devuelve la hora de la alarma
     * @return Hora de la alarma
     */
    public int getHourAlarm() {
        return hourAlarm;
    }

    /**
     * Método que establece la hora de la alarma
     * @param hourAlarm Hora de la alarma
     */
    public void setHourAlarm(int hourAlarm) {
        this.hourAlarm = hourAlarm;
    }

    /**
     * Método que devuelve el modo del reloj
     * @return true si el modo del reloj es 24H
     */
    public boolean isMode24() {
        return mode24;
    }

    /**
     * Método que establece el modo del reloj
     * @param mode24 true para modo 24H, false para 12H
     */
    public void setMode24(boolean mode24) {
        this.mode24 = mode24;
    }

    /**
     * Crea una instancia de ClockBean con ajustes por defecto.
     * El reloj está en modo 24H y la alarma está desactivada.
     * Se inicia un Timer que actualiza el reloj cada segundo.
     */
    public ClockBean() {
        this.setHorizontalAlignment(SwingConstants.RIGHT);
        message = "";
        mode24 = true;
        enableAlarm = false;
        t = new Timer(1000, this);
        t.start();
        calendar = Calendar.getInstance();

        String h = hours[calendar.get(Calendar.HOUR_OF_DAY)];
        String m = minutes[calendar.get(Calendar.MINUTE)];
        String s = seconds[calendar.get(Calendar.SECOND)];
        String AM_PM = AMPM[calendar.get(Calendar.AM_PM)];
        setText(h + ":" + m + ":" + s + " " + AM_PM);
    }

    /**
     * Actualiza el reloj y comprueba la alarma cuando el Timer lanza un ActionEvent
     * @param e El ActionEvent que activa el método
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String h;
        String m;
        String s;
        String AM_PM;
        calendar = Calendar.getInstance();

        if (isMode24()) {
            h = hours[calendar.get(Calendar.HOUR_OF_DAY)];
            m = minutes[calendar.get(Calendar.MINUTE)];
            s = seconds[calendar.get(Calendar.SECOND)];
            setText(h + ":" + m + ":" + s);
        } else {
            h = hours[calendar.get(Calendar.HOUR)];
            m = minutes[calendar.get(Calendar.MINUTE)];
            s = seconds[calendar.get(Calendar.SECOND)];
            AM_PM = AMPM[calendar.get(Calendar.AM_PM)];
            setText(h + ":" + m + ":" + s + " " + AM_PM);
        }
        repaint();

        if (enableAlarm) {
            if ((Integer.parseInt(h) == hourAlarm) && (Integer.parseInt(m) == minutesAlarm)) {
                receptor.captureAlarm(new AlarmEvent(this));
            }
        }
    }

    /**
     * Añade un IAlarmListener para capturar los eventos de la alarma
     * @param receptor El IAmListener que se añade
     */
    public void addAlarmListener(IAlarmListener receptor) {
        this.receptor = receptor;
    }

    /**
     * Borra un IAlarmListener para dejar de capturar eventos de la alarma
     * @param receptor El IAmListener que se borra
     */
    public void removeAlarmListener(IAlarmListener receptor) {
        this.receptor = null;
    }
}
