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

import java.util.regex.Pattern;


/**
 * @author: Deyviz Perez
 * @version: 1.0
 * @detail: Controlador, de la primera pagina, donde se puede verificar las versiones instaladar y a actualizar
 **/
public class InicioAsistenteActualizacionController {

    Logger logger = Logger.getLogger(InicioAsistenteActualizacionController.class);
    Constantes constante = new Constantes();
    Utilitario utilitario = new Utilitario();

    @FXML
    private Button btnIrRelacionComponente;

    @FXML
    private Label lblversionInstalador = new Label();

    @FXML
    private Label lblversionActualizador = new Label();

    @FXML
    private Button btnExit;

    private Main application;

    public void setApp(Main application) {
        // String version =(this.versionPOS()==null)?this.versionPOS():"";
        String version = this.versionPOS();
        String versionActualizador = this.versionActualizadorPOS();
        this.application = application;
        lblversionInstalador.setText(version);
        lblversionActualizador.setText(versionActualizador);
    }

    public void irRelacionComponente_button(ActionEvent event) throws InterruptedException {

        String versionInstalador = this.versionPOS();
        String versionActualizador = this.versionActualizadorPOS();
        Boolean validarVersiones = false;
        String[] partsInstalador = versionInstalador.split(Pattern.quote("."));
        int instalador1 = Integer.parseInt(partsInstalador[0]);
        int instalador2 = Integer.parseInt(partsInstalador[1]);
        int instalador3 = Integer.parseInt(partsInstalador[2]);
        int instalador4 = Integer.parseInt(partsInstalador[3]);
        String[] partsActualizador = versionActualizador.split(Pattern.quote("."));
        int actualizador1 = Integer.parseInt(partsActualizador[0]);
        int actualizador2 = Integer.parseInt(partsActualizador[1]);
        int actualizador3 = Integer.parseInt(partsActualizador[2]);
        int actualizador4 = Integer.parseInt(partsActualizador[3]);

        if (instalador1 > actualizador1) {
            validarVersiones = true;
            Alert alert = new Alert(Alert.AlertType.ERROR, "La versión a actualizar es menor o igual a la que ya se encuentra instalada. Por favor verifique", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                //do stuff
            }
        }
        if (instalador1 == actualizador1) {
            if (instalador2 > actualizador2) {
                validarVersiones = true;
                Alert alert = new Alert(Alert.AlertType.ERROR, "La versión a actualizar es menor o igual a la que ya se encuentra instalada. Por favor verifique", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    //do stuff
                }
            }
            if (instalador2 == actualizador2) {
                if (instalador3 > actualizador3) {
                    validarVersiones = true;
                    Alert alert = new Alert(Alert.AlertType.ERROR, "La versión a actualizar es menor o igual a la que ya se encuentra instalada. Por favor verifique", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        //do stuff
                    }
                }
                if (instalador3 == actualizador3) {
                    if (instalador4 > actualizador4) {
                        validarVersiones = true;
                        Alert alert = new Alert(Alert.AlertType.ERROR, "La versión a actualizar es menor o igual a la que ya se encuentra instalada. Por favor verifique", ButtonType.OK);
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES) {
                            //do stuff
                        }
                    }
                    if (instalador4 == actualizador4) {
                        validarVersiones = true;
                        Alert alert = new Alert(Alert.AlertType.ERROR, "La versión a actualizar es menor o igual a la que ya se encuentra instalada. Por favor verifique", ButtonType.OK);
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES) {
                            //do stuff
                        }
                    }
                }
            }
        }
        if (!validarVersiones) {
            if (versionInstalador != null && versionActualizador != null) {
                logger.info("Versión del Instalador: " + this.versionPOS());
                logger.info("Versión del Actualizador: " + this.versionActualizadorPOS());
                this.application.relacionComponente();
                this.Exit_button();
            } else {
                logger.info("No existe el archivo donde se encuentra la versión del Instalador ó el Actualizador ");
                Alert alert = new Alert(Alert.AlertType.WARNING, "No se encuentra la versión del Instalador ó el Actualizador", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    //do stuff
                }
            }
        }
    }

    public void Exit_button() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public String versionPOS() {
        if (utilitario.ejecutarComandoCMD(constante.CMD_VERIFICAR_SERVICIO_WINDOW_POS) != null) {
            String rutaServicioPOS = utilitario.extraerRutaServicioPOSexe();

            rutaServicioPOS = rutaServicioPOS.substring(0, rutaServicioPOS.indexOf("bin")) + "lib\\config";

            return utilitario.versionPOS(rutaServicioPOS);
        } else {
            return constante.NO_EXISTE;
        }
    }

    public String versionActualizadorPOS() {
        String ubicacionActualizadorServidor = utilitario.conocerRutaVersionActualizador(constante.SERVICIO_POS_SERVER);
        String versionActualizador = utilitario.versionPOS(ubicacionActualizadorServidor);
        return versionActualizador;
    }

}



