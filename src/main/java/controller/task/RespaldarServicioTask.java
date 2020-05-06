package controller.task;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import util.Constantes;
import java.util.Date;
import util.Utilitario;

public class RespaldarServicioTask {

    Logger logger = Logger.getLogger(RespaldarServicioTask.class);
    Utilitario utilitario = new Utilitario();
    Constantes constante = new Constantes();

    String rutaServicioInstalado=utilitario.obtenerRutaServicioInstaladoRaiz();
    String rutaBackupInstalado=utilitario.obtenerRutaBackupInstalador(rutaServicioInstalado);
    String rutaInstaladorServicioCONF = rutaServicioInstalado.concat("\\conf");
    String rutaInstaladorServicioLIB = rutaServicioInstalado.concat("\\lib");
    String rutaInstaladorServicioBackupCONF = rutaBackupInstalado.concat("\\conf");
    String rutaInstaladorServicioBackupLIB = rutaBackupInstalado.concat("\\lib");
    
    public RespaldarServicioTask() throws IOException {
    }
    public Boolean respaldarServicio() {
        try {
            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY  + "\""+ rutaInstaladorServicioCONF  + "\""+" " +"\""+ rutaInstaladorServicioBackupCONF  + "\""+ constante.CMD_MIR);
            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY  + "\""+ rutaInstaladorServicioLIB  + "\""+" " +"\""+ rutaInstaladorServicioBackupLIB + "\""+ constante.CMD_MIR);
            return true;
        } catch (Exception e) {
            logger.error("Ocurrio un erro al respaldar el servicio");
            return false;
        }
    }

}
