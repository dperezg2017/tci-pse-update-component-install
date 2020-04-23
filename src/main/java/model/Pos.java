package model;
/**
 * @author: Deyviz Perez
 * @version: 1.0
 * **/
public class Pos {

    String descripcion;
    String estado;
    String ubicacion;
    String ubicacionActualizador;

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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUbicacionActualizador() {
        return ubicacionActualizador;
    }

    public void setUbicacionActualizador(String ubicacionActualizador) {
        this.ubicacionActualizador = ubicacionActualizador;
    }

    public Pos(String descripcion, String estado, String ubicacion, String ubicacionActualizador) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.ubicacionActualizador = ubicacionActualizador;
    }
}
