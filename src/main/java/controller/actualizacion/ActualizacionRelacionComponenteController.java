package controller.actualizacion;

import controller.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;

import model.Pos;
import util.Constantes;
import util.Utilitario;
import org.apache.log4j.Logger;

public class ActualizacionRelacionComponenteController {

    Logger logger = Logger.getLogger(ActualizacionRelacionComponenteController.class);
    Constantes constante = new Constantes();

    @FXML
    private Button btnProcesoActualizacion;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnAtras;

    @FXML
    private TableView<Pos> tablaRelacion;

    @FXML
    TableColumn<Pos, String> descripcion = new TableColumn<>("descripcion");

    @FXML
    TableColumn<Pos, String> estado = new TableColumn<>("estado");

    @FXML
    TableColumn<Pos, String> rutaServicioInstalado = new TableColumn<>("ubicacion");

    @FXML
    TableColumn<Pos, String> multiemisor = new TableColumn<>("multiemisor");

    @FXML
    TableColumn<Pos, String> funcionalidad = new TableColumn<>("funcionalidad");

    @FXML
    TableColumn<Pos, String> rutaServicioInstalar = new TableColumn<>("rutaServicioInstalar");

    private Main application;

    Utilitario utilitario = new Utilitario();

    public void setApp(Main application) throws IOException {
        this.application = application;

        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        rutaServicioInstalado.setCellValueFactory(new PropertyValueFactory<>("rutaServicioInstalado"));
        multiemisor.setCellValueFactory(new PropertyValueFactory<>("multiemisor"));
        funcionalidad.setCellValueFactory(new PropertyValueFactory<>("funcionalidad"));
        rutaServicioInstalar.setCellValueFactory(new PropertyValueFactory<>("rutaServicioInstalar"));
        tablaRelacion.getItems().setAll(insert());
    }

    private Collection<? extends Pos> insert() throws IOException {

        String rutaServicioInstalado = utilitario.obtenerRutaServicioInstaladoRaiz();
        String rutaServicioInstalar = utilitario.obtenerRutaServicioInstalar();;
        String estadoPosServer = utilitario.conocerStatusServicioPOS(constante.CMD_STATUS_SERVICIO_POS_UPDATE_EPOS);
        return FXCollections.observableArrayList(
                new Pos(constante.SERVICIO_POS_UPDATE_EPOS, estadoPosServer, rutaServicioInstalado,"No",constante.MSJ_ARC_FUNCIONALIDAD_UPDATE_EPOS, rutaServicioInstalar));
    }

    public void irActualizacionProcesoActualizacion() {
        Stage stage = (Stage) btnProcesoActualizacion.getScene().getWindow();
        stage.close();
        this.application.actualizacionProcesoActualizacion();
    }

    public void cancelar() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public void volverAtras() {
        Stage stage = (Stage) btnAtras.getScene().getWindow();
        stage.close();
        this.application.inicioAsistenteActualizacion();
    }
}
