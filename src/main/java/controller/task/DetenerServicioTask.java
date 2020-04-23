package controller.task;

import util.Constantes;
import util.Utilitario;
import org.apache.log4j.Logger;

/**
 * @author: Deyviz Perez
 * @version: 1.0
 * **/
public class DetenerServicioTask {

    Utilitario utilitario = new Utilitario();
    Constantes constante = new Constantes();
    Logger logger = Logger.getLogger(DetenerServicioTask.class);

    /* estado:
     *  0: iniciado
     * -1: detenenido
     * -2: noexiste
     * -3: error */

    public int detenerPrintmanager() {
        try {
            utilitario.ejecutarComandoCMD(constante.CMD_NET_STOP_PRINTMANAGER);
            if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_PRINTMANAGER).equalsIgnoreCase(constante.SERVICIO_POS_INICIADO)) {
                return 0;
            }
            if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_PRINTMANAGER).equalsIgnoreCase(constante.SERVICIO_POS_DETENIDO)) {
                return -1;
            }
            if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_PRINTMANAGER).equalsIgnoreCase(constante.SERVICIO_POS_NO_EXISTE)) {
                return -2;
            }
            return -3;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -3;
        }
    }

    public int detenerBD() {
        try {
            utilitario.ejecutarComandoCMD(constante.CMD_NET_STOP_BD);
            if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_BD).equalsIgnoreCase(constante.SERVICIO_POS_INICIADO)) {
                return 0;
            }
            if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_BD).equalsIgnoreCase(constante.SERVICIO_POS_DETENIDO)) {
                return -1;
            }
            if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_BD).equalsIgnoreCase(constante.SERVICIO_POS_NO_EXISTE)) {
                return -2;
            }
            return -3;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -3;
        }
    }

    public int detenerServidorPos() {
        try {
            utilitario.ejecutarComandoCMD(constante.CMD_NET_STOP_POS_SERVER);
//            logger.info("[constante.CMD_NET_STOP_POS_SERVER]: "+constante.CMD_NET_STOP_POS_SERVER);
//            logger.info("[utilitario.ejecutarComandoCMD(constante.CMD_NET_STOP_POS_SERVER)]: "+utilitario.ejecutarComandoCMD(constante.CMD_NET_STOP_POS_SERVER));
            if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_SERVER).equalsIgnoreCase(constante.SERVICIO_POS_INICIADO)) {
                // LOCAL
                return 0;
            }
            if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_SERVER).equalsIgnoreCase(constante.SERVICIO_POS_DETENIDO)) {
               //IDE
                return -1;
            }
            if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_SERVER).equalsIgnoreCase(constante.SERVICIO_POS_NO_EXISTE)) {
                return -2;
            }
            return -3;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -3;
        }
    }

    public int detenerCliente() {
        try {
            utilitario.ejecutarComandoCMD(constante.CMD_NET_STOP_CLIENTE);
            if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_CLIENTE).equalsIgnoreCase(constante.SERVICIO_POS_INICIADO)) {
                return 0;
            }
            if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_CLIENTE).equalsIgnoreCase(constante.SERVICIO_POS_DETENIDO)) {
                return -1;
            }
            if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_CLIENTE).equalsIgnoreCase(constante.SERVICIO_POS_NO_EXISTE)) {
                return -2;
            }
            return -3;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -3;
        }
    }


}
