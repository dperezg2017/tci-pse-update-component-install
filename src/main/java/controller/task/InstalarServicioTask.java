package controller.task;
import org.apache.log4j.Logger;
import util.Constantes;
import util.Utilitario;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class InstalarServicioTask {

    Utilitario utilitario = new Utilitario();

    Logger logger = Logger.getLogger(InstalarServicioTask.class);
    Constantes constante = new Constantes();

    public boolean instalarServicioUpdateEpos() throws IOException {

        Process process = null;
        InputStream inputStream = null;
        try {
            String rutaServicioBatInstalar = utilitario.obtenerRutaServicioInstalarBinBat();
            Runtime runtime = Runtime.getRuntime();
            logger.info("Se va a ejecutar el comando CMD :"+constante.RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_UPDATE_EPOS_BIN_BAT_FILE_INSTALL);
            process = runtime.exec(constante.RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_UPDATE_EPOS_BIN_BAT_FILE_INSTALL,null,new File(rutaServicioBatInstalar));
            inputStream = process.getInputStream();
            StringBuilder respuesta = new StringBuilder();
            int i = 0;
            while( (i = inputStream.read() ) != -1) {
                respuesta.append(i);
            }
            if(!respuesta.toString().isEmpty()){
                logger.info("Respuesta del comando CMD : "+respuesta.toString());
                inputStream.close();
                process.destroy();
                return true;
            }else {
                logger.warn("Respuesta del comando CMD : "+respuesta.toString());
                inputStream.close();
                process.destroy();
                return false;
            }
        } catch (Exception e) {
            logger.error("Ocurrio un error al ejecutar el instalar el servicio Update-Epos: Error al iniciar el archivo BAT");
            inputStream.close();
            process.destroy();
            return false;
        }
    }
}
