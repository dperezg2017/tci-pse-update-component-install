package controller.task;
import util.Constantes;
import util.Utilitario;

import java.io.IOException;

/**
 * @author: Deyviz Perez
 * @version: 1.0
 * **/
public class IniciarServicioTask {

    Utilitario utilitario = new Utilitario();
    Constantes constante = new Constantes();



    public Boolean iniciarPrintmanager() throws IOException {
        utilitario.ejecutarComandoCMD(constante.CMD_NET_START_PRINTMANAGER);
        if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_PRINTMANAGER).equalsIgnoreCase(constante.SERVICIO_POS_INICIADO)) {
            return true;
        }
        return false;
    }

    public Boolean iniciarBD() throws IOException {
        utilitario.ejecutarComandoCMD(constante.CMD_NET_START_BD);
        if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_BD).equalsIgnoreCase(constante.SERVICIO_POS_INICIADO)) {
            return true;
        }
        return false;
    }
    public Boolean iniciarServidorPos() throws IOException {
        utilitario.ejecutarComandoCMD(constante.CMD_NET_START_POS_SERVER);
        if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_SERVER).equalsIgnoreCase(constante.SERVICIO_POS_INICIADO)) {
            return true;
        }
        return false;
    }

    public Boolean iniciarCliente() throws IOException {
        utilitario.ejecutarComandoCMD(constante.CMD_NET_START_CLIENTE);
        if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_CLIENTE).equalsIgnoreCase(constante.SERVICIO_POS_INICIADO)) {
            return true;
        }
        return false;
    }


}
