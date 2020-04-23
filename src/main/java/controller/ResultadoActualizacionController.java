package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import util.Constantes;
import util.Utilitario;

/**
 * @author: Deyviz Perez
 * @version: 1.0
 * @detail:  Controlador, de la final pagina, donde se puede verificar log de la actualizacion
 * **/
public class ResultadoActualizacionController {

    Constantes constante = new Constantes();

    @FXML
    private Button btnExit;


    private Main application;

    @FXML
    private TextArea txtAreaResultado;

    Utilitario utilitario = new Utilitario();

    /* Status de los servicios window POS*/
    String estadoPosPrintManager=utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_PRINTMANAGER);
    String estadoPosServer=utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_SERVER);
    String estadoPosBd=utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_BD);
    String estadoPosCliente=utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_CLIENTE);

    public void setApp(Main application) {

        this.application = application;
        txtAreaResultado.setEditable(false);
        //txtAreaResultado.setDisable(true);
        txtAreaResultado.setWrapText(true );
        txtAreaResultado.appendText("La actualización se ha completado con exito.");
        txtAreaResultado.appendText("\n");
        txtAreaResultado.appendText("Detalle de la actualización:");
        txtAreaResultado.appendText("\n");
        String resultadoEstadoPosPrintManager=(estadoPosPrintManager.equalsIgnoreCase(constante.SERVICIO_POS_INICIADO))?constante.SERVICIO_POS_SERVICIO_INICIADO_PRINTMANAGER:constante.SERVICIO_POS_SERVICIO_NO_EXISTE_DETENIDO_PRINTMANAGER;
        txtAreaResultado.appendText(resultadoEstadoPosPrintManager);
        txtAreaResultado.appendText("\n");
        String resultadoEstadoPosServer=(estadoPosServer.equalsIgnoreCase(constante.SERVICIO_POS_INICIADO))?constante.SERVICIO_POS_SERVICIO_INICIADO_SERVER:constante.SERVICIO_POS_SERVICIO_NO_EXISTE_DETENIDO_SERVER;
        txtAreaResultado.appendText(resultadoEstadoPosServer);
        txtAreaResultado.appendText("\n");
        String resultadoEstadoPosBd=(estadoPosBd.equalsIgnoreCase(constante.SERVICIO_POS_INICIADO))?constante.SERVICIO_POS_SERVICIO_INICIADO_BD:constante.SERVICIO_POS_SERVICIO_NO_EXISTE_DETENIDO_BD;
        txtAreaResultado.appendText(resultadoEstadoPosBd);
        txtAreaResultado.appendText("\n");
        String resultadoEstadoPosCliente=(estadoPosCliente.equalsIgnoreCase(constante.SERVICIO_POS_INICIADO))?constante.SERVICIO_POS_SERVICIO_INICIADO_CLIENTE:constante.SERVICIO_POS_SERVICIO_NO_EXISTE_DETENIDO_CLIENTE;
        txtAreaResultado.appendText(resultadoEstadoPosCliente);
        txtAreaResultado.appendText("\n");

    }



    public void Exit_button() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }




}
