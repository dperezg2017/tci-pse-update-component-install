package model;

public class Pos {

    String descripcion;
    String estado;
    String rutaServicioInstalado;
    String multiemisor;
    String funcionalidad;
    String rutaServicioInstalar;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRutaServicioInstalado() {
        return rutaServicioInstalado;
    }

    public void setRutaServicioInstalado(String rutaServicioInstalado) {
        this.rutaServicioInstalado = rutaServicioInstalado;
    }

    public String getFuncionalidad() {
        return funcionalidad;
    }

    public void setFuncionalidad(String funcionalidad) {
        this.funcionalidad = funcionalidad;
    }

    public String getRutaServicioInstalar() {
        return rutaServicioInstalar;
    }

    public void setRutaServicioInstalar(String rutaServicioInstalar) {
        this.rutaServicioInstalar = rutaServicioInstalar;
    }

    public String getMultiemisor() {
        return multiemisor;
    }

    public void setMultiemisor(String multiemisor) {
        this.multiemisor = multiemisor;
    }

    public Pos(String descripcion, String estado, String rutaServicioInstalado, String multiemisor, String funcionalidad, String rutaServicioInstalar) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.rutaServicioInstalado = rutaServicioInstalado;
        this.multiemisor = multiemisor;
        this.funcionalidad = funcionalidad;
        this.rutaServicioInstalar = rutaServicioInstalar;
    }
}
