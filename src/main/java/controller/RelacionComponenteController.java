package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Collection;

import model.Pos;
import util.Constantes;
import util.Utilitario;
import org.apache.log4j.Logger;

/**
 * @author Deyviz Perez
 * @version 1.0
 * @detail Controlador, de la segunda pagina, donde se puede verificar las rutas de los instaladores y actualizares
 * **/
public class RelacionComponenteController {

    Logger logger = Logger.getLogger(RelacionComponenteController.class);
    Constantes constante = new Constantes();

    @FXML
    private Button btnProcesoActualizacion;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnAtras;

    @FXML
    private TableView<Pos> tablaRelacion;

//    @FXML
//    TableColumn<Pos, String> descripcion = new  TableColumn<>("descripcion");
//
//    @FXML
//    TableColumn<Pos, String> estado = new TableColumn<>("estado");
//
//    @FXML
//    TableColumn<Pos, String> ubicacion = new TableColumn<>("ubicacion");
//
//    @FXML
//    TableColumn<Pos, String> ubicacionActualizador = new TableColumn<>("ubicacionActualizador");

    @FXML
    TableColumn<Pos, String> descripcion = new TableColumn<Pos, String>("descripcion");

    @FXML
    TableColumn<Pos, String> estado = new TableColumn<Pos, String>("estado");

    @FXML
    TableColumn<Pos, String> ubicacion = new TableColumn<Pos, String>("ubicacion");

    @FXML
    TableColumn<Pos, String> ubicacionActualizador = new TableColumn<Pos, String>("ubicacionActualizador");
    private Main application;

    Utilitario utilitario = new Utilitario();

    public void setApp(Main application) {
        this.application = application;

        descripcion.setCellValueFactory(new PropertyValueFactory<Pos, String>("descripcion"));
        estado.setCellValueFactory(new PropertyValueFactory<Pos, String>("estado"));
        ubicacion.setCellValueFactory(new PropertyValueFactory<Pos, String>("ubicacion"));
        ubicacionActualizador.setCellValueFactory(new PropertyValueFactory<Pos, String>("ubicacionActualizador"));
        tablaRelacion.getItems().setAll(insert());
    }
    private Collection<? extends Pos> insert(){

        /* Ubicacion del Actualizador POS*/
        String ubicacionActualizadorPrintManager=utilitario.conocerRutaActualizador(constante.SERVICIO_POS_PRINTMANAGER);
        String ubicacionActualizadorServidor=utilitario.conocerRutaActualizador(constante.SERVICIO_POS_SERVER);
        String ubicacionActualizadorBd=utilitario.conocerRutaActualizador(constante.SERVICIO_POS_BD);
        String ubicacionActualizadorCliente=utilitario.conocerRutaActualizador(constante.SERVICIO_POS_CLIENTE);
        logger.info("ubicacion Actualizador PrintManager: "+ubicacionActualizadorPrintManager);
        logger.info("ubicacion Actualizador Servidor POS: "+ubicacionActualizadorServidor);
        logger.info("ubicacion Actualizador Base de Datos: "+ubicacionActualizadorBd);
        logger.info("ubicacion Actualizador Cliente: "+ubicacionActualizadorCliente);

        /* Ubicacion del Instalador POS*/
        String ubicacionInstaladorPrintManager=utilitario.conocerRutaInstalador(constante.SERVICIO_POS_PRINTMANAGER);
        String ubicacionInstaladorServidor=utilitario.conocerRutaInstalador(constante.SERVICIO_POS_SERVER);
        String ubicacionInstaladorBd=utilitario.conocerRutaInstalador(constante.SERVICIO_POS_BD);
        String ubicacionInstaladorCliente=utilitario.conocerRutaInstalador(constante.SERVICIO_POS_CLIENTE);
        logger.info("ubicacion Instalador PrintManager: "+ubicacionInstaladorPrintManager);
        logger.info("ubicacion Instalador Servidor POS: "+ubicacionInstaladorServidor);
        logger.info("ubicacion Instalador Base de Datos: "+ubicacionInstaladorBd);
        logger.info("ubicacion Instalador Cliente: "+ubicacionInstaladorCliente);

        /* Status de los servicios window POS*/
        String estadoPosPrintManager=utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_PRINTMANAGER);
        String estadoPosServer=utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_SERVER);
        String estadoPosBd=utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_BD);
        String estadoPosCliente=utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_CLIENTE);
        logger.info("Estado Servicio PrintManager: "+estadoPosPrintManager);
        logger.info("Estado Servicio Servidor POS: "+estadoPosServer);
        logger.info("Estado Servicio Base de Datos: "+estadoPosBd);
        logger.info("Estado Servicio Cliente: "+estadoPosCliente);

       return  FXCollections.observableArrayList(
                new Pos("SrvWinFE_Update_Epos", "No Existe", "D://POS/ActualizadorEpos/","Actualizar el servicio POS"),
                new Pos(constante.SERVICIO_POS_SERVER, estadoPosServer, ubicacionInstaladorServidor,ubicacionActualizadorServidor),
                new Pos(constante.SERVICIO_POS_BD, estadoPosBd, ubicacionInstaladorBd,ubicacionActualizadorBd),
                new Pos(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, ubicacionInstaladorCliente,ubicacionActualizadorCliente));

    }

//    public void irProcesoActualizacion_button() {
//        this.application.procesoActualizacion();
//        this.Exit_button();
//    }
public void irProcesoActualizacion_button() {
    Stage stage = (Stage) btnProcesoActualizacion.getScene().getWindow();
    stage.close();
    this.application.procesoActualizacion();
}
    public void Exit_button() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    public void Atras_button() {
        Stage stage = (Stage) btnAtras.getScene().getWindow();
        stage.close();
        this.application.inicioAsistenteActualizacion();
    }
}
