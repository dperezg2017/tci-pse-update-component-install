package controller;

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

/**
 * @author: Deyviz Perez
 * @version: 1.0
 * @detail:  Clase donde se inicia el sistema
 * **/
public class Main extends Application implements Serializable {

    static Logger logger = Logger.getLogger(Main.class);
    private static Main mainInstance;
    private Parent root;


    public Main() throws ClassNotFoundException {
        try {
//        Properties props = new Properties();
//        props.load(getClass().getResourceAsStream("../resource/log4j.properties"));
//        PropertyConfigurator.configure(props);
            URL url = Loader.getResource("log4j.properties");
            PropertyConfigurator.configure(url);

            logger.info("Inicio del Actualizador - ePos");
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
    }
    public static Main getInstance() {
        return mainInstance;
    }


    @Override
    public void start(Stage primaryStage) {
        try {
            inicioAsistenteActualizacion();
        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    public void inicioAsistenteActualizacion() {
        FXMLLoader loader;
        try {
            //InicioAsistenteActualizacionController inicioAsistenteActualizacionController = new InicioAsistenteActualizacionController();
           // loader = new FXMLLoader(getClass().getResource("/view/inicioAsistenteActualizacion.fxml"));
            loader = new_stage2("/view/inicioAsistenteActualizacion.fxml");
          //  loader.setController(inicioAsistenteActualizacionController);
 //            FXMLLoader loader = new FXMLLoader(InicioAsistenteActualizacionController.class.getResource("view/inicioAsistenteActualizacion.fxml"));
//            loader = new_stage2("inicioAsistenteActualizacion.fxml");
            InicioAsistenteActualizacionController inicioAsistenteActualizacionController = loader.getController();
            inicioAsistenteActualizacionController.setApp(this);
        } catch (Exception e) {
            logger.error("Error:"+ e);
            logger.error("Error Cause:"+ e.getCause());
            logger.error("Error Message:"+ e.getMessage());
            e.printStackTrace();
        }
    }

    public void procesoActualizacion() {
        FXMLLoader loader;
        try {
//            loader = new_stage("view/procesoActualizacion.fxml");
//            URL url = Loader.getResource("procesoActualizacion.fxml");
//            loader = new_stage(url);
            loader = new_stage2("/view/procesoActualizacion.fxml");
            ProcesoActualizacionController procesoActualizacionController = loader.getController();
            procesoActualizacionController.setApp(this);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    public void relacionComponente() {
        FXMLLoader loader;
        try {
//            loader = new_stage("view/relacionComponente.fxml");
//            URL url = Loader.getResource("relacionComponente.fxml");
//            loader = new_stage(url);
            loader = new_stage2("/view/relacionComponente.fxml");
            RelacionComponenteController relacionComponenteController = loader.getController();
            relacionComponenteController.setApp(this);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    public void resultadoActualizacion() {
        FXMLLoader loader;
        try {
//            loader = new_stage("view/resultadoActualizacion.fxml");
//            URL url = Loader.getResource("resultadoActualizacion.fxml");
//            loader = new_stage(url);
            loader = new_stage2("/view/resultadoActualizacion.fxml");
            ResultadoActualizacionController resultadoActualizacionController = loader.getController();
            resultadoActualizacionController.setApp(this);
           logger.info("Fin del Actualizador - ePos");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }


//    public FXMLLoader new_stage(String fxml) throws Exception {
public FXMLLoader new_stage(URL fxml) throws Exception {
        Stage stage = new Stage();
        stage.setTitle("copyright (©) - Transporte Confidencial de Información");
        //FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        FXMLLoader loader = new FXMLLoader(fxml);
        root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();
//        stage.setOnCloseRequest( event -> {try {
//            this.getSystem().serialize();
//        } catch (IOException e) {
//            e.printStackTrace();
//        };} );
        return loader;
    }

    public FXMLLoader new_stage2(String fxml) throws Exception {
        Stage stage = new Stage();
        stage.setTitle("copyright (©) - Transporte Confidencial de Información");
        //FXMLLoader loader = new FXMLLoader(InicioAsistenteActualizacionController.class.getClassLoader().getResource(fxml));
          FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();
//        stage.setOnCloseRequest( event -> {try {
//            this.getSystem().serialize();
//        } catch (IOException e) {
//            e.printStackTrace();
//        };} );
        return loader;
    }


    public static void main(String[] args) throws IOException {
        launch(args);
//        Properties props = new Properties();
//        props.load(Main.class.getResourceAsStream("../resource/log4j.properties"));
//        PropertyConfigurator.configure(props);
    }
}
