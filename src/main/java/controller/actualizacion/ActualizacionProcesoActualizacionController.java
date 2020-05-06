package controller.actualizacion;

import controller.Main;
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
import java.io.IOException;
import java.util.Collection;

import controller.task.ActualizarServicioTask;
import controller.task.DetenerServicioTask;
import controller.task.IniciarServicioTask;
import controller.task.RespaldarServicioTask;
import model.PosActualizacion;
import util.Constantes;
import util.Utilitario;
import org.apache.log4j.Logger;

public class ActualizacionProcesoActualizacionController implements PropertyChangeListener {
    Logger logger = Logger.getLogger(ActualizacionProcesoActualizacionController.class);
    Constantes constante = new Constantes();

    @FXML
    public Button btnSiguiente;

    @FXML
    public Button btnActualizar;

    @FXML
    public Button btnAtras;


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

    String ubicacionActualizadorServicio = utilitario.obtenerRutaServicioInstalar();
    String estadoPosServer = utilitario.obtenerEstadoServicio();
    String ubicacionInstaladorServidor = utilitario.obtenerRutaServicioInstaladoRaiz();

    @FXML
    private TableView<PosActualizacion> tablaActualizacion;
    private Main application;

    public ActualizacionProcesoActualizacionController() throws IOException {
    }

    public void setApp(Main application) {
        this.application = application;
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
                new PosActualizacion(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, "Inicio de la Actualización", null, null, null, null, ubicacionActualizadorServicio));
    }

    @FXML
    private void InicioActualizacion() throws IOException {

        ObservableList<PosActualizacion> list = FXCollections.observableArrayList();
        list.add(new PosActualizacion(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, "Comenzando con la Actualización", null, null, null, null, ubicacionActualizadorServicio));
        tablaActualizacion.setItems(list);

        TaskServicio taskServicio = new TaskServicio();
        taskServicio.addPropertyChangeListener(this);
        taskServicio.execute();
    }

    public void siguiente() {
        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        stage.close();
        this.application.actualiacionResultadoActualizacion();
    }

    public void volverAtras() {
        Stage stage = (Stage) btnAtras.getScene().getWindow();
        stage.close();
        this.application.actualizacionRelacionComponente();
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

    class TaskServicio extends SwingWorker<Void, Void> {

        ImageView detenerServicio = new ImageView();
        ImageView respaldarServicioS = new ImageView();
        ImageView actualizarServicio = new ImageView();
        ImageView iniciarServicio = new ImageView();

        DetenerServicioTask detenerServicioTask = new DetenerServicioTask();
        RespaldarServicioTask respaldarServicioTask = new RespaldarServicioTask();
        ActualizarServicioTask actualizarServicioTask = new ActualizarServicioTask();
        IniciarServicioTask iniciarServicioTask = new IniciarServicioTask();

        TaskServicio() throws IOException {
        }

        private ImageView iniciarServicio() {
            ImageView icono = new ImageView();
            switch (iniciarServicioTask.iniciarServicio()) {
                case 0:
                    logger.error("No se inicio el servicio, se encuentra detenido");
                    iniciarServicio = error();
                    icono = error();
                    break;
                case 1:
                    logger.info("El servicio se inicio con exito");
                    iniciarServicio = check();
                    icono = check();
                    break;
                case -1:
                    logger.error("No se inicio el servicio, ya que el servicio no existe");
                    iniciarServicio = info();
                    icono = info();
                    break;
                case -2:
                    logger.error("Ocurrio un error al detener el servicio");
                    iniciarServicio = error();
                    icono = error();
                    break;
            }
            return icono;
        }

        private ImageView detenerServicio() {
            ImageView icono = new ImageView();
            switch (detenerServicioTask.detenerServicio()) {
                case 0:
                    logger.info("El servicio se detuvo con exito");
                    detenerServicio = check();
                    icono = check();
                    break;
                case 1:
                    logger.error("No se detuvo el servicio");
                    detenerServicio = error();
                    icono = error();
                    break;
                case -1:
                    logger.error("No se detuvo el servicio, ya que el servicio no existe");
                    detenerServicio = info();
                    icono = info();
                    break;
                case -2:
                    logger.error("Ocurrio un error al detener el servicio");
                    icono = error();
                    detenerServicio = error();
                    break;
            }
            return icono;
        }

        private ImageView respaldarServicio() {
            try {
                if (ubicacionInstaladorServidor.contains(constante.NO_EXISTE)) {
                    logger.error("No se respaldo el servicio, ya que no existe el servicio a instalado");
                    respaldarServicioS = info();
                    return info();
                } else if (respaldarServicioTask.respaldarServicio().equals(true)) {
                    logger.info("El servicio fue respaldado con exito");
                    respaldarServicioS = check();
                    return check();
                }
            } catch (Exception e) {
                logger.error("Ocurrio un error al respaldar el servicio", e);
                respaldarServicioS = error();
                logger.error(e);
            }
            respaldarServicioS = error();
            return error();
        }

        private ImageView actualizarServicio() {
            if (ubicacionInstaladorServidor.contains(constante.NO_EXISTE)) {
                logger.error("No se realizo la actualizacion del servicio, por que no se encuentra el servicio instalado");
                actualizarServicio = info();
                return info();
            } else if (actualizarServicioTask.actualizarServicio().equals(true)) {
                actualizarServicio = check();
                return check();
            } else {
                logger.error("Ocurrio un error al actualizar el servicio");
                actualizarServicio = error();
                return error();
            }
        }

        @Override
        public Void doInBackground() {
            /************************************************************************************ Proceso que detiene el servicio ************************************************************************************/
            ObservableList<PosActualizacion> list3 = FXCollections.observableArrayList();
            list3.add(new PosActualizacion(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, constante.SERVICIO_POS_DETENIENDO_SERVICIO, loading(), null, null, null, ubicacionActualizadorServicio));
            tablaActualizacion.setItems(list3);

            ObservableList<PosActualizacion> list5 = FXCollections.observableArrayList();
            list5.add(new PosActualizacion(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, constante.SERVICIO_POS_SERVICIO_DETENIDO_SERVER, detenerServicio(), null, null, null, ubicacionActualizadorServicio));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list5);

            /************************************************************************************ Proceso que backupea los 4 servicios ************************************************************************************/
            ObservableList<PosActualizacion> list10 = FXCollections.observableArrayList();
            list10.add(new PosActualizacion(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, constante.SERVICIO_POS_REALIZANDO_BACKUP, this.detenerServicio, loading(), null, null, ubicacionActualizadorServicio));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list10);


            ObservableList<PosActualizacion> list11 = FXCollections.observableArrayList();
            list11.add(new PosActualizacion(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, constante.SERVICIO_POS_FINALIZADO_BACKUP, this.detenerServicio, respaldarServicio(), null, null, ubicacionActualizadorServicio));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list11);


            /******************************************************************************************* Proceso que actualiza los 4 servicios ************************************************************************************/
            ObservableList<PosActualizacion> list15 = FXCollections.observableArrayList();
            list15.add(new PosActualizacion(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, constante.SERVICIO_POS_ACTUALIZANDO, this.detenerServicio, this.respaldarServicioS, loading(), null, ubicacionActualizadorServicio));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list15);


            ObservableList<PosActualizacion> list17 = FXCollections.observableArrayList();
            list17.add(new PosActualizacion(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, constante.SERVICIO_POS_ACTUALIZADO_SERVER, this.detenerServicio, this.respaldarServicioS, actualizarServicio(), null, ubicacionActualizadorServicio));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list17);

            /******************************************************************************************* Proceso que Inicia los 4 servicios ************************************************************************************/

            ObservableList<PosActualizacion> list20 = FXCollections.observableArrayList();
            list20.add(new PosActualizacion(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, constante.SERVICIO_POS_INICIANDO_COMPONENTES, this.detenerServicio, this.respaldarServicioS, this.actualizarServicio, loading(), ubicacionActualizadorServicio));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list20);

            ObservableList<PosActualizacion> list21 = FXCollections.observableArrayList();
            list21.add(new PosActualizacion(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, constante.SERVICIO_POS_INICIADO_COMPONENTE, this.detenerServicio, this.respaldarServicioS, this.actualizarServicio, iniciarServicio(), ubicacionActualizadorServicio));
            tablaActualizacion.refresh();
            tablaActualizacion.setItems(list21);

            btnSiguiente.setDisable(false);
            btnActualizar.setDisable(true);
            btnAtras.setDisable(true);
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
