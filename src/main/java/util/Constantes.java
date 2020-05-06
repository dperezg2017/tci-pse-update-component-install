package util;

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
    public static final String RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_UPDATE_EPOS_INSTALL="\\Componentes\\SrvWinFE_POS_Update_Epos";
    public static final String RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_UPDATE_EPOS_LIB_CONFIG_INSTALL="\\Componentes\\SrvWinFE_POS_Update_Epos\\lib\\config";
    public static final String RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_UPDATE_EPOS_BIN_BAT_INSTALL="\\Componentes\\SrvWinFE_POS_Update_Epos\\bin\\";
    public static final String RUTA_NOMBRE_TEST_IDE_VERSION_COMPONENTE_UPDATE_EPOS_BIN_BAT_FILE_INSTALL="cmd /c Install-UpdateEpos.bat";

    public static final String CMD_VERIFICAR_SERVICIO_WINDOW_UPDATE_EPOS="cmd /C sc queryex type= service state= all | find /i \"SrvWinFE_POS_Update_Epos\""; // buscar servicio window de POS
    public static final String CMD_UBICACION_SERVICIO_UPDATE_POS_PC_ENGLISH="sc qc \"SrvWinFE_POS_Update_Epos\" 5000 | find \"BINARY_PATH_NAME\"";
    public static final String CMD_UBICACION_SERVICIO_UPDATE_POS_PC_SPANISH="sc qc \"SrvWinFE_POS_Update_Epos\" 5000 | find \"NOMBRE_RUTA_BINARIO\"";

    public static final String CMD_UPDATE_POS_EXE="posserver.exe";
    public static final String CMD_NET_STOP_POS_UPDATE_EPOS="net stop SrvWinFE_POS_Update_Epos";
    public static final String CMD_NET_START_POS_UPDATE_EPOS="net start SrvWinFE_POS_Update_Epos";
    public static final String CMD_STATUS_SERVICIO_POS_UPDATE_EPOS="sc query \"SrvWinFE_POS_Update_Epos\"";

    public static final String CMD_ROBOCOPY="ROBOCOPY ";
    //public static final String CMD_MIR=" /MIR ";
//    public static final String CMD_MIR=" /B /E ";
    public static final String CMD_MIR=" /s ";
    public static final String SERVICIO_POS_UPDATE_EPOS="SrvWinFE_POS_Update_Epos";
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
    public static final String SERVICIO_POS_INSTALANDO="Instalando componente";

    public static final String SERVICIO_POS_INSTALANDO_SERVER="Se realizó la instalación del Servicio SrvWinFE_POS_Update_Epos";
    public static final String SERVICIO_POS_ACTUALIZADO_SERVER="Se realizó la actualización del servicio SrvWinFE_POS_Update_Epos";
    public static final String SERVICIO_POS_INICIANDO_COMPONENTES="Iniciando Componente...";
    public static final String SERVICIO_POS_INICIADO_COMPONENTE="Se inició el servicio de manera satisfactoria";
    public static final String SERVICIO_POS_SERVICIO_DETENIDO_SERVER="El Servicio SrvWinFE_POS_Modulo_Servidor esta detenido.";
    public static final String SERVICIO_POS_UPDATE_EPOS_SERVICIO_INICIADO="El Servicio SrvWinFE_POS_Update_Epos esta iniciado.";
    public static final String SERVICIO_POS_UPDATE_EPOS_NO_EXISTE_DETENIDO="El Servicio SrvWinFE_POS_Update_Epos no existe ó esta detenido.";

    public static final String NO_EXISTE="No existe ";
    public static final String ERROR="Error";
    public static final String VACIO="";

    public static final String ICON_LOADING= "view/images/icon/16px/loading.png";
    public static final String ICON_INFO= "view/images/icon/16px/info.png";
    public static final String ICON_ERROR= "view/images/icon/16px/error.png";
    public static final String ICON_WARNING= "view/images/icon/16px/warning.png";
    public static final String ICON_CHECK= "view/images/icon/16px/check.png";

    public static final String MSJ_IAA_INSTALACION= "Para continuar con la Instalación haga clic en el boton 'Siguiente'.";
    public static final String MSJ_IAA_ACTUALIZACION= "Para continuar con la Actualización haga clic en el boton 'Siguiente'.";
    public static final String MSJ_IAA_VERSION_MAYOR= "No se realizara ninguna acción, por que la versión instalada es mayor";
    public static final String MSJ_IAA_VERSION_IGUAL= "No se realizara ninguna acción, por que la versión es igual";
    public static final String MSJ_IAA_VERSION_ERROR= "Ocurrio un error al obtener la versión, comuniquese con el administrador.";

    public static final String MSJ_ARC_FUNCIONALIDAD_UPDATE_EPOS= "Realizar la actualización del ePos.";
}
