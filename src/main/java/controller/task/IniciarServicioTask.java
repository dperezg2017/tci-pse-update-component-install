package controller.task;
import org.apache.log4j.Logger;
import util.Constantes;
import util.Utilitario;

import java.io.IOException;

public class IniciarServicioTask {

    Logger logger = Logger.getLogger(IniciarServicioTask.class);
    Utilitario utilitario = new Utilitario();
    Constantes constante = new Constantes();

    public int iniciarServicio()  {

        try {
            utilitario.ejecutarComandoCMD(constante.CMD_NET_START_POS_UPDATE_EPOS);

            String estado = utilitario.obtenerEstadoServicio();
            if (estado.contains(constante.SERVICIO_POS_DETENIDO)) {
                return 0;
            }else if(estado.contains(constante.SERVICIO_POS_INICIADO)){
                return 1;
            }else{
                return -1;
            }
        } catch (Exception e) {
            logger.error("Ocurrio un error al detener el servicio:",e);
            return -2;
        }
    }



}
