package util;

import org.apache.log4j.Logger;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utilitario {

    Logger logger = Logger.getLogger(Utilitario.class);
    Constantes constante = new Constantes();

    public Utilitario(){}
    public String conocerRutaActualizador(String servicio){ //ELIMINAR
        String detalle;
        String ubicacionActualizador = obtenerRutaServicioInstalarCMD();
        if(ubicacionActualizador!=null) {
            if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_UPDATE_EPOS)) {
                detalle = ubicacionActualizador.substring(0, ubicacionActualizador.indexOf(constante.RUTA_NOMBRE_TEST_IDE_RAIZ));
                detalle = detalle + constante.RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_UPDATE_EPOS_INSTALL;
                return detalle;
            }
        }else{
            return constante.NO_EXISTE;
        }
        return null;
    }

    public String obtenerRutaServicioInstalarBinBat(){
        String rutaActual = obtenerRutaServicioInstalarCMD();
        return (rutaActual!=null)?rutaActual.concat(constante.RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_UPDATE_EPOS_BIN_BAT_INSTALL):null;
    }
    public String obtenerRutaServicioInstalarLibConfig(){
        String rutaActual = obtenerRutaServicioInstalarCMD();
        return (rutaActual!=null)?rutaActual.concat(constante.RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_UPDATE_EPOS_LIB_CONFIG_INSTALL):null;
    }
    public String obtenerRutaServicioInstalar(){
        String rutaActual = obtenerRutaServicioInstalarCMD();
        return (rutaActual!=null)?rutaActual.concat(constante.RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_UPDATE_EPOS_INSTALL):null;
    }

    public String obtenerRutaBackupInstalador(String rutaServicioInstalado) {
        String detalle;
        if(rutaServicioInstalado!=null) {
                detalle = rutaServicioInstalado.substring(0, rutaServicioInstalado.indexOf(constante.SERVICIO_POS_UPDATE_EPOS));
                detalle = detalle + "BACKUP\\"+fechaDelSistema()+"\\"+constante.SERVICIO_POS_UPDATE_EPOS;
                return detalle;
        }
        return null;
    }
    public String fechaDelSistema(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String fecha = dateFormat.format(date);
        String fechaBackup = fecha.substring(0,4)+fecha.substring(5,7)+fecha.substring(8,10);
        return fechaBackup;
    }

    public String conocerRutaInstalador(String servicio) throws IOException {
        String detalle;
        String extraerRutaServicioPOSexe =obtenerRutaServicioInstaladoBin();
        if(extraerRutaServicioPOSexe!=null) {
            if (servicio.equalsIgnoreCase(constante.SERVICIO_POS_SERVER)) {
                detalle = extraerRutaServicioPOSexe.substring(0, extraerRutaServicioPOSexe.indexOf("SERVIDOR"));
                detalle = detalle + "SERVIDOR";
                File file = new File(detalle);
                if (!file.exists()){
                    return constante.NO_EXISTE+constante.SERVICIO_POS_SERVER;
                }
                return detalle;
            }
        }
        return null;
    }

    public String obtenerVersion(String rutaVersion)  {
        try {
           File archivo = new File (rutaVersion+"\\version-number.txt");
            if(archivo.exists()){
                FileReader fileReader = new FileReader (archivo);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String linea;
                StringBuilder detalleVersion = new StringBuilder();
                while((linea=bufferedReader.readLine())!=null) {
                    detalleVersion.append(linea);
                }
                fileReader.close();
                bufferedReader.close();
                if(detalleVersion==null){
                    logger.error("Ocurrio un error al obtener la version: El archivo 'version-number.txt' se encuentra vacio");
                    return constante.ERROR;
                }
                return detalleVersion.substring(detalleVersion.indexOf("=")+1,detalleVersion.length());
            }else{
                logger.error("Ocurrio un error al obtener la version: El archivo 'version-number.txt' no existe");
                return constante.ERROR;
            }
        } catch(IOException e) {
            logger.error("Ocurrio un error al obtener la version: ",e);
            return constante.ERROR;
        }
    }

    public String identificarRutaServicioInstalado(String rptaUbicacionesCMD){
        String[] splited = rptaUbicacionesCMD.split("(?!\1)[\"']");  //("(?!\\1)[\"']");
        for(int i=0 ;i<splited.length;i++){
            if(splited[i].contains(constante.CMD_UPDATE_POS_EXE)){
                return splited[i].substring(0,splited[i].indexOf(constante.CMD_UPDATE_POS_EXE));
            }
        }
        return null;
    }

    public String conocerStatusServicioPOS(String servicio) throws IOException {
        String detalle=null;
            String estadoServicio=ejecutarComandoCMD(constante.CMD_STATUS_SERVICIO_POS_UPDATE_EPOS);
            if(estadoServicio != null){
                if(estadoServicio.contains(constante.SERVICIO_POS_RUNNING)){
                    detalle = constante.SERVICIO_POS_INICIADO;
                }else if(estadoServicio.contains(constante.SERVICIO_POS_STOPPED)){
                    detalle = constante.SERVICIO_POS_DETENIDO;
                }else{
                    logger.error("Ocurrio un error al solicitar el estado del servicio 'SrvWinFE_POS_Update_Epos'.");
                    detalle = constante.SERVICIO_POS_NO_EXISTE;
                }
            }
        return detalle;
    }

    public String obtenerEstadoServicio() throws IOException {
        String detalle=null;
        String estadoServicio=ejecutarComandoCMD(constante.CMD_STATUS_SERVICIO_POS_UPDATE_EPOS);
        if(estadoServicio != null){
            if(estadoServicio.contains(constante.SERVICIO_POS_RUNNING)){
                detalle = constante.SERVICIO_POS_INICIADO;
            }else if(estadoServicio.contains(constante.SERVICIO_POS_STOPPED)){
                detalle = constante.SERVICIO_POS_DETENIDO;
            }else{
                detalle = constante.SERVICIO_POS_NO_EXISTE;
            }
        }
        return detalle;
    }

    public String obtenerRutaServicioInstaladoBin() throws IOException {
        String detalleServicioIntalado = ejecutarComandoCMD(constante.CMD_UBICACION_SERVICIO_UPDATE_POS_PC_SPANISH);
        if (detalleServicioIntalado != null) {
            return identificarRutaServicioInstalado(detalleServicioIntalado);
        } else if (ejecutarComandoCMD(constante.CMD_UBICACION_SERVICIO_UPDATE_POS_PC_ENGLISH) != null) {
            return identificarRutaServicioInstalado(ejecutarComandoCMD(constante.CMD_UBICACION_SERVICIO_UPDATE_POS_PC_ENGLISH));
        } else {
            logger.error("Ocurrio un error al obtener la ruta del servicio instalado: no se encuentra el servicio");
            return null;
        }
    }
    public String obtenerRutaServicioInstaladoRaiz() throws IOException {
        String rutaServicioInstalado = obtenerRutaServicioInstaladoBin();
        return (rutaServicioInstalado!=null)?rutaServicioInstalado.substring(0, rutaServicioInstalado.indexOf("\\bin")):constante.NO_EXISTE;
    }

    public String obtenerRutaServicioInstalarCMD() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "chdir");
        Process process =null;
        try {
            process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            process.destroy();
            return line;
        } catch (Exception e) {
            logger.error("Ocurrio un error al obtener la ruta del servicio a instalar: ",e);
            process.destroy();
            return null;
        }
    }
    public String ejecutarComandoCMD(String command) throws IOException {
        Process process = null;
        BufferedReader bufferedReader=null;
        try {
            StringBuilder respuesta = new StringBuilder();
            logger.info("Se va a ejecutar el comando CMD :"+command);
            process = Runtime.getRuntime().exec(command);
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                respuesta.append(line);
            }
            if(!respuesta.toString().isEmpty()){
                logger.info("Respuesta del comando CMD : "+respuesta.toString());
                process.destroy();
                bufferedReader.close();
                return respuesta.toString();
            }else {
                logger.warn("Respuesta del comando CMD : "+respuesta.toString());
                process.destroy();
                bufferedReader.close();
                return null;
            }
        } catch (Exception e) {
            logger.error("Ocurrio un error al ejecutar el comando CMD :",e);
            process.destroy();
            bufferedReader.close();
            return null;
        }
    }

    public String validarVersion(String versionInstalada, String versionInstalar){

        List<Integer> lstVersionInstaladaInt = new ArrayList<>(Arrays.asList(versionInstalada.split(Pattern.quote(".")))).stream().map(s->Integer.parseInt(s)).collect(Collectors.toList());
        List<Integer> lstVersionInstalarInt = new ArrayList<>(Arrays.asList(versionInstalar.split(Pattern.quote(".")))).stream().map(s->Integer.parseInt(s)).collect(Collectors.toList());

        if(lstVersionInstaladaInt.get(0)<lstVersionInstalarInt.get(0)){
            return constante.MSJ_IAA_ACTUALIZACION;
        }else if(lstVersionInstaladaInt.get(0)>lstVersionInstalarInt.get(0)){
            return constante.MSJ_IAA_VERSION_MAYOR;
        }else{
            if(lstVersionInstaladaInt.get(1)<lstVersionInstalarInt.get(1)){
                return constante.MSJ_IAA_ACTUALIZACION;
            }else if(lstVersionInstaladaInt.get(1)>lstVersionInstalarInt.get(1)) {
                return constante.MSJ_IAA_VERSION_MAYOR;
            }else{
                if(lstVersionInstaladaInt.get(2)<lstVersionInstalarInt.get(2)){
                    return constante.MSJ_IAA_ACTUALIZACION;
                }else if(lstVersionInstaladaInt.get(2)>lstVersionInstalarInt.get(2)) {
                    return constante.MSJ_IAA_VERSION_MAYOR;
                }else{
                    if(lstVersionInstaladaInt.get(3)<lstVersionInstalarInt.get(3)){
                        return constante.MSJ_IAA_ACTUALIZACION;
                    }else if(lstVersionInstaladaInt.get(3)>lstVersionInstalarInt.get(3)) {
                        return constante.MSJ_IAA_VERSION_MAYOR;
                    }else{
                        return constante.MSJ_IAA_VERSION_IGUAL;
                    }
                }
            }
        }

//        for(int i=0;i<lstVersionInstalada.size();i++){
//            for(int j=0;j<lstVersionInstalar.size();j++){
//                if(Integer.parseInt(lstVersionInstalada.get(i))<Integer.parseInt(lstVersionInstalar.get(i))){
//                    return constante.MSJ_IAA_ACTUALIZACION;
//                }
//                if(Integer.parseInt(lstVersionInstalada.get(i))>=Integer.parseInt(lstVersionInstalar.get(i))){
//                    return constante.MSJ_IAA_VERSION_MAYOR_IGUAL;
//                }
//            }
//        }
    }

//    public static List<String> revertirLista(String version){
//       List<String> lstVersion = new ArrayList<>(Arrays.asList(version.split(Pattern.quote("."))));
//       Collections.reverse(lstVersion);
//       return lstVersion;
//    }


//    public static Integer eliminarPuntosVersionToNumero(String version){
//        String[] lstVersionInstalada = version.split(Pattern.quote("."));
//        StringBuilder sb = new StringBuilder();
//        for(int i=0;i<lstVersionInstalada.length;i++){
//            sb.append(lstVersionInstalada[i]);
//        }
//     return Integer.parseInt(sb.toString());
//    }
}
