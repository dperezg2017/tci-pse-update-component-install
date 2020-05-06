package controller.instalacion;

import controller.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Pos;
import org.apache.log4j.Logger;
import util.Constantes;
import util.Utilitario;

import java.io.IOException;
import java.util.Collection;

public class InstalacionRelacionComponenteController {

    Logger logger = Logger.getLogger(InstalacionRelacionComponenteController.class);
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

    public void setApp(Main application) {
        this.application = application;

        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        rutaServicioInstalado.setCellValueFactory(new PropertyValueFactory<>("rutaServicioInstalado"));
        multiemisor.setCellValueFactory(new PropertyValueFactory<>("multiemisor"));
        funcionalidad.setCellValueFactory(new PropertyValueFactory<>("funcionalidad"));
        rutaServicioInstalar.setCellValueFactory(new PropertyValueFactory<>("rutaServicioInstalar"));
        tablaRelacion.getItems().setAll(insert());
    }
    private Collection<? extends Pos> insert() {

        String rutaServicioInstalar = utilitario.obtenerRutaServicioInstalar();;
        return FXCollections.observableArrayList(
        new Pos(constante.SERVICIO_POS_UPDATE_EPOS, constante.NO_EXISTE, constante.NO_EXISTE,"No",constante.MSJ_ARC_FUNCIONALIDAD_UPDATE_EPOS, rutaServicioInstalar));
    }

    public void irInstalacionProcesoActualizacion() {
        Stage stage = (Stage) btnProcesoActualizacion.getScene().getWindow();
        stage.close();
        this.application.instalacionProcesoActualizacion();
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
