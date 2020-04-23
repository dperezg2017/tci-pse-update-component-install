package controller.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import util.Constantes;
import java.util.Date;
import util.Utilitario;

/**
 * @author: Deyviz Perez
 * @version: 1.0
 * **/
public class RespaldarServicioTask {

    Utilitario utilitario = new Utilitario();
    Constantes constante = new Constantes();


    String ubicacionInstaladorClienteCONF = utilitario.conocerRutaInstalador(constante.SERVICIO_POS_CLIENTE) + "\\conf";
    String ubicacionInstaladorClienteLIB = utilitario.conocerRutaInstalador(constante.SERVICIO_POS_CLIENTE)+ "\\lib";

    String ubicacionInstaladorClienteBackupCONF = utilitario.conocerRutaBackupInstalador(constante.SERVICIO_POS_CLIENTE);
    String ubicacionInstaladorClienteBackupLIB = utilitario.conocerRutaBackupInstalador(constante.SERVICIO_POS_CLIENTE);


    String ubicacionInstaladorBdCONF = utilitario.conocerRutaInstalador(constante.SERVICIO_POS_BD) + "\\conf";
    String ubicacionInstaladorBdLIB = utilitario.conocerRutaInstalador(constante.SERVICIO_POS_BD) + "\\lib";

    String ubicacionInstaladorBdBackupCONF = utilitario.conocerRutaBackupInstalador(constante.SERVICIO_POS_BD);
    String ubicacionInstaladorBdBackupLIB = utilitario.conocerRutaBackupInstalador(constante.SERVICIO_POS_BD);


    String ubicacionInstaladorServidorCONF = utilitario.conocerRutaInstalador(constante.SERVICIO_POS_SERVER) + "\\conf";
    String ubicacionInstaladorServidorLIB = utilitario.conocerRutaInstalador(constante.SERVICIO_POS_SERVER) + "\\lib";

    String ubicacionInstaladorServidorBackupCONF = utilitario.conocerRutaBackupInstalador(constante.SERVICIO_POS_SERVER);
    String ubicacionInstaladorServidorBackupLIB = utilitario.conocerRutaBackupInstalador(constante.SERVICIO_POS_SERVER);

    String ubicacionInstaladorPrintManagerCONF = utilitario.conocerRutaInstalador(constante.SERVICIO_POS_PRINTMANAGER) + "\\conf";
    String ubicacionInstaladorPrintManagerLIB = utilitario.conocerRutaInstalador(constante.SERVICIO_POS_PRINTMANAGER) + "\\lib";

    String ubicacionInstaladorPrintManagerBackupCONF = utilitario.conocerRutaBackupInstalador(constante.SERVICIO_POS_PRINTMANAGER);
    String ubicacionInstaladorPrintManagerBackupLIB = utilitario.conocerRutaBackupInstalador(constante.SERVICIO_POS_PRINTMANAGER);

    public Boolean respaldarPrintmanager() {
        try {
            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY  + "\""+ubicacionInstaladorPrintManagerCONF + "\""+" " +"\""+ ubicacionInstaladorPrintManagerBackupCONF +  "\\conf" + "\""+constante.CMD_MIR);
            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY  + "\""+ ubicacionInstaladorPrintManagerLIB + "\""+" " +"\""+ ubicacionInstaladorPrintManagerBackupLIB  +  "\\lib" + "\""+ constante.CMD_MIR);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean respaldarServidorPos() {
        try {
//            System.out.println("respaldarServidorPos: " + constante.CMD_ROBOCOPY  + "\""+ ubicacionInstaladorPrintManagerCONF + "\""+" " +"\""+ ubicacionInstaladorPrintManagerBackupCONF + "\\" + fechaDelSistema() + "\\conf" + "\""+ constante.CMD_MIR);
//            System.out.println("respaldarServidorPos: " + constante.CMD_ROBOCOPY  + "\""+ ubicacionInstaladorPrintManagerLIB  + "\""+" " +"\""+ ubicacionInstaladorPrintManagerBackupLIB + "\\" + fechaDelSistema() + "\\lib" + "\""+ constante.CMD_MIR);

            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY  + "\""+ ubicacionInstaladorServidorCONF  + "\""+" " +"\""+ ubicacionInstaladorServidorBackupCONF + "\\conf" + "\""+ constante.CMD_MIR);
            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY  + "\""+ ubicacionInstaladorServidorLIB  + "\""+" " +"\""+ ubicacionInstaladorServidorBackupLIB + "\\lib" + "\""+ constante.CMD_MIR);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean respaldarServidorBD() {
        try {
//            System.out.println("respaldarServidorBD: " + constante.CMD_ROBOCOPY  + "\""+ ubicacionInstaladorBdCONF  + "\""+" " +"\""+  ubicacionInstaladorBdBackupCONF + "\\" + fechaDelSistema() + "\\conf" + "\""+ constante.CMD_MIR);
//            System.out.println("respaldarServidorBD: " + constante.CMD_ROBOCOPY  + "\""+ ubicacionInstaladorBdLIB  + "\""+" " +"\""+  ubicacionInstaladorBdBackupLIB + "\\" + fechaDelSistema() + "\\lib" + "\""+ constante.CMD_MIR);

            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY  + "\""+ ubicacionInstaladorBdCONF  + "\""+" " +"\""+  ubicacionInstaladorBdBackupCONF +  "\\conf" + "\""+ constante.CMD_MIR);
            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY  + "\""+ ubicacionInstaladorBdLIB  + "\""+" " +"\""+  ubicacionInstaladorBdBackupLIB +  "\\lib" + "\""+ constante.CMD_MIR);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public Boolean respaldarServidorCliente(){
        try {
//            System.out.println("respaldarServidorCliente: "+ constante.CMD_ROBOCOPY  + "\""+ubicacionInstaladorClienteCONF + "\""+" " +"\""+ ubicacionInstaladorClienteBackupCONF+"\\"+fechaDelSistema()+"\\conf"+ "\""+constante.CMD_MIR);
//            System.out.println("respaldarServidorCliente: "+ constante.CMD_ROBOCOPY  + "\""+ubicacionInstaladorClienteLIB + "\""+" " +"\""+ ubicacionInstaladorClienteBackupLIB+"\\"+fechaDelSistema()+"\\lib"+ "\""+constante.CMD_MIR);

            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY  + "\""+ubicacionInstaladorClienteCONF + "\""+" " +"\""+ ubicacionInstaladorClienteBackupCONF+  "\\conf"+ "\""+constante.CMD_MIR);
            utilitario.ejecutarComandoCMD(constante.CMD_ROBOCOPY  + "\""+ubicacionInstaladorClienteLIB  + "\""+" " +"\""+ ubicacionInstaladorClienteBackupLIB +  "\\lib"+ "\""+constante.CMD_MIR);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String fechaDelSistema(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        //System.out.println(dateFormat.format(date));
        String fecha = dateFormat.format(date);
        String fechaBackup = fecha.substring(0,4)+fecha.substring(5,7)+fecha.substring(8,9);
        return fechaBackup;
    }



}
