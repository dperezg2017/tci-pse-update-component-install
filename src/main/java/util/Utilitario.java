package util;

import org.apache.log4j.Logger;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author: Deyviz Perez
 * @version: 1.0
 * **/
public class Utilitario {

    Logger logger = Logger.getLogger(Utilitario.class);
    Constantes constante = new Constantes();
    public String conocerRutaActualizador(String servicio){
        String detalle=null;
        String ubicacionActualizador = conocerUbicacionDelActualizadorCMD();
        if(ubicacionActualizador!=null) {
            if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_SERVER)) {
                detalle = ubicacionActualizador.substring(0, ubicacionActualizador.indexOf(constante.RUTA_NOMBRE_TEST_IDE_RAIZ));
                detalle = detalle + constante.RUTA_NOMBRE_TEST_IDE_RAIZ_COMPONENTE_SERVER;
                return detalle;
            } else if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_BD)) {
                detalle = ubicacionActualizador.substring(0, ubicacionActualizador.indexOf(constante.RUTA_NOMBRE_TEST_IDE_RAIZ));
                detalle = detalle + constante.RUTA_NOMBRE_TEST_IDE_RAIZ_COMPONENTE_BD;
                return detalle;
            } else if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_CLIENTE)) {
                detalle = ubicacionActualizador.substring(0, ubicacionActualizador.indexOf(constante.RUTA_NOMBRE_TEST_IDE_RAIZ));
                detalle = detalle +  constante.RUTA_NOMBRE_TEST_IDE_RAIZ_COMPONENTE_CLIENTE;
                return detalle;
            } else if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_PRINTMANAGER)) {
                detalle = ubicacionActualizador.substring(0, ubicacionActualizador.indexOf(constante.RUTA_NOMBRE_TEST_IDE_RAIZ));
                String complemento =  constante.RUTA_NOMBRE_TEST_IDE_RAIZ_COMPONENTE_PRINTMANAGER;
                detalle = detalle + complemento;
                return detalle;
            }
        }else{
            return constante.NO_EXISTE;
        }
        return null;
    }

    public String conocerRutaVersionActualizador(String servicio){
        String detalle=null;
        String ubicacionActualizador = conocerUbicacionDelActualizadorCMD();
        if(ubicacionActualizador!=null) {
            if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_SERVER)) {
                logger.info("[ubicacionActualizador]: "+ubicacionActualizador);
                logger.info("[constante.RUTA_NOMBRE_TEST_IDE_RAIZ]: "+constante.RUTA_NOMBRE_TEST_IDE_RAIZ);
                detalle = ubicacionActualizador.substring(0, ubicacionActualizador.indexOf(constante.RUTA_NOMBRE_TEST_IDE_RAIZ));
                detalle = detalle + constante.RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_SERVER;
                logger.info("[conocerRutaVersionActualizador]: "+detalle);
                return detalle;
            }
        }else{
            return constante.NO_EXISTE;
        }
        return null;
    }
//    public String conocerRutaActualizador(String servicio){
//        String detalle=null;
//        String ubicacionActualizador = conocerUbicacionDelActualizadorCMD();
//        if(ubicacionActualizador!=null) {
//            if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_SERVER)) {
//                detalle = ubicacionActualizador.substring(0, ubicacionActualizador.indexOf(constante.RUTA_NOMBRE_TEST_IDE_RAIZ));
//                detalle = detalle + "\\ActualizadorEpos\\Componentes\\SrvWinFE_POS_Modulo_Servidor";
//                detalle.indexOf("\\");
//                return detalle;
//            } else if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_BD)) {
//                detalle = ubicacionActualizador.substring(0, ubicacionActualizador.indexOf("ActualizadorEpos"));
//                detalle = detalle + "\\ActualizadorEpos\\Componentes\\SrvWinFE_POS_Modulo_BD";
//                return detalle;
//            } else if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_CLIENTE)) {
//                detalle = ubicacionActualizador.substring(0, ubicacionActualizador.indexOf("ActualizadorEpos"));
//                detalle = detalle + "\\ActualizadorEpos\\Componentes\\SrvWinFE_POS_Modulo_Cliente";
//                return detalle;
//            } else if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_PRINTMANAGER)) {
//                detalle = ubicacionActualizador.substring(0, ubicacionActualizador.indexOf("ActualizadorEpos"));
//                String complemento = "\\ActualizadorEpos\\Componentes\\SrvWinFE_POS_Plugin_Rep_Impresa";
//               // complemento.substring(complemento.indexOf("\\",complemento.length()));
//                detalle = detalle + complemento;
//                return detalle;
//            }
//        }else{
//            return constante.NO_EXISTE;
//        }
//        return null;
//    }
    public String conocerRutaBackupInstalador(String servicio){
        String detalle=null;
        String extraerRutaServicioPOSexe =extraerRutaServicioPOSexe();
        if(extraerRutaServicioPOSexe!=null) {
            if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_SERVER)) {
                detalle = extraerRutaServicioPOSexe.substring(0, extraerRutaServicioPOSexe.indexOf("SERVIDOR"));
                detalle = detalle + "BACKUP\\"+fechaDelSistema()+"\\SERVIDOR";
//                System.out.println("conocerRutaInstalador SERVER: " + detalle);
                return detalle;

            } else if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_BD)) {
                detalle = extraerRutaServicioPOSexe.substring(0, extraerRutaServicioPOSexe.indexOf("SERVIDOR"));
                detalle = detalle + "BACKUP\\"+fechaDelSistema()+"\\DATABASE";
//                System.out.println("conocerRutaInstalador BD: " + detalle);
                return detalle;
            } else if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_CLIENTE)) {
                detalle = extraerRutaServicioPOSexe.substring(0, extraerRutaServicioPOSexe.indexOf("SERVIDOR"));
                detalle = detalle + "BACKUP\\"+fechaDelSistema()+"\\CLIENTE";
//                System.out.println("conocerRutaInstalador CLIENTE: " + detalle);
                return detalle;
            }
        }

        if(servicio.equalsIgnoreCase(constante.SERVICIO_POS_PRINTMANAGER)){
            String extraerRutaServicioPOSPrintManagerExe=extraerRutaServicioPOSPrintManagerExe();
            if(extraerRutaServicioPOSPrintManagerExe!=null) {
                detalle = extraerRutaServicioPOSPrintManagerExe.substring(0, extraerRutaServicioPOSPrintManagerExe.indexOf("REPRESENTACION-IMPRESA"));
                detalle = detalle + "BACKUP\\"+fechaDelSistema()+"\\REPRESENTACION-IMPRESA";
//                System.out.println("conocerRutaInstalador PRINTMANAGER: " + detalle);
                return detalle;
            }else{
                return constante.NO_EXISTE+constante.SERVICIO_POS_PRINTMANAGER;
            }
        }
        return null;
    }
    public String fechaDelSistema(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        //System.out.println(dateFormat.format(date));
        String fecha = dateFormat.format(date);
        String fechaBackup = fecha.substring(0,4)+fecha.substring(5,7)+fecha.substring(8,10);
        return fechaBackup;
    }
    public String conocerRutaInstalador(String servicio){
        String detalle=null;
        String extraerRutaServicioPOSexe =extraerRutaServicioPOSexe();
        if(extraerRutaServicioPOSexe!=null) {
            if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_SERVER)) {
                detalle = extraerRutaServicioPOSexe.substring(0, extraerRutaServicioPOSexe.indexOf("SERVIDOR"));
                detalle = detalle + "SERVIDOR";

                File file = new File(detalle);
                if (!file.exists()){
                    return constante.NO_EXISTE+constante.SERVICIO_POS_SERVER;
                }
//                System.out.println("conocerRutaInstalador SERVER: " + detalle);
                return detalle;

            } else if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_BD)) {
                detalle = extraerRutaServicioPOSexe.substring(0, extraerRutaServicioPOSexe.indexOf("SERVIDOR"));
                detalle = detalle + "DATABASE";

                File file = new File(detalle);
                if (!file.exists()){
                    return constante.NO_EXISTE+constante.SERVICIO_POS_BD;
                }
//                System.out.println("conocerRutaInstalador BD: " + detalle);
                return detalle;
            } else if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_CLIENTE)) {
                detalle = extraerRutaServicioPOSexe.substring(0, extraerRutaServicioPOSexe.indexOf("SERVIDOR"));
                detalle = detalle + "CLIENTE";

                File file = new File(detalle);
                if (!file.exists()){
                    return constante.NO_EXISTE+constante.SERVICIO_POS_CLIENTE;
                }

//                System.out.println("conocerRutaInstalador CLIENTE: " + detalle);
                return detalle;
            }
        }

        if(servicio.equalsIgnoreCase(constante.SERVICIO_POS_PRINTMANAGER)){
            String extraerRutaServicioPOSPrintManagerExe=extraerRutaServicioPOSPrintManagerExe();
            if(extraerRutaServicioPOSPrintManagerExe!=null) {
                detalle = extraerRutaServicioPOSPrintManagerExe.substring(0, extraerRutaServicioPOSPrintManagerExe.indexOf("REPRESENTACION-IMPRESA"));
                detalle = detalle + "REPRESENTACION-IMPRESA";
//                System.out.println("conocerRutaInstalador PRINTMANAGER: " + detalle);
                return detalle;
            }else{
                return constante.NO_EXISTE+constante.SERVICIO_POS_PRINTMANAGER;
            }
        }
        return null;
    }
    public String versionPOS(String rutaPOS){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File (rutaPOS+"\\version-number.txt");
            if(archivo.exists()){
                fr = new FileReader (archivo);
                br = new BufferedReader(fr);
                String linea;
                StringBuilder sbVersion = new StringBuilder();
                while((linea=br.readLine())!=null) {
                    System.out.println("version POS: " + linea);
                    sbVersion.append(linea);
                }
                if(sbVersion!=null){
                    String version=sbVersion.substring(sbVersion.indexOf("=")+1,sbVersion.length());
                    return version;
                }
            }else{
                return null;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return null;
    }

    public String extraerUbicacionServicioPOSPrintManager(String rptaUbicacionesCMD){
        String[] splited = rptaUbicacionesCMD.split("\\s+");
        String rutaPOSPrintManagerExe = null;
        for(int i=0 ;i<splited.length;i++){
            if(splited[i].contains(constante.CMD_POS_PRINTMANAGER_EXE)){
                int indice = splited[i].toString().indexOf(constante.CMD_POS_PRINTMANAGER_EXE);
                rutaPOSPrintManagerExe = splited[i].toString().substring(0,indice);
//                System.out.println("Ruta POS - PRINTMANAGER: "+rutaPOSPrintManagerExe);
                return rutaPOSPrintManagerExe;
            }
        }
        return rutaPOSPrintManagerExe;
    }
    public String extraerUbicacionServicioPOS(String rptaUbicacionesCMD){
        String[] splited = rptaUbicacionesCMD.split("\\s+");
        String rutaPOSexe = null;
        for(int i=0 ;i<splited.length;i++){
            if(splited[i].contains(constante.CMD_POS_EXE)){
                int indice = splited[i].toString().indexOf(constante.CMD_POS_EXE);
                rutaPOSexe = splited[i].toString().substring(0,indice);
//                System.out.println("Ruta POS: "+rutaPOSexe);
                return rutaPOSexe;
            }
        }
        return rutaPOSexe;
    }
    public String conocerStatusServicioPOS(String servicio){
        String detalle=null;
        if(servicio.equalsIgnoreCase(constante.SERVICIO_POS_SERVER)) {
            String ejecutarComandoCMDBServer=ejecutarComandoCMD(constante.CMD_STATUS_SERVICIO_POS_SERVER);
            if(ejecutarComandoCMDBServer != null){
                if(ejecutarComandoCMDBServer.contains(constante.SERVICIO_POS_RUNNING)){
                    detalle = constante.SERVICIO_POS_INICIADO;
                }else if(ejecutarComandoCMDBServer.contains(constante.SERVICIO_POS_STOPPED)){
                    detalle = constante.SERVICIO_POS_DETENIDO;
                }else{
                    detalle = constante.SERVICIO_POS_NO_EXISTE;
                }
            }

        }else if(servicio.equalsIgnoreCase(constante.SERVICIO_POS_BD)){
            String ejecutarComandoCMDBd =ejecutarComandoCMD(constante.CMD_STATUS_SERVICIO_POS_BD);
            if(ejecutarComandoCMDBd!=null){
                if(ejecutarComandoCMDBd != null){
                    if(ejecutarComandoCMDBd.contains(constante.SERVICIO_POS_RUNNING)){
                        detalle = constante.SERVICIO_POS_INICIADO;
                    }else if(ejecutarComandoCMDBd.contains(constante.SERVICIO_POS_STOPPED)){
                        detalle = constante.SERVICIO_POS_DETENIDO;
                    }else{
                        detalle = constante.SERVICIO_POS_NO_EXISTE;
                    }
                }
            }

        }else if(servicio.equalsIgnoreCase(constante.SERVICIO_POS_CLIENTE)){
            String ejecutarComandoCMDCliente = ejecutarComandoCMD(constante.CMD_STATUS_SERVICIO_POS_CLIENTE);
            if(ejecutarComandoCMDCliente!=null){
                if(ejecutarComandoCMDCliente != null){
                    if(ejecutarComandoCMDCliente.contains(constante.SERVICIO_POS_RUNNING)){
                        detalle = constante.SERVICIO_POS_INICIADO;
                    }else if(ejecutarComandoCMDCliente.contains(constante.SERVICIO_POS_STOPPED)){
                        detalle = constante.SERVICIO_POS_DETENIDO;
                    }else{
                        detalle = constante.SERVICIO_POS_NO_EXISTE;
                    }
                }
            }


        }else if(servicio.equalsIgnoreCase(constante.SERVICIO_POS_PRINTMANAGER)){
            String ejecutarComandoCMDPrintManager=ejecutarComandoCMD(constante.CMD_STATUS_SERVICIO_POS_PRINTMANAGER);
            if(ejecutarComandoCMDPrintManager!=null){
                if(ejecutarComandoCMDPrintManager.contains(constante.SERVICIO_POS_RUNNING)){
                    detalle = constante.SERVICIO_POS_INICIADO;
                }else if(ejecutarComandoCMDPrintManager.contains(constante.SERVICIO_POS_STOPPED)){
                    detalle = constante.SERVICIO_POS_DETENIDO;
                }else{
                    detalle = constante.SERVICIO_POS_NO_EXISTE;
                }
            }
        }

        return detalle;
    }
    public String extraerRutaServicioPOSPrintManagerExe(){
        String detalleServicio=null;
        String detallePrintManagerEN=ejecutarComandoCMD(constante.CMD_UBICACION_SERVICIO_POS_PRINTMANAGER_PC_ENGLISH);
        String detallePrintManagerES=ejecutarComandoCMD(constante.CMD_UBICACION_SERVICIO_POS_PRINTMANAGER_PC_SPANISH);

        if(detallePrintManagerES!=null){
            detalleServicio=extraerUbicacionServicioPOSPrintManager(detallePrintManagerES);
        }else if(detallePrintManagerEN!=null){
            detalleServicio=extraerUbicacionServicioPOSPrintManager(detallePrintManagerES);
        }

        return detalleServicio;
    }

    public String extraerRutaServicioPOSexe(){
        String detalleServicio=null;
        String detallePosEN=ejecutarComandoCMD(constante.CMD_UBICACION_SERVICIO_POS_PC_SPANISH);
        String detallePosES=ejecutarComandoCMD(constante.CMD_UBICACION_SERVICIO_POS_PC_ENGLISH);
        if(detallePosES!=null){
            detalleServicio=extraerUbicacionServicioPOS(detallePosES);
        }else if(detallePosEN!=null){
            detalleServicio=extraerUbicacionServicioPOS(detallePosEN);
        }
        return detalleServicio;
    }

    public String  conocerUbicacionDelActualizadorCMD() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "chdir");
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                System.out.println(line);
            }
            int exitCode = process.waitFor();
//            System.out.println("\nExited with error code : " + exitCode);
//            System.out.println("stringbuilder: " + sb);
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String ejecutarComandoCMD(String command){
        try {
            String line = null;
            StringBuilder rpt = new StringBuilder();
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = in.readLine()) != null) {
                rpt.append(line);
                // System.out.println("system: "+line);
            }
            if(!rpt.toString().isEmpty()){
                return rpt.toString();
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
}
}
