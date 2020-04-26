package controller.task;
import util.Constantes;
import util.Utilitario;

import java.io.IOException;

/**
 * @author: Deyviz Perez
 * @version: 1.0
 * **/
public class ActualizarServicioTask {

    Utilitario utilitario = new Utilitario();
    Constantes constante = new Constantes();

    /* Ubicacion del Actualizador POS*/
    String ubicacionActualizadorServidor=utilitario.conocerRutaActualizador(constante.SERVICIO_POS_SERVER);
    String ubicacionActualizadorBd=utilitario.conocerRutaActualizador(constante.SERVICIO_POS_BD);
    String ubicacionActualizadorCliente=utilitario.conocerRutaActualizador(constante.SERVICIO_POS_CLIENTE);
    String ubicacionActualizadorPrintManager=utilitario.conocerRutaActualizador(constante.SERVICIO_POS_PRINTMANAGER);

    /* Ubicacion del Instalador POS*/
    String ubicacionInstaladorServidor=utilitario.conocerRutaInstalador(constante.SERVICIO_POS_SERVER);
    String ubicacionInstaladorBd=utilitario.conocerRutaInstalador(constante.SERVICIO_POS_BD);
    String ubicacionInstaladorCliente=utilitario.conocerRutaInstalador(constante.SERVICIO_POS_CLIENTE);
    String ubicacionInstaladorPrintManager=utilitario.conocerRutaInstalador(constante.SERVICIO_POS_PRINTMANAGER);

    public ActualizarServicioTask() throws IOException {
    }


    public Boolean actualizarPrintmanager() {
        try {
            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY + "\""+ubicacionActualizadorPrintManager + "\""+" " +"\""+  ubicacionInstaladorPrintManager  + "\""+ constante.CMD_MIR);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean actualizarServidorPos() {
        try {
     //       System.out.println("actualizarServidorPos: " + constante.CMD_ROBOCOPY + "\""+ubicacionActualizadorServidor + "\""+" " +"\""+ ubicacionInstaladorServidor + "\""+ constante.CMD_MIR);

            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY + "\"" +ubicacionActualizadorServidor + "\""+" " +"\""+ ubicacionInstaladorServidor  + "\""+ constante.CMD_MIR);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean actualizarServidorBD() {
        try {
         //   System.out.println("actualizarServidorBD: " + constante.CMD_ROBOCOPY + ubicacionActualizadorBd+ "\""+" " +"\""+ubicacionInstaladorBd + "\""+ constante.CMD_MIR);

            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY + ubicacionActualizadorBd + "\""+" " +"\""+ ubicacionInstaladorBd  + "\""+ constante.CMD_MIR);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public Boolean actualizarServidorCliente(){
        try {
           // System.out.println("actualizarServidorCliente: " + constante.CMD_ROBOCOPY + ubicacionActualizadorCliente + "\""+" " +"\""+ ubicacionInstaladorCliente + "\""+ constante.CMD_MIR);

            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY + ubicacionActualizadorCliente + "\""+" " +"\""+ ubicacionInstaladorCliente  + "\""+ constante.CMD_MIR);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }




}
