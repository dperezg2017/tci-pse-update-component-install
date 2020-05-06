package controller;

import controller.actualizacion.ActualizacionProcesoActualizacionController;
import controller.actualizacion.ActualizacionRelacionComponenteController;
import controller.actualizacion.ActualizacionResultadoActualizacionController;
import controller.instalacion.InstalacionProcesoActualizacionController;
import controller.instalacion.InstalacionRelacionComponenteController;
import controller.instalacion.InstalacionResultadoActualizacionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

public class Main extends Application implements Serializable {

    static Logger logger = Logger.getLogger(Main.class);
    private Parent root;

    public Main(){
        try {
            URL url = Loader.getResource("log4j.properties");
            PropertyConfigurator.configure(url);

            logger.info("Inicio del Actualizador - ePos");
        }catch (Exception e){
            logger.error("Ocurrio un error al iniciar el cargar la configuracion de LOG: ",e);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            inicioAsistenteActualizacion();
        } catch(Exception e) {
            logger.error("Ocurrio un error al iniciar la primera pantalla: ",e);
        }
    }

    public void inicioAsistenteActualizacion() {
        FXMLLoader loader;
        try {
            loader = new_stage2("/view/InicioAsistenteActualizacion.fxml");
            InicioAsistenteActualizacionController inicioAsistenteActualizacionController = loader.getController();
            inicioAsistenteActualizacionController.setApp(this);
        } catch (Exception e) {
            logger.error("Ocurrio un error al cargar la pantalla de 'inicio asistente actualizacion' :",e);
        }
    }

    public void actualizacionRelacionComponente() {
        FXMLLoader loader;
        try {
            loader = new_stage2("/view/actualizacion/ActualizacionRelacionComponente.fxml");
                ActualizacionRelacionComponenteController actualizacionRelacionComponenteController = loader.getController();
            actualizacionRelacionComponenteController.setApp(this);
        } catch (Exception e) {
            logger.error("Ocurrio un error al cargar la pantalla de 'actualizacion relacion componente' :",e);
        }
    }
    public void instalacionRelacionComponente() {
        FXMLLoader loader;
        try {
            loader = new_stage2("/view/instalacion/InstalacionRelacionComponente.fxml");
            InstalacionRelacionComponenteController instalacionRelacionComponenteController = loader.getController();
            instalacionRelacionComponenteController.setApp(this);
        } catch (Exception e) {
            logger.error("Ocurrio un error al cargar la pantalla de 'instalacion relacion componente' :",e);
            logger.error(e);
        }
    }

    public void actualizacionProcesoActualizacion() {
        FXMLLoader loader;
        try {
            loader = new_stage2("/view/actualizacion/ActualizacionProcesoActualizacion.fxml");
            ActualizacionProcesoActualizacionController actualizacionProcesoActualizacionController = loader.getController();
            actualizacionProcesoActualizacionController.setApp(this);
        } catch (Exception e) {
            logger.error("Ocurrio un error al cargar la pantalla de 'actualizacion proceso actualizacion' :",e);
            logger.error(e);
        }
    }

    public void instalacionProcesoActualizacion() {
        FXMLLoader loader;
        try {
            loader = new_stage2("/view/instalacion/InstalacionProcesoActualizacion.fxml");
            InstalacionProcesoActualizacionController instalacionProcesoActualizacionController = loader.getController();
            instalacionProcesoActualizacionController.setApp(this);
        } catch (Exception e) {
            logger.error("Ocurrio un error al cargar la pantalla de 'instalacion proceso actualizacion' :",e);
            logger.error(e);
        }
    }

    public void actualiacionResultadoActualizacion() {
        FXMLLoader loader;
        try {
            loader = new_stage2("/view/actualizacion/ActualizacionResultadoActualizacion.fxml");
            ActualizacionResultadoActualizacionController actualizacionResultadoActualizacionController = loader.getController();
            actualizacionResultadoActualizacionController.setApp(this);
        } catch (Exception e) {
            logger.error("Ocurrio un error al cargar la pantalla de 'actualizacion resultado actualizacion' :",e);
            logger.error(e);
        }
    }

    public void instalacionResultadoActualizacion() {
        FXMLLoader loader;
        try {
            loader = new_stage2("/view/instalacion/InstalacionResultadoActualizacion.fxml");
            InstalacionResultadoActualizacionController instalacionResultadoActualizacionController = loader.getController();
            instalacionResultadoActualizacionController.setApp(this);
        } catch (Exception e) {
            logger.error("Ocurrio un error al cargar la pantalla de 'instalacion resultado actualizacion' :",e);
            logger.error(e);
        }
    }


    public FXMLLoader new_stage2(String fxml) throws Exception {
        Stage stage = new Stage();
        stage.setTitle("copyright (©) - Transporte Confidencial de Información");
          FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        return loader;
    }


    public static void main(String[] args) throws IOException {
        launch(args);
//        Properties props = new Properties();
//        props.load(Main.class.getResourceAsStream("../resource/log4j.properties"));
//        PropertyConfigurator.configure(props);
    }
}
