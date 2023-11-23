package clock;

import java.util.EventObject;

/**
 * Representa un evento de alarma.
 * Hereda de EventObject y se utiliza para notificar a los listeners cuando
 * se activa una alarma.
 *
 * @author iglesias_nieto_rodrigo
 */
public class AlarmEvent extends EventObject {

    /**
     * Crea una nueva instancia de AlarmEvent
     * @param source El objeto que generó el evento de alarma.
     */
    public AlarmEvent(Object source) {
        super(source);
    }

}
