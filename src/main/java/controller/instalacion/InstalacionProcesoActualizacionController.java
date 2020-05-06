package controller.instalacion;

import controller.Main;
import controller.task.*;
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
import model.PosInstalacion;
import org.apache.log4j.Logger;
import util.Constantes;
import util.Utilitario;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Collection;

public class InstalacionProcesoActualizacionController implements PropertyChangeListener {

    Logger logger = Logger.getLogger(InstalacionProcesoActualizacionController.class);
    Constantes constante = new Constantes();

    @FXML
    public Button btnSiguiente;
    @FXML
    TableColumn<PosInstalacion, String> nombre = new TableColumn<PosInstalacion, String>("nombre");
    @FXML
    TableColumn<PosInstalacion, String> estado = new TableColumn<PosInstalacion, String>("estado");
    @FXML
    TableColumn<PosInstalacion, String> estadoProceso = new TableColumn<PosInstalacion, String>("estadoProceso");
    @FXML
    TableColumn<PosInstalacion, ImageView> imagenDetener = new TableColumn<PosInstalacion, ImageView>("detenerImagen");
    @FXML
    TableColumn<PosInstalacion, ImageView> imagenRespaldar = new TableColumn<PosInstalacion, ImageView>("imagenRespaldar");
    @FXML
    TableColumn<PosInstalacion, ImageView> imagenInstalar = new TableColumn<PosInstalacion, ImageView>("imagenInstalar");
    @FXML
    TableColumn<PosInstalacion, ImageView> imagenIniciar = new TableColumn<PosInstalacion, ImageView>("imagenIniciar");
    @FXML
    TableColumn<PosInstalacion, String> ubicacionInstalador = new TableColumn<PosInstalacion, String>("ubicacionInstalador");

    Utilitario utilitario = new Utilitario();
    String ubicacionInstaladorServicio = utilitario.obtenerRutaServicioInstalar();
    String estadoPosServer = utilitario.obtenerEstadoServicio();

    @FXML
    private Button btnAtras;
    @FXML
    private Button btnInstalar;
    @FXML
    private TableView<PosInstalacion> tablaInstalacion;
    private Main application;

    public InstalacionProcesoActualizacionController() throws IOException {
    }

    public void setApp(Main application) {
        this.application = application;
//        this.btnSiguiente.setDisable();
        nombre.setCellValueFactory(new PropertyValueFactory<PosInstalacion, String>("nombre"));
        estado.setCellValueFactory(new PropertyValueFactory<PosInstalacion, String>("estado"));
        estadoProceso.setCellValueFactory(new PropertyValueFactory<PosInstalacion, String>("estadoProceso"));
        imagenDetener.setCellValueFactory(new PropertyValueFactory<PosInstalacion, ImageView>("imagenDetener"));
        imagenRespaldar.setCellValueFactory(new PropertyValueFactory<PosInstalacion, ImageView>("imagenRespaldar"));
        imagenInstalar.setCellValueFactory(new PropertyValueFactory<PosInstalacion, ImageView>("imagenInstalar"));
        imagenIniciar.setCellValueFactory(new PropertyValueFactory<PosInstalacion, ImageView>("imagenIniciar"));
        ubicacionInstalador.setCellValueFactory(new PropertyValueFactory<PosInstalacion, String>("ubicacionInstalador"));

        tablaInstalacion.getItems().setAll(insert());
    }

    private Collection<? extends PosInstalacion> insert() {
        this.btnSiguiente.setDisable(true);
        return FXCollections.observableArrayList(
                new PosInstalacion(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, "Inicio de la Instalación", null, null, null, null, ubicacionInstaladorServicio));
    }

    @FXML
    private void InicioInstalacion() {

        ObservableList<PosInstalacion> list = FXCollections.observableArrayList();
        list.add(new PosInstalacion(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, "Comenzando con la Instalación", null, null, null, null, ubicacionInstaladorServicio));
        tablaInstalacion.setItems(list);

        TaskServicio taskServicio = new TaskServicio();
        taskServicio.addPropertyChangeListener(this);
        taskServicio.execute();
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
        ImageView respaldarServicio = new ImageView();
        ImageView instalarServicio = new ImageView();
        ImageView iniciarServicio = new ImageView();

        IniciarServicioTask iniciarServicioTask = new IniciarServicioTask();
        InstalarServicioTask instalarServicioTask = new InstalarServicioTask();

        private ImageView detenerServicio() {
            detenerServicio=info();
            return info();
        }

        private ImageView respaldarServicio() {
            respaldarServicio=info();
            return info();
        }

        private ImageView instalarServicio() {
            try {
                if (instalarServicioTask.instalarServicioUpdateEpos()) {
                    instalarServicio = check();
                    return check();
                } else {
                    instalarServicio = error();
                    return error();
                }
            } catch (Exception e) {
                logger.error("Ocurrio un erro al instalar",e);
                instalarServicio = error();
            }
            instalarServicio = info();
            return info();
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

        @Override
        public Void doInBackground() throws IOException {
            logger.info("Proceso de Actualizacion:  ");
            ObservableList<PosInstalacion> list8 = FXCollections.observableArrayList();
            list8.add(new PosInstalacion(constante.SERVICIO_POS_UPDATE_EPOS, constante.NO_EXISTE, constante.SERVICIO_POS_SERVICIO_DETENIDO_SERVER, detenerServicio(), null, null, null, ubicacionInstaladorServicio));
            tablaInstalacion.refresh();
            tablaInstalacion.setItems(list8);
            /************************************************************************************ Proceso que backupea los 4 servicios **********************************************************************/
            ObservableList<PosInstalacion> list13 = FXCollections.observableArrayList();
            list13.add(new PosInstalacion(constante.SERVICIO_POS_UPDATE_EPOS, constante.NO_EXISTE, constante.SERVICIO_POS_FINALIZADO_BACKUP, this.detenerServicio, respaldarServicio(), null, null, ubicacionInstaladorServicio));
            tablaInstalacion.refresh();
            tablaInstalacion.setItems(list13);
            /******************************************************************************************* Proceso que instala ************************************************************************************/
            ObservableList<PosInstalacion> list15 = FXCollections.observableArrayList();
            list15.add(new PosInstalacion(constante.SERVICIO_POS_UPDATE_EPOS, constante.NO_EXISTE, constante.SERVICIO_POS_INSTALANDO, this.detenerServicio, this.respaldarServicio, loading(), null, ubicacionInstaladorServicio));
            tablaInstalacion.refresh();
            tablaInstalacion.setItems(list15);

            ObservableList<PosInstalacion> list17 = FXCollections.observableArrayList();
            list17.add(new PosInstalacion(constante.SERVICIO_POS_UPDATE_EPOS, constante.NO_EXISTE, constante.SERVICIO_POS_INSTALANDO_SERVER, this.detenerServicio, this.respaldarServicio, instalarServicio(), null, ubicacionInstaladorServicio));
            tablaInstalacion.refresh();
            tablaInstalacion.setItems(list17);

            /******************************************************************************************* Proceso que Inicia los 4 servicios ************************************************************************************/

            ObservableList<PosInstalacion> list20 = FXCollections.observableArrayList();
            list20.add(new PosInstalacion(constante.SERVICIO_POS_UPDATE_EPOS, constante.NO_EXISTE, constante.SERVICIO_POS_INICIANDO_COMPONENTES, this.detenerServicio, this.respaldarServicio, this.instalarServicio, loading(), ubicacionInstaladorServicio));
            tablaInstalacion.refresh();
            tablaInstalacion.setItems(list20);

            ObservableList<PosInstalacion> list21 = FXCollections.observableArrayList();
            list21.add(new PosInstalacion(constante.SERVICIO_POS_UPDATE_EPOS, constante.NO_EXISTE, constante.SERVICIO_POS_INICIADO_COMPONENTE, this.detenerServicio, this.respaldarServicio, this.instalarServicio, iniciarServicio(), ubicacionInstaladorServicio));
            tablaInstalacion.refresh();
            tablaInstalacion.setItems(list21);

            ObservableList<PosInstalacion> list22 = FXCollections.observableArrayList();
            list22.add(new PosInstalacion(constante.SERVICIO_POS_UPDATE_EPOS, utilitario.obtenerEstadoServicio(), constante.SERVICIO_POS_INICIADO_COMPONENTE, this.detenerServicio, this.respaldarServicio, this.instalarServicio, this.iniciarServicio, ubicacionInstaladorServicio));
            tablaInstalacion.refresh();
            tablaInstalacion.setItems(list22);

            btnSiguiente.setDisable(false);
            btnInstalar.setDisable(true);
            btnAtras.setDisable(true);
            tablaInstalacion.refresh();
            return null;
        }

        @Override
        public void done() {
//            fConsultarCDR.getProgressBar().setVisible(false);
//            fConsultarCDR.getBtnaceptar().setEnabled(true);
//            fConsultarCDR.getBtnreporte().setEnabled(true);
        }
    }

    public void siguiente() {
        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        stage.close();
        this.application.instalacionResultadoActualizacion();
    }

    public void volverAtras() {
        Stage stage = (Stage) btnAtras.getScene().getWindow();
        stage.close();
        this.application.instalacionRelacionComponente();
    }
}
