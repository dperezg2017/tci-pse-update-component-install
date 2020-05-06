package model;

import javafx.scene.image.ImageView;

public class PosInstalacion {

    String nombre;
    String estado;
    String estadoProceso;
    ImageView imagenDetener;
    ImageView imagenRespaldar;
    ImageView imagenInstalar;
    ImageView imagenIniciar;
    String ubicacionInstalador;

    public PosInstalacion(String nombre, String estado, String estadoProceso, ImageView imagenDetener, ImageView imagenRespaldar, ImageView imagenInstalar, ImageView imagenIniciar, String ubicacionInstalador) {
        this.nombre = nombre;
        this.estado = estado;
        this.estadoProceso = estadoProceso;
        this.imagenDetener = imagenDetener;
        this.imagenRespaldar = imagenRespaldar;
        this.imagenInstalar = imagenInstalar;
        this.imagenIniciar = imagenIniciar;
        this.ubicacionInstalador = ubicacionInstalador;
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

    public ImageView getImagenInstalar() {
        return imagenInstalar;
    }

    public void setImagenInstalar(ImageView imagenInstalar) {
        this.imagenInstalar = imagenInstalar;
    }

    public ImageView getImagenIniciar() {
        return imagenIniciar;
    }

    public void setImagenIniciar(ImageView imagenIniciar) {
        this.imagenIniciar = imagenIniciar;
    }

    public String getUbicacionInstalador() {
        return ubicacionInstalador;
    }

    public void setUbicacionInstalador(String ubicacionInstalador) {
        this.ubicacionInstalador = ubicacionInstalador;
    }
}
