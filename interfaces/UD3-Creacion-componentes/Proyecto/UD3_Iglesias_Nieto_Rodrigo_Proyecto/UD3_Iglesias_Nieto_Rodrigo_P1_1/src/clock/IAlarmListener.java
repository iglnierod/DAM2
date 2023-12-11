/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package clock;

import java.util.EventListener;

/**
 * Representa un listener para capturar eventos.
 * Las clases que implmentan esta interfaz pueden responder a las alarmas
 * por el componente ClockBean.
 * 
 * @author iglesias_nieto_rodrigo
 */
public interface IAlarmListener extends EventListener {

    /**
     * Método llamado cuando se captura un evento de alarma
     *
     * @param ev El AlarmEvent que representa la alarma capturada
     */
    public void captureAlarm(AlarmEvent ev);
}
