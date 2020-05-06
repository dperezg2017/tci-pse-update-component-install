package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import util.Constantes;
import util.Utilitario;
import java.io.IOException;


public class InicioAsistenteActualizacionController {

    Logger logger = Logger.getLogger(InicioAsistenteActualizacionController.class);
    Constantes constante = new Constantes();
    Utilitario utilitario = new Utilitario();

    @FXML
    private Label lblmsjInicioAsistenteActualizacion = new Label();

    @FXML
    private Label lblversionInstalada = new Label();

    @FXML
    private Label lblversionInstalar = new Label();

    @FXML
    private Button btnSalir;

    private Main application;

    public void setApp(Main application) throws IOException {
        String versionInstalada = this.versionInstalada();
        String versionInstalar = this.versionInstalar();
        String msjInicioAsistenteActualizacion = msjInicioAsistenteActualizacion(versionInstalada,versionInstalar);
        this.application = application;
        lblversionInstalada.setText(versionInstalada);
        lblversionInstalar.setText(versionInstalar);
        lblmsjInicioAsistenteActualizacion.setText(msjInicioAsistenteActualizacion);
    }

    public void irRelacionComponente() throws IOException {

        String mensaje=msjInicioAsistenteActualizacion(this.versionInstalada(),this.versionInstalar());

        if(mensaje.equalsIgnoreCase(constante.MSJ_IAA_VERSION_MAYOR)){
            Alert alert = new Alert(Alert.AlertType.ERROR, mensaje, ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                logger.warn(constante.MSJ_IAA_VERSION_MAYOR);
                this.salir();
            }
        }else if(mensaje.equalsIgnoreCase(constante.MSJ_IAA_VERSION_IGUAL)){
            Alert alert = new Alert(Alert.AlertType.WARNING, mensaje, ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                logger.warn(constante.MSJ_IAA_VERSION_IGUAL);
                this.salir();
            }
        }else if(mensaje.equalsIgnoreCase(constante.MSJ_IAA_VERSION_ERROR)){
            Alert alert = new Alert(Alert.AlertType.WARNING, mensaje, ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                logger.warn(constante.MSJ_IAA_VERSION_ERROR);
                this.salir();
            }
        }else if(mensaje.equalsIgnoreCase(constante.MSJ_IAA_ACTUALIZACION)){
            this.salir();
            this.application.actualizacionRelacionComponente();
        }else if(mensaje.equalsIgnoreCase(constante.MSJ_IAA_INSTALACION)){
            this.salir();
            this.application.instalacionRelacionComponente();
        }
    }

    public void salir() {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    public String versionInstalada() throws IOException {
        if (utilitario.ejecutarComandoCMD(constante.CMD_VERIFICAR_SERVICIO_WINDOW_UPDATE_EPOS) != null) {
            String rutaServicioInstalado = utilitario.obtenerRutaServicioInstaladoBin();
            String rutaVersionInstalado = (rutaServicioInstalado!=null)?rutaServicioInstalado.substring(0, rutaServicioInstalado.indexOf("bin")) + "lib\\config":null;
            return (rutaVersionInstalado!=null)?utilitario.obtenerVersion(rutaVersionInstalado):constante.NO_EXISTE;
        } else {
            return constante.NO_EXISTE;
        }
    }

    public String versionInstalar() {
        String rutaVersionInstalar = utilitario.obtenerRutaServicioInstalarLibConfig();
        return (rutaVersionInstalar!=null)?utilitario.obtenerVersion(rutaVersionInstalar):constante.ERROR;
    }

    public String msjInicioAsistenteActualizacion(String versionInstalada, String versionInstalar ){
        if(versionInstalada==constante.ERROR || versionInstalar==constante.ERROR){
            return constante.MSJ_IAA_VERSION_ERROR;
        }
        if(versionInstalada.equalsIgnoreCase(constante.NO_EXISTE) &&
                !versionInstalar.equalsIgnoreCase(constante.ERROR )&& !versionInstalar.equalsIgnoreCase(constante.NO_EXISTE)){
            return constante.MSJ_IAA_INSTALACION;
        }
        return utilitario.validarVersion(versionInstalada,versionInstalar);
    }

}



