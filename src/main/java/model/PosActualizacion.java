package model;

import javafx.scene.image.ImageView;

public class PosActualizacion {

    String nombre;
    String estado;
    String estadoProceso;
    ImageView imagenDetener;
    ImageView imagenRespaldar;
    ImageView imagenActualizar;
    ImageView imagenIniciar;
    String ubicacionActualizador;

    public PosActualizacion(String nombre, String estado, String estadoProceso, ImageView imagenDetener, ImageView imagenRespaldar, ImageView imagenActualizar, ImageView imagenIniciar, String ubicacionActualizador) {
        this.nombre = nombre;
        this.estado = estado;
        this.estadoProceso = estadoProceso;
        this.imagenDetener = imagenDetener;
        this.imagenRespaldar = imagenRespaldar;
        this.imagenActualizar = imagenActualizar;
        this.imagenIniciar = imagenIniciar;
        this.ubicacionActualizador = ubicacionActualizador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public ImageView getImagenDetener() {
        return imagenDetener;
    }

    public void setImagenDetener(ImageView imagenDetener) {
        this.imagenDetener = imagenDetener;
    }

    public ImageView getImagenRespaldar() {
        return imagenRespaldar;
    }

    public void setImagenRespaldar(ImageView imagenRespaldar) {
        this.imagenRespaldar = imagenRespaldar;
    }

    public ImageView getImagenActualizar() {
        return imagenActualizar;
    }

    public void setImagenActualizar(ImageView imagenActualizar) {
        this.imagenActualizar = imagenActualizar;
    }

    public ImageView getImagenIniciar() {
        return imagenIniciar;
    }

    public void setImagenIniciar(ImageView imagenIniciar) {
        this.imagenIniciar = imagenIniciar;
    }

    public String getUbicacionActualizador() {
        return ubicacionActualizador;
    }

    public void setUbicacionActualizador(String ubicacionActualizador) {
        this.ubicacionActualizador = ubicacionActualizador;
    }
}
