package util;
/**
 * @author: Deyviz Perez
 * @version: 1.0
 * **/
public class Constantes {
    /*dperezc: para el actualizador*/
//    public static final String RUTA_NOMBRE_TEST_IDE_RAIZ="Asistente de Actualización POS";
//    public static final String RUTA_NOMBRE_TEST_IDE_RAIZ="Asistente de Actualizaci";
//    public static final String RUTA_NOMBRE_TEST_IDE_RAIZ_COMPONENTE_PRINTMANAGER="Asistente de Actualización POS\\Componentes\\SrvWinFE_POS_Plugin_Rep_Impresa";
//    public static final String RUTA_NOMBRE_TEST_IDE_RAIZ_COMPONENTE_SERVER="Asistente de Actualización POS\\Componentes\\SrvWinFE_POS_Modulo_Servidor";
//    public static final String RUTA_NOMBRE_TEST_IDE_RAIZ_COMPONENTE_BD="Asistente de Actualización POS\\Componentes\\SrvWinFE_POS_Modulo_BD";
//    public static final String RUTA_NOMBRE_TEST_IDE_RAIZ_COMPONENTE_CLIENTE="Asistente de Actualización POS\\Componentes\\SrvWinFE_POS_Modulo_Cliente";
//    public static final String RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_SERVER="Asistente de Actualización POS\\Componentes\\SrvWinFE_POS_Modulo_Servidor\\lib\\config";

    /*dperezc: test en el IDE*/
    public static final String RUTA_NOMBRE_TEST_IDE_RAIZ="tci-pse-update-component-install";
    public static final String RUTA_NOMBRE_TEST_IDE_RAIZ_COMPONENTE_PRINTMANAGER="tci-pse-update-component-install\\Componentes\\SrvWinFE_POS_Plugin_Rep_Impresa";
    public static final String RUTA_NOMBRE_TEST_IDE_RAIZ_COMPONENTE_SERVER="tci-pse-update-component-install\\Componentes\\SrvWinFE_POS_Modulo_Servidor";
    public static final String RUTA_NOMBRE_TEST_IDE_RAIZ_COMPONENTE_BD="tci-pse-update-component-install\\Componentes\\SrvWinFE_POS_Modulo_BD";
    public static final String RUTA_NOMBRE_TEST_IDE_RAIZ_COMPONENTE_CLIENTE="tci-pse-update-component-install\\Componentes\\SrvWinFE_POS_Modulo_Cliente";
    //public static final String RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_SERVER="tci-pse-update-component-install\\Componentes\\SrvWinFE_POS_Modulo_Servidor\\lib\\config";
    public static final String RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_SERVER="\\Componentes\\SrvWinFE_POS_Modulo_Servidor\\lib\\config";


    public static final String CMD_VERIFICAR_SERVICIO_WINDOW_POS="cmd /C sc queryex type= service state= all | find /i \"SrvWinFE_POS_Modulo_Servidor\""; // buscar servicio window de POS
    public static final String CMD_UBICACION_SERVICIO_POS_PC_ENGLISH="sc qc \"SrvWinFE_POS_Modulo_Servidor\" 5000 | find \"BINARY_PATH_NAME\"";
    public static final String CMD_UBICACION_SERVICIO_POS_PC_SPANISH="sc qc \"SrvWinFE_POS_Modulo_Servidor\" 5000 | find \"NOMBRE_RUTA_BINARIO\"";

    public static final String CMD_UBICACION_SERVICIO_POS_PRINTMANAGER_PC_ENGLISH="sc qc \"SrvWinFE_POS_Plugin_Rep_Impresa\" 5000 | find \"BINARY_PATH_NAME\"";
    public static final String CMD_UBICACION_SERVICIO_POS_PRINTMANAGER_PC_SPANISH="sc qc \"SrvWinFE_POS_Plugin_Rep_Impresa\" 5000 | find \"NOMBRE_RUTA_BINARIO\"";
    public static final String CMD_POS_EXE="posserver.exe";
    public static final String CMD_POS_PRINTMANAGER_EXE="posprintmanager.exe";

    public static final String CMD_NET_STOP_PRINTMANAGER="net stop SrvWinFE_POS_Plugin_Rep_Impresa";
    public static final String CMD_NET_STOP_BD="net stop SrvWinFE_POS_Modulo_BD";
    public static final String CMD_NET_STOP_POS_SERVER="net stop SrvWinFE_POS_Modulo_Servidor";
    public static final String CMD_NET_STOP_CLIENTE="net stop SrvWinFE_POS_Modulo_Cliente";

    public static final String CMD_NET_START_PRINTMANAGER="net start SrvWinFE_POS_Plugin_Rep_Impresa";
    public static final String CMD_NET_START_BD="net start SrvWinFE_POS_Modulo_BD";
    public static final String CMD_NET_START_POS_SERVER="net start SrvWinFE_POS_Modulo_Servidor";
    public static final String CMD_NET_START_CLIENTE="net start SrvWinFE_POS_Modulo_Cliente";

    public static final String RUNAS_NET_STOP_PRINTMANAGER="runas /user:Administrator \"net stop \"SrvWinFE_POS_Plugin_Rep_Impresa\"";  //runas /user:Administrator "net stop \"Service Integration Gateway\""

    public static final String CMD_STATUS_SERVICIO_POS_SERVER="sc query \"SrvWinFE_POS_Modulo_Servidor\"";
    public static final String CMD_STATUS_SERVICIO_POS_BD="sc query \"SrvWinFE_POS_Modulo_BD\"";
    public static final String CMD_STATUS_SERVICIO_POS_CLIENTE="sc query \"SrvWinFE_POS_Modulo_Cliente\"";
    public static final String CMD_STATUS_SERVICIO_POS_PRINTMANAGER="sc query \"SrvWinFE_POS_Plugin_Rep_Impresa\"";

   // public static final String CMD_BACKUP_INSTALADOR_PRINTMANAGER="ROBOCOPY D:\testrespaldar\REPRESENTACION-IMPRESA D:\testrespaldar\backup2 /mir";
    public static final String CMD_ROBOCOPY="ROBOCOPY ";
    //public static final String CMD_MIR=" /MIR ";
    public static final String CMD_MIR=" /B /E ";
    public static final String SERVICIO_POS_UPDATE_EPOS="SrvWinFE_POS_Modulo_Servidor";
    public static final String SERVICIO_POS_SERVER="SrvWinFE_POS_Modulo_Servidor";
    public static final String SERVICIO_POS_BD="SrvWinFE_POS_Modulo_BD";
    public static final String SERVICIO_POS_CLIENTE="SrvWinFE_POS_Modulo_Cliente";
    public static final String SERVICIO_POS_PRINTMANAGER="SrvWinFE_POS_Plugin_Rep_Impresa";

    public static final String SERVICIO_POS_RUNNING="RUNNING";
    public static final String SERVICIO_POS_STOPPED="STOPPED";
    public static final String SERVICIO_POS_INICIADO="Iniciado";
    public static final String SERVICIO_POS_DETENIDO="Detenido";
    public static final String SERVICIO_POS_NO_EXISTE="No Existe";

    public static final String SERVICIO_POS_DETENIENDO_SERVICIO="Deteniendo Servicio..";
    public static final String SERVICIO_POS_REALIZANDO_BACKUP="Realizando Backup...";
    public static final String SERVICIO_POS_FINALIZADO_BACKUP="Se creó el backup de manera satisfactoria.";
    public static final String SERVICIO_POS_ACTUALIZANDO="Actualizando componente";
    public static final String SERVICIO_POS_ACTUALIZADO_PRINTMANAGER="Se realizó la actualización del servicio SrvWinFE_POS_Plugin_Rep_Impresa";
    public static final String SERVICIO_POS_ACTUALIZADO_BD="Se realizó la actualización del servicio Servicio SrvWinFE_POS_Modulo_BD";
    public static final String SERVICIO_POS_ACTUALIZADO_SERVER="Se realizó la actualización del servicio Servicio SrvWinFE_POS_Modulo_Servidor";
    public static final String SERVICIO_POS_ACTUALIZADO_CLIENTE="Se realizó la actualización del servicio Servicio SrvWinFE_POS_Modulo_Cliente";
    public static final String SERVICIO_POS_INICIANDO_COMPONENTES="Iniciando Componente...";
    public static final String SERVICIO_POS_INICIADO_COMPONENTE="Se inició el servicio de manera satisfactoria";

    public static final String SERVICIO_POS_SERVICIO_DETENIDO_PRINTMANAGER="El Servicio SrvWinFE_POS_Plugin_Rep_Impresa esta detenido.";
    public static final String SERVICIO_POS_SERVICIO_DETENIDO_BD="El Servicio SrvWinFE_POS_Modulo_BD esta detenido.";
    public static final String SERVICIO_POS_SERVICIO_DETENIDO_SERVER="El Servicio SrvWinFE_POS_Modulo_Servidor esta detenido.";
    public static final String SERVICIO_POS_SERVICIO_DETENIDO_CLIENTE="El Servicio SrvWinFE_POS_Modulo_Cliente esta detenido.";

    public static final String SERVICIO_POS_SERVICIO_INICIADO_PRINTMANAGER="El Servicio SrvWinFE_POS_Plugin_Rep_Impresa esta iniciado.";
    public static final String SERVICIO_POS_SERVICIO_INICIADO_BD="El Servicio SrvWinFE_POS_Modulo_BD esta iniciado.";
    public static final String SERVICIO_POS_SERVICIO_INICIADO_SERVER="El Servicio SrvWinFE_POS_Modulo_Servidor esta iniciado.";
    public static final String SERVICIO_POS_SERVICIO_INICIADO_CLIENTE="El Servicio SrvWinFE_POS_Modulo_Cliente esta iniciado.";

    public static final String SERVICIO_POS_SERVICIO_NO_EXISTE_DETENIDO_PRINTMANAGER="El Servicio SrvWinFE_POS_Plugin_Rep_Impresa no existe ó esta detenido.";
    public static final String SERVICIO_POS_SERVICIO_NO_EXISTE_DETENIDO_BD="El Servicio SrvWinFE_POS_Modulo_BD no existe ó esta detenido.";
    public static final String SERVICIO_POS_SERVICIO_NO_EXISTE_DETENIDO_SERVER="El Servicio SrvWinFE_POS_Modulo_Servidor no existe ó esta detenido.";
    public static final String SERVICIO_POS_SERVICIO_NO_EXISTE_DETENIDO_CLIENTE="El Servicio SrvWinFE_POS_Modulo_Cliente no existe ó esta detenido.";

    public static final String NO_EXISTE="No existe ";

    public static final String ICON_LOADING= "view/images/icon/16px/loading.png";
    public static final String ICON_INFO= "view/images/icon/16px/info.png";
    public static final String ICON_ERROR= "view/images/icon/16px/error.png";
    public static final String ICON_WARNING= "view/images/icon/16px/warning.png";
    public static final String ICON_CHECK= "view/images/icon/16px/check.png";

}
