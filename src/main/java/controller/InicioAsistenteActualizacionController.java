package controller;


import javafx.event.ActionEvent;
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
import java.util.regex.Pattern;

public class InicioAsistenteActualizacionController {

    Logger logger = Logger.getLogger(InicioAsistenteActualizacionController.class);
    Constantes constante = new Constantes();
    Utilitario utilitario = new Utilitario();

    @FXML
    private Button btnIrRelacionComponente;

    @FXML
    private Label lblmsjInicioAsistenteActualizacion = new Label();

    @FXML
    private Label lblversionInstalada = new Label();

    @FXML
    private Label lblversionInstalar = new Label();

    @FXML
    private Button btnExit;

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

    public void irRelacionComponente_button(ActionEvent event) throws IOException {

        String mensaje=msjInicioAsistenteActualizacion(this.versionInstalada(),this.versionInstalar());

        if(mensaje.equalsIgnoreCase(constante.MSJ_IAA_VERSION_MAYOR_IGUAL)){
            Alert alert = new Alert(Alert.AlertType.ERROR, mensaje, ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                logger.info("LE DISTE ACEPTAR");
            }
        }else if(mensaje.equalsIgnoreCase(constante.MSJ_IAA_VERSION_ERROR)){
            Alert alert = new Alert(Alert.AlertType.WARNING, mensaje, ButtonType.OK);
            alert.showAndWait();
        }

    }

    public void Exit_button() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public String versionInstalada() throws IOException {
        if (utilitario.ejecutarComandoCMD(constante.CMD_VERIFICAR_SERVICIO_WINDOW_UPDATE_EPOS) != null) {
            String rutaServicioInstalado = utilitario.obtenerRutaServicioInstalado();
            String rutaVersionInstalado = (rutaServicioInstalado!=null)?rutaServicioInstalado.substring(0, rutaServicioInstalado.indexOf("bin")) + "lib\\config":null;
            return (rutaVersionInstalado!=null)?utilitario.obtenerVersion(rutaVersionInstalado):constante.NO_EXISTE;
        } else {
            logger.error("Ocurrio un error al obtener la version instalada: no existe el servicio SrvWinFE_POS_Update_Epos");
            return constante.NO_EXISTE;
        }
    }

    public String versionInstalar() {
        String rutaVersionInstalar = utilitario.obtenerRutaServicioInstalar();
        return (rutaVersionInstalar!=null)?utilitario.obtenerVersion(rutaVersionInstalar):constante.ERROR;
    }

    public String msjInicioAsistenteActualizacion(String versionInstalada, String versionInstalar ){
        if(versionInstalada==constante.NO_EXISTE && versionInstalar!=constante.ERROR && versionInstalar!=constante.NO_EXISTE){
            return constante.MSJ_IAA_INSTALACION;
        }
        return utilitario.validarVersion(versionInstalada,versionInstalar);
    }

}



