package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;

import controller.task.ActualizarServicioTask;
import controller.task.DetenerServicioTask;
import controller.task.IniciarServicioTask;
import controller.task.RespaldarServicioTask;
import model.PosActualizacion;
import util.Constantes;
import util.Utilitario;
import org.apache.log4j.Logger;

/**
 * @author: Deyviz Perez
 * @version: 1.0
 * @detail:  Controlador, de la tercera pagina, donde se realizar (detener servicio, backup, actualizar, e iniciar servicio)
 * **/
public class ProcesoActualizacionController implements PropertyChangeListener {


    Logger logger = Logger.getLogger(ProcesoActualizacionController.class);
    Constantes constante = new Constantes();

    @FXML
    public Button btnSiguiente;

//    @FXML
//    TableColumn<PosActualizacion, String> nombre = new TableColumn<>("nombre");
//    @FXML
//    TableColumn<PosActualizacion, String> estado = new TableColumn<>("estado");
//    @FXML
//    TableColumn<PosActualizacion, String> estadoProceso = new TableColumn<>("estadoProceso");
//    @FXML
//    TableColumn<PosActualizacion, ImageView> imagenDetener = new TableColumn<PosActualizacion, ImageView>("detenerImagen");
//    @FXML
//    TableColumn<PosActualizacion, ImageView> imagenRespaldar = new TableColumn<PosActualizacion, ImageView>("imagenRespaldar");
//    @FXML
//    TableColumn<PosActualizacion, ImageView> imagenActualizar = new TableColumn<PosActualizacion, ImageView>("imagenActualizar");
//    @FXML
//    TableColumn<PosActualizacion, ImageView> imagenIniciar = new TableColumn<PosActualizacion, ImageView>("imagenIniciar");
//    @FXML
//    TableColumn<PosActualizacion, String> ubicacionActualizador = new TableColumn<>("ubicacionActualizador");

    @FXML
    TableColumn<PosActualizacion, String> nombre = new TableColumn<PosActualizacion, String>("nombre");
    @FXML
    TableColumn<PosActualizacion, String> estado = new TableColumn<PosActualizacion, String>("estado");
    @FXML
    TableColumn<PosActualizacion, String> estadoProceso = new TableColumn<PosActualizacion, String>("estadoProceso");
    @FXML
    TableColumn<PosActualizacion, ImageView> imagenDetener = new TableColumn<PosActualizacion, ImageView>("detenerImagen");
    @FXML
    TableColumn<PosActualizacion, ImageView> imagenRespaldar = new TableColumn<PosActualizacion, ImageView>("imagenRespaldar");
    @FXML
    TableColumn<PosActualizacion, ImageView> imagenActualizar = new TableColumn<PosActualizacion, ImageView>("imagenActualizar");
    @FXML
    TableColumn<PosActualizacion, ImageView> imagenIniciar = new TableColumn<PosActualizacion, ImageView>("imagenIniciar");
    @FXML
    TableColumn<PosActualizacion, String> ubicacionActualizador = new TableColumn<PosActualizacion, String>("ubicacionActualizador");

    Utilitario utilitario = new Utilitario();
    /* Ubicacion del Actualizador POS*/
    String ubicacionActualizadorServidor = utilitario.conocerRutaActualizador(constante.SERVICIO_POS_SERVER);
    String ubicacionActualizadorBd = utilitario.conocerRutaActualizador(constante.SERVICIO_POS_BD);
    String ubicacionActualizadorCliente = utilitario.conocerRutaActualizador(constante.SERVICIO_POS_CLIENTE);
    String ubicacionActualizadorPrintManager = utilitario.conocerRutaActualizador(constante.SERVICIO_POS_PRINTMANAGER);
    /* Status de los servicios window POS*/
    String estadoPosServer = utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_SERVER);
    String estadoPosBd = utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_BD);
    String estadoPosCliente = utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_CLIENTE);
    String estadoPosPrintManager = utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_PRINTMANAGER);
    /* Ubicacion del Instalador POS*/
    String ubicacionInstaladorPrintManager=utilitario.conocerRutaInstalador(constante.SERVICIO_POS_PRINTMANAGER);
    String ubicacionInstaladorServidor=utilitario.conocerRutaInstalador(constante.SERVICIO_POS_SERVER);
    String ubicacionInstaladorBd=utilitario.conocerRutaInstalador(constante.SERVICIO_POS_BD);
    String ubicacionInstaladorCliente=utilitario.conocerRutaInstalador(constante.SERVICIO_POS_CLIENTE);

    @FXML
    private Button btnAtras;
    @FXML
    private Button btnActualizar;
    @FXML
    private TableView<PosActualizacion> tablaActualizacion;
    private Main application;

    public void setApp(Main application) {
        this.application = application;
//        this.btnSiguiente.setDisable();
        nombre.setCellValueFactory(new PropertyValueFactory<PosActualizacion, String>("nombre"));
        estado.setCellValueFactory(new PropertyValueFactory<PosActualizacion, String>("estado"));
        estadoProceso.setCellValueFactory(new PropertyValueFactory<PosActualizacion, String>("estadoProceso"));
        imagenDetener.setCellValueFactory(new PropertyValueFactory<PosActualizacion, ImageView>("imagenDetener"));
        imagenRespaldar.setCellValueFactory(new PropertyValueFactory<PosActualizacion, ImageView>("imagenRespaldar"));
        imagenActualizar.setCellValueFactory(new PropertyValueFactory<PosActualizacion, ImageView>("imagenActualizar"));
        imagenIniciar.setCellValueFactory(new PropertyValueFactory<PosActualizacion, ImageView>("imagenIniciar"));
        ubicacionActualizador.setCellValueFactory(new PropertyValueFactory<PosActualizacion, String>("ubicacionActualizador"));

        tablaActualizacion.getItems().setAll(insert());
    }

    private Collection<? extends PosActualizacion> insert() {
        this.btnSiguiente.setDisable(true);
        return FXCollections.observableArrayList(
                new PosActualizacion("SrvWinFE_Update_Epos", "No Existe", "", null, null, null, null, ubicacionActualizadorPrintManager),
                new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, "", null, null, null, null, ubicacionActualizadorServidor),
                new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, "", null, null, null, null, ubicacionActualizadorBd),
                new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, "", null, null, null, null, ubicacionActualizadorCliente));
    }

    @FXML
    private void InicioActualizacion() {

        ObservableList<PosActualizacion> list = FXCollections.observableArrayList();
        list.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_DETENIENDO_SERVICIO, loading(), null, null, null, ubicacionActualizadorPrintManager));
        list.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, "", null, null, null, null, ubicacionActualizadorServidor));
        list.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, "", null, null, null, null, ubicacionActualizadorBd));
        list.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, "", null, null, null, null, ubicacionActualizadorCliente));
        tablaActualizacion.setItems(list);

        TaskDetenerServicio objeto = new TaskDetenerServicio();
        objeto.addPropertyChangeListener(this);
        objeto.execute();
    }

    public void Siguiente_button() {
        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        stage.close();
        this.application.resultadoActualizacion();
    }

    public void Atras_button() {
        Stage stage = (Stage) btnAtras.getScene().getWindow();
        stage.close();
        this.application.relacionComponente();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public ImageView check() {
        return new ImageView(new Image(constante.ICON_CHECK));
    }

    public ImageView loading() {
        return new ImageView(new Image(constante.ICON_LOADING));
    }

    public ImageView info() {
        return new ImageView(new Image(constante.ICON_INFO));
    }

    public ImageView warning() {
        return new ImageView(new Image(constante.ICON_WARNING));
    }

    public ImageView error() {
        return new ImageView(new Image(constante.ICON_ERROR));
    }

    class TaskDetenerServicio extends SwingWorker<Void, Void> {

        /* Iconos - Acciones */

        ImageView detenerPrintmanager = new ImageView();
        ImageView detenerServidorPOS = new ImageView();
        ImageView detenerBD = new ImageView();
        ImageView detenerCliente = new ImageView();

        ImageView respaldarPrintmanager = new ImageView();
        ImageView respaldarServidorPOS = new ImageView();
        ImageView respaldarBD = new ImageView();
        ImageView respaldarCliente = new ImageView();

        ImageView actualizarPrintmanager = new ImageView();
        ImageView actualizarBD = new ImageView();
        ImageView actualizarServidorPos = new ImageView();
        ImageView actualizarCliente = new ImageView();

        ImageView iniciarPrintmanager = new ImageView();
        ImageView iniciarBD = new ImageView();
        ImageView iniciarServidorPos = new ImageView();
        ImageView iniciarCliente = new ImageView();

        DetenerServicioTask detenerServicioTask = new DetenerServicioTask();
        RespaldarServicioTask respaldarServicioTask = new RespaldarServicioTask();
        ActualizarServicioTask actualizarServicioTask = new ActualizarServicioTask();
        IniciarServicioTask iniciarServicioTask = new IniciarServicioTask();

        private ImageView detenerPrintmanager() {
            try {
                int estadoServicio = detenerServicioTask.detenerPrintmanager();
                if (estadoServicio == 0) { // iniciado
                    detenerPrintmanager = warning();
                    logger.info("[Fase 1] Estado de Servicio: Iniciado(0) - PrintManager ");
                    return warning();
                } else if (estadoServicio == -1) { // detenido
                    detenerPrintmanager = check();
                    logger.info("[Fase 1] Estado de Servicio: Detenido(-1) - PrintManager ");
                    return check();
                } else if (estadoServicio == -2) { // no existe
                    detenerPrintmanager = info();
                    logger.info("[Fase 1] Estado de Servicio: No Existe(-2) - PrintManager ");
                    return info();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 1] Estado de Servicio: Error - PrintManager");
                detenerPrintmanager = error();  // error
                logger.error(e);
            }
            logger.error("[Fase 1] Estado de Servicio: Error - PrintManager");
            detenerPrintmanager = error();  // error
            return error();
        }
        private ImageView detenerServidorPOS() {
            try {
                int estadoServicio = detenerServicioTask.detenerServidorPos();
                if (estadoServicio == 0) { // iniciado
                    detenerServidorPOS = warning();
                    logger.info("[Fase 1] Estado de Servicio: Iniciado(0) - Servidor POS ");
                    return warning();
                } else if (estadoServicio == -1) { // detenido
                    detenerServidorPOS = check();
                    logger.info("[Fase 1] Estado de Servicio: Detenido(-1) - Servidor POS ");
                    return check();
                } else if (estadoServicio == -2) { // no existe
                    detenerServidorPOS = info();
                    logger.info("[Fase 1] Estado de Servicio: No Existe(-2) - Servidor POS ");
                    return info();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 1] Estado de Servicio: Error - Servidor POS");
                detenerServidorPOS = error();  // error
                logger.error(e);
            }
            logger.error("[Fase 1] Estado de Servicio: Error - Servidor POS");
            detenerServidorPOS = error();  // error
            return error();
        }
        private ImageView detenerBD() {
            try {
                int estadoServicio = detenerServicioTask.detenerBD();
                if (estadoServicio == 0) { // iniciado
                    detenerBD = warning();
                    logger.info("[Fase 1]Estado de Servicio: Iniciado(0) - Base de Datos");
                    return warning();
                } else if (estadoServicio == -1) { // detenido
                    detenerBD = check();
                    logger.info("[Fase 1] Estado de Servicio: Detenido(-1) - Base de Datos");
                    return check();
                } else if (estadoServicio == -2) { // no existe
                    detenerBD = info();
                    logger.info("[Fase 1] Estado de Servicio: No Existe(-2) - Base de Datos");
                    return info();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 1] Estado de Servicio: Error - Base de Datos");
                detenerBD = error();  // error
                logger.error(e);
            }
            logger.error("[Fase 1] Estado de Servicio: Error - Base de Datos");
            detenerBD = error();  // error
            return error();
        }
        private ImageView detenerCliente() {
            try {
                int estadoServicio = detenerServicioTask.detenerCliente();
                if (estadoServicio == 0) { // iniciado
                    detenerCliente = warning();
                    logger.info("[Fase 1] Estado de Servicio: Iniciado(0) - Cliente");
                    return warning();
                } else if (estadoServicio == -1) { // detenido
                    detenerCliente = check();
                    logger.info("[Fase 1] Estado de Servicio: Detenido(-1) - Cliente");
                    return check();
                } else if (estadoServicio == -2) { // no existe
                    detenerCliente = info();
                    logger.info("[Fase 1] Estado de Servicio: No Existe(-2) - Cliente");
                    return info();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 1] Estado de Servicio: Error - Cliente");
                detenerCliente = error();  // error
                logger.error(e);
            }
            logger.error("[Fase 1] Estado de Servicio: Error - Cliente");
            detenerCliente = error();  // error
            return error();
        }

        private ImageView respaldarPrintmanager() {
            try {
                if (ubicacionInstaladorPrintManager.contains(constante.NO_EXISTE)) {
                    respaldarPrintmanager = info();
                    logger.info("[Fase 2] Estado de Backup: Sin Acción - PrintManager");
                    return info();
                } else if (respaldarServicioTask.respaldarPrintmanager().equals(true)) {
                    logger.info("[Fase 2] Estado de Backup: Terminado - PrintManager");
                    respaldarPrintmanager = check();
                    return check();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 2] Estado de Backup: Error - PrintManager");
                respaldarPrintmanager = error();
                logger.error(e);
            }
            logger.error("[Fase 2] Estado de Backup: Error - PrintManager");
            respaldarPrintmanager = error();
            return error();
        }
        private ImageView respaldarServidorPOS() {
            try {
                if (ubicacionInstaladorServidor.contains(constante.NO_EXISTE)) {
                    logger.info("[Fase 2] Estado de Backup: Sin Acción - Servidor POS");
                    respaldarServidorPOS = info();
                    return info();
                } else if (respaldarServicioTask.respaldarServidorPos().equals(true)) {
                    logger.info("[Fase 2] Estado de Backup: Terminado - Servidor POS");
                    respaldarServidorPOS = check();
                    return check();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 2] Estado de Backup: Error - Servidor POS");
                respaldarServidorPOS = error();
                logger.error(e);
            }
            logger.error("[Fase 2] Estado de Backup: Error - Servidor POS");
            respaldarServidorPOS = error();
            return error();
        }
        private ImageView respaldarBD() {
            try {
                if (ubicacionInstaladorBd.contains(constante.NO_EXISTE)) {
                    logger.info("[Fase 2] Estado de Backup: Sin Acción - Base de Datos");
                    respaldarBD = info();
                    return info();
                } else if (respaldarServicioTask.respaldarServidorBD().equals(true)) {
                    logger.info("[Fase 2] Estado de Backup: Terminado - Base de Datos");
                    respaldarBD = check();
                    return check();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 2] Estado de Backup: Error - Base de Datos");
                respaldarBD = error();
                logger.error(e);
            }
            logger.error("[Fase 2] Estado de Backup: Error - Base de Datos");
            respaldarBD = error();
            return error();
        }
        private ImageView respaldarCliente() {
            try {
                if (ubicacionInstaladorCliente.contains(constante.NO_EXISTE)) {
                    logger.info("[Fase 2] Estado de Backup: Sin Acción - Cliente");
                    respaldarCliente = info();
                    return info();
                } else if (respaldarServicioTask.respaldarServidorCliente().equals(true)) {
                    logger.info("[Fase 2] Estado de Backup: Terminado - Cliente");
                    respaldarCliente = check();
                    return check();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 2] Estado de Backup: Error - Cliente");
                respaldarCliente = error();
                logger.error(e);
            }
            logger.error("[Fase 2] Estado de Backup: Error - Cliente");
            respaldarCliente = error();
            return error();
        }

        private ImageView actualizarPrintmanager() {
            try {
                if (ubicacionInstaladorPrintManager.contains(constante.NO_EXISTE)) {
                    logger.info("[Fase 3] Estado de actualización: Sin Acción - PrintManager");
                    actualizarPrintmanager = info();
                    return info();
                } else if (actualizarServicioTask.actualizarPrintmanager().equals(true)) {
                    logger.info("[Fase 3] Estado de actualización: Terminado - PrintManager");
                    actualizarPrintmanager = check();
                    return check();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 3] Estado de actualización: Error - PrintManager");
                actualizarPrintmanager = error();
                logger.error(e);
            }
            logger.error("[Fase 3] Estado de actualización: Error - PrintManager");
            actualizarPrintmanager = error();
            return error();
        }
        private ImageView actualizarServidorPos() {
            try {
                if (ubicacionInstaladorServidor.contains(constante.NO_EXISTE)) {
                    logger.info("[Fase 3] Estado de actualización: Sin Acción - Servidor POS");
                    actualizarServidorPos = info();
                    return info();
                } else if (actualizarServicioTask.actualizarServidorPos().equals(true)) {
                    logger.info("[Fase 3] Estado de actualización: Terminado - Servidor POS");
                    actualizarServidorPos = check();
                    return check();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 3] Estado de actualización: Error - Servidor POS");
                actualizarServidorPos = error();
                logger.error(e);
            }
            logger.error("[Fase 3] Estado de actualización: Error - Servidor POS");
            actualizarServidorPos = error();
            return error();
        }
        private ImageView actualizarBD() {
            try {
                if (ubicacionInstaladorBd.contains(constante.NO_EXISTE)) {
                    logger.info("[Fase 3] Estado de actualización: Sin Acción - Base de Datos");
                    actualizarBD = info();
                    return info();
                } else if (actualizarServicioTask.actualizarServidorBD().equals(true)) {
                    logger.info("[Fase 3] Estado de actualización: Terminado - Base de Datos");
                    actualizarBD = check();
                    return check();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 3] Estado de actualización: Error - Base de Datos");
                actualizarBD = error();
                logger.error(e);
            }
            logger.error("[Fase 3] Estado de actualización: Error - Base de Datos");
            actualizarBD = error();
            return error();
        }
        private ImageView actualizarCliente() {
            try {
                if (ubicacionInstaladorCliente.contains(constante.NO_EXISTE)) {
                    logger.info("[Fase 3] Estado de actualización: Sin Acción - Cliente");
                    actualizarCliente = info();
                    return info();
                } else if (actualizarServicioTask.actualizarServidorCliente().equals(true)) {
                    logger.info("[Fase 3] Estado de actualización: Terminado - Cliente");
                    actualizarCliente = check();
                    return check();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 3] Estado de actualización: Error - Cliente");
                actualizarCliente = error();
                logger.error(e);
            }
            logger.error("[Fase 3] Estado de actualización: Error - Cliente");
            actualizarCliente = error();
            return error();
        }

        private ImageView iniciarPrintmanager() {
            try {
                if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_PRINTMANAGER).contains(constante.SERVICIO_POS_NO_EXISTE)) {
                    logger.info("[Fase 4] Estado de Servicio: Sin Acción - PrintManager");
                    iniciarPrintmanager = info();
                    return info();
                } else if (iniciarServicioTask.iniciarPrintmanager().equals(true)) {
                    logger.info("[Fase 4] Estado de Servicio: Iniciado - PrintManager");
                    iniciarPrintmanager = check();
                    return check();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 4] Estado de Servicio: Error - PrintManager");
                iniciarPrintmanager = error();
                logger.error(e);
            }
            logger.error("[Fase 4] Estado de Servicio: Error - PrintManager");
            iniciarPrintmanager = error();
            return error();
        }
        private ImageView iniciarServidorPos() {
            try {
                if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_SERVER).contains(constante.SERVICIO_POS_NO_EXISTE)) {
                    logger.info("[Fase 4] Estado de Servicio: Sin Acción - Servidor POS");
                    iniciarServidorPos = info();
                    return info();
                } else if (iniciarServicioTask.iniciarServidorPos().equals(true)) {
                    logger.info("[Fase 4] Estado de Servicio: Iniciado - Servidor POS");
                    iniciarServidorPos = check();
                    return check();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 4] Estado de Servicio: Error - Servidor POS");
                iniciarServidorPos = error();
                logger.error(e);
            }
            logger.error("[Fase 4] Estado de Servicio: Error - Servidor POS");
            iniciarServidorPos = error();
            return error();
        }
        private ImageView iniciarBD() {
            try {
                if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_BD).contains(constante.SERVICIO_POS_NO_EXISTE)) {
                    logger.info("[Fase 4] Estado de Servicio: Sin Acción - Base de Datos");
                    iniciarBD = info();
                    return info();
                } else if (iniciarServicioTask.iniciarBD().equals(true)) {
                    logger.info("[Fase 4] Estado de Servicio: Iniciado - Base de Datos");
                    iniciarBD = check();
                    return check();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 4] Estado de Servicio: Error - Base de Datos");
                iniciarBD = error();
                logger.error(e);
            }
            logger.error("[Fase 4] Estado de Servicio: Error - Base de Datos");
            iniciarBD = error();
            return error();
        }
        private ImageView iniciarCliente() {
            try {
                if (utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_CLIENTE).contains(constante.SERVICIO_POS_NO_EXISTE)) {
                    logger.info("[Fase 4] Estado de Servicio: Sin Acción - Cliente");
                    iniciarCliente = info();
                    return info();
                } else if (iniciarServicioTask.iniciarCliente().equals(true)) {
                    logger.info("[Fase 4] Estado de Servicio: Iniciado - Cliente");
                    iniciarCliente = check();
                    return check();
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("[Fase 4] Estado de Servicio: Error - Cliente");
                iniciarCliente = error();
                logger.error(e);
            }
            logger.error("[Fase 4] Estado de Servicio: Error - Cliente");
            iniciarCliente = error();
            return error();
        }
        @Override
        public Void doInBackground() {
            logger.info("Proceso de Actualizacion:  ");
            ObservableList<PosActualizacion> list3 = FXCollections.observableArrayList();
            list3.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_SERVICIO_DETENIDO_PRINTMANAGER,    detenerPrintmanager(), null, null, null, ubicacionActualizadorPrintManager));
            list3.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_DETENIENDO_SERVICIO,                                       loading(), null, null, null, ubicacionActualizadorServidor));
            list3.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, "",                                                                 null, null, null, null, ubicacionActualizadorBd));
            list3.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, "",                                                       null, null, null, null, ubicacionActualizadorCliente));
            tablaActualizacion.setItems(list3);

            ObservableList<PosActualizacion> list5 = FXCollections.observableArrayList();
            list5.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_SERVICIO_DETENIDO_PRINTMANAGER, this.detenerPrintmanager, null, null, null, ubicacionActualizadorPrintManager));
            list5.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_SERVICIO_DETENIDO_SERVER,                       detenerServidorPOS(), null, null, null, ubicacionActualizadorServidor));
            list5.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_DETENIENDO_SERVICIO,                                               loading(), null, null, null, ubicacionActualizadorBd));
            list5.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, "",                                                       null, null, null, null, ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list5);

            ObservableList<PosActualizacion> list7 = FXCollections.observableArrayList();
            list7.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_SERVICIO_DETENIDO_PRINTMANAGER, this.detenerPrintmanager, null, null, null, ubicacionActualizadorPrintManager));
            list7.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_SERVICIO_DETENIDO_SERVER,                    this.detenerServidorPOS, null, null, null, ubicacionActualizadorServidor));
            list7.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_SERVICIO_DETENIDO_BD,                                            detenerBD(), null, null, null, ubicacionActualizadorBd));
            list7.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_DETENIENDO_SERVICIO,                                     loading(), null, null, null, ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list7);

            ObservableList<PosActualizacion> list8 = FXCollections.observableArrayList();
            list8.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_SERVICIO_DETENIDO_PRINTMANAGER, this.detenerPrintmanager,           loading(), null, null, ubicacionActualizadorPrintManager));
            list8.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_SERVICIO_DETENIDO_SERVER,                    this.detenerServidorPOS, null, null, null, ubicacionActualizadorServidor));
            list8.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_SERVICIO_DETENIDO_BD,                                         this.detenerBD, null, null, null, ubicacionActualizadorBd));
            list8.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_SERVICIO_DETENIDO_BD,                             detenerCliente(), null, null, null, ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list8);

            /************************************************************************************ Proceso que backupea los 4 servicios ************************************************************************************/
            ObservableList<PosActualizacion> list10 = FXCollections.observableArrayList();
            list10.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_FINALIZADO_BACKUP,             this.detenerPrintmanager, respaldarPrintmanager(), null, null, ubicacionActualizadorPrintManager));
            list10.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_REALIZANDO_BACKUP,                          this.detenerServidorPOS,               loading(), null, null, ubicacionActualizadorServidor));
            list10.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_SERVICIO_DETENIDO_BD,                                        this.detenerBD,     null, null, null, ubicacionActualizadorBd));
            list10.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_SERVICIO_DETENIDO_BD,                         this.detenerCliente,     null, null, null, ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list10);


            ObservableList<PosActualizacion> list11 = FXCollections.observableArrayList();
            list11.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_FINALIZADO_BACKUP,              this.detenerPrintmanager, this.respaldarPrintmanager, null, null, ubicacionActualizadorPrintManager));
            list11.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_FINALIZADO_BACKUP,                           this.detenerServidorPOS,     respaldarServidorPOS(), null, null, ubicacionActualizadorServidor));
            list11.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_REALIZANDO_BACKUP,                                            this.detenerBD,                  loading(), null, null, ubicacionActualizadorBd));
            list11.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_SERVICIO_DETENIDO_BD,                          this.detenerCliente,        null, null, null, ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list11);


            ObservableList<PosActualizacion> list12 = FXCollections.observableArrayList();
            list12.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_FINALIZADO_BACKUP,             this.detenerPrintmanager, this.respaldarPrintmanager, null, null, ubicacionActualizadorPrintManager));
            list12.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_FINALIZADO_BACKUP,                          this.detenerServidorPOS,  this.respaldarServidorPOS, null, null, ubicacionActualizadorServidor));
            list12.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_FINALIZADO_BACKUP,                                           this.detenerBD,              respaldarBD(), null, null, ubicacionActualizadorBd));
            list12.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_REALIZANDO_BACKUP,                            this.detenerCliente,                  loading(), null, null, ubicacionActualizadorCliente));
            tablaActualizacion.setItems(list12);


            ObservableList<PosActualizacion> list13 = FXCollections.observableArrayList();
            list13.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_FINALIZADO_BACKUP,             this.detenerPrintmanager, this.respaldarPrintmanager,           loading(), null, ubicacionActualizadorPrintManager));
            list13.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_FINALIZADO_BACKUP,                          this.detenerServidorPOS,  this.respaldarServidorPOS, null, null, ubicacionActualizadorServidor));
            list13.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_FINALIZADO_BACKUP,                                           this.detenerBD,           this.respaldarBD, null, null, ubicacionActualizadorBd));
            list13.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_FINALIZADO_BACKUP,                            this.detenerCliente,         respaldarCliente(), null, null, ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list13);


            /******************************************************************************************* Proceso que actualiza los 4 servicios ************************************************************************************/
            ObservableList<PosActualizacion> list15 = FXCollections.observableArrayList();
            list15.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_ACTUALIZADO_PRINTMANAGER,       this.detenerPrintmanager, this.respaldarPrintmanager, actualizarPrintmanager(), null, ubicacionActualizadorPrintManager));
            list15.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_ACTUALIZANDO,                                this.detenerServidorPOS,  this.respaldarServidorPOS,                loading(), null, ubicacionActualizadorServidor));
            list15.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_FINALIZADO_BACKUP,                                            this.detenerBD,           this.respaldarBD,      null, null, ubicacionActualizadorBd));
            list15.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_FINALIZADO_BACKUP,                             this.detenerCliente,      this.respaldarCliente,      null, null, ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list15);


            ObservableList<PosActualizacion> list17 = FXCollections.observableArrayList();
            list17.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_ACTUALIZADO_PRINTMANAGER,       this.detenerPrintmanager, this.respaldarPrintmanager, this.actualizarPrintmanager, null, ubicacionActualizadorPrintManager));
            list17.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_ACTUALIZADO_SERVER,                          this.detenerServidorPOS,  this.respaldarServidorPOS,     actualizarServidorPos(), null, ubicacionActualizadorServidor));
            list17.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_ACTUALIZANDO,                                                 this.detenerBD,           this.respaldarBD,                   loading(), null, ubicacionActualizadorBd));
            list17.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_FINALIZADO_BACKUP,                             this.detenerCliente,      this.respaldarCliente,         null, null, ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list17);


            ObservableList<PosActualizacion> list18 = FXCollections.observableArrayList();
            list18.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_ACTUALIZADO_PRINTMANAGER,       this.detenerPrintmanager, this.respaldarPrintmanager, this.actualizarPrintmanager, null, ubicacionActualizadorPrintManager));
            list18.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_ACTUALIZADO_SERVER,                          this.detenerServidorPOS,  this.respaldarServidorPOS,  this.actualizarServidorPos, null, ubicacionActualizadorServidor));
            list18.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_ACTUALIZADO_BD,                                               this.detenerBD,           this.respaldarBD,              actualizarBD(), null, ubicacionActualizadorBd));
            list18.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_ACTUALIZANDO,                                  this.detenerCliente,      this.respaldarCliente,                   loading(), null, ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list18);


            ObservableList<PosActualizacion> list19 = FXCollections.observableArrayList();
            list19.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_INICIANDO_COMPONENTES,           this.detenerPrintmanager, this.respaldarPrintmanager, this.actualizarPrintmanager,        loading(), ubicacionActualizadorPrintManager));
            list19.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_ACTUALIZADO_SERVER,                           this.detenerServidorPOS,  this.respaldarServidorPOS,  this.actualizarServidorPos, null, ubicacionActualizadorServidor));
            list19.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_ACTUALIZADO_BD,                                                this.detenerBD,           this.respaldarBD,           this.actualizarBD, null, ubicacionActualizadorBd));
            list19.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_ACTUALIZADO_CLIENTE,                            this.detenerCliente,      this.respaldarCliente,         actualizarCliente(), null, ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list19);


            /******************************************************************************************* Proceso que Inicia los 4 servicios ************************************************************************************/

            ObservableList<PosActualizacion> list20 = FXCollections.observableArrayList();
            list20.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_INICIADO_COMPONENTE,              this.detenerPrintmanager, this.respaldarPrintmanager, this.actualizarPrintmanager, iniciarPrintmanager(), ubicacionActualizadorPrintManager));
            list20.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_INICIANDO_COMPONENTES,                         this.detenerServidorPOS,  this.respaldarServidorPOS,  this.actualizarServidorPos,             loading(), ubicacionActualizadorServidor));
            list20.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_ACTUALIZADO_BD,                                                 this.detenerBD,           this.respaldarBD,           this.actualizarBD,      null, ubicacionActualizadorBd));
            list20.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_ACTUALIZADO_CLIENTE,                             this.detenerCliente,      this.respaldarCliente,      this.actualizarCliente,      null, ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list20);

            ObservableList<PosActualizacion> list21 = FXCollections.observableArrayList();
            list21.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_INICIADO_COMPONENTE,              this.detenerPrintmanager, this.respaldarPrintmanager, this.actualizarPrintmanager, this.iniciarPrintmanager, ubicacionActualizadorPrintManager));
            list21.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_INICIADO_COMPONENTE,                           this.detenerServidorPOS,  this.respaldarServidorPOS,  this.actualizarServidorPos,     iniciarServidorPos(), ubicacionActualizadorServidor));
            list21.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_INICIANDO_COMPONENTES,                                          this.detenerBD,           this.respaldarBD,           this.actualizarBD,                loading(), ubicacionActualizadorBd));
            list21.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_ACTUALIZADO_CLIENTE,                             this.detenerCliente,      this.respaldarCliente,      this.actualizarCliente,         null, ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list21);

            ObservableList<PosActualizacion> list22 = FXCollections.observableArrayList();
            list22.add(new PosActualizacion(constante.SERVICIO_POS_PRINTMANAGER, estadoPosPrintManager, constante.SERVICIO_POS_INICIADO_COMPONENTE,              this.detenerPrintmanager, this.respaldarPrintmanager, this.actualizarPrintmanager, this.iniciarPrintmanager, ubicacionActualizadorPrintManager));
            list22.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_INICIADO_COMPONENTE,                           this.detenerServidorPOS,  this.respaldarServidorPOS,  this.actualizarServidorPos,  this.iniciarServidorPos, ubicacionActualizadorServidor));
            list22.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_INICIADO_COMPONENTE,                                            this.detenerBD,           this.respaldarBD,           this.actualizarBD,              iniciarBD(), ubicacionActualizadorBd));
            list22.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_INICIANDO_COMPONENTES,                           this.detenerCliente,      this.respaldarCliente,      this.actualizarCliente,                loading(), ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list22);

            ObservableList<PosActualizacion> list23 = FXCollections.observableArrayList();
            list23.add(new PosActualizacion("SrvWinFE_Update_Epos", estadoPosPrintManager, constante.SERVICIO_POS_INICIADO_COMPONENTE,              this.detenerPrintmanager, this.respaldarPrintmanager, this.actualizarPrintmanager, this.iniciarPrintmanager, ubicacionActualizadorPrintManager));
            list23.add(new PosActualizacion(constante.SERVICIO_POS_SERVER, estadoPosServer, constante.SERVICIO_POS_INICIADO_COMPONENTE,                           this.detenerServidorPOS,  this.respaldarServidorPOS,  this.actualizarServidorPos,  this.iniciarServidorPos, ubicacionActualizadorServidor));
            list23.add(new PosActualizacion(constante.SERVICIO_POS_BD, estadoPosBd, constante.SERVICIO_POS_INICIADO_COMPONENTE,                                            this.detenerBD,           this.respaldarBD,           this.actualizarBD,           this.iniciarBD, ubicacionActualizadorBd));
            list23.add(new PosActualizacion(constante.SERVICIO_POS_CLIENTE, estadoPosCliente, constante.SERVICIO_POS_INICIADO_COMPONENTE,                             this.detenerCliente,      this.respaldarCliente,      this.actualizarCliente,         iniciarCliente(), ubicacionActualizadorCliente));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list23);


            btnSiguiente.setDisable(false);
            tablaActualizacion.refresh();
            return null;
        }

        @Override
        public void done() {
//            fConsultarCDR.getProgressBar().setVisible(false);
//            fConsultarCDR.getBtnaceptar().setEnabled(true);
//            fConsultarCDR.getBtnreporte().setEnabled(true);
        }
    }

}
