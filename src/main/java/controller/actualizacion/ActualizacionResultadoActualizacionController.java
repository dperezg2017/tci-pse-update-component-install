package controller.actualizacion;


import controller.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import util.Constantes;
import util.Utilitario;

import java.io.IOException;

public class ActualizacionResultadoActualizacionController {

    Constantes constante = new Constantes();

    @FXML
    private Button btnExit;

    private Main application;

    @FXML
    private TextArea txtAreaResultado;

    Utilitario utilitario = new Utilitario();
    String estadoPosServer=utilitario.conocerStatusServicioPOS(constante.SERVICIO_POS_SERVER);

    public ActualizacionResultadoActualizacionController() throws IOException {
    }

    public void setApp(Main application) {
        this.application = application;
        txtAreaResultado.setEditable(false);
        txtAreaResultado.setWrapText(true );
        txtAreaResultado.appendText("La actualización se ha completado");
        txtAreaResultado.appendText("\n");
        String resultadoEstadoPosServer=(estadoPosServer.equalsIgnoreCase(constante.SERVICIO_POS_INICIADO))?constante.SERVICIO_POS_UPDATE_EPOS_SERVICIO_INICIADO:constante.SERVICIO_POS_UPDATE_EPOS_NO_EXISTE_DETENIDO;
        txtAreaResultado.appendText(resultadoEstadoPosServer);
        txtAreaResultado.appendText("\n");
    }

    public void Exit_button() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }




}
