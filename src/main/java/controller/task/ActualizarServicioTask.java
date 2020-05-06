package controller.task;
import org.apache.log4j.Logger;
import util.Constantes;
import util.Utilitario;

import java.io.IOException;

public class ActualizarServicioTask {

    Logger logger = Logger.getLogger(ActualizarServicioTask.class);
    Utilitario utilitario = new Utilitario();
    Constantes constante = new Constantes();

    String rutaServicioInstalar=utilitario.obtenerRutaServicioInstalar();
    String rutaServicioInstalado=utilitario.obtenerRutaServicioInstaladoRaiz();

    public ActualizarServicioTask() throws IOException {
    }
    public Boolean actualizarServicio() {
        try {
            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY + "\"" +rutaServicioInstalar + "\""+" " +"\""+ rutaServicioInstalado  + "\""+ constante.CMD_MIR);
            return true;
        } catch (Exception e) {
            logger.error("Ocurrio un error al actualizar el servicio: ",e);
            return false;
        }
    }

}
