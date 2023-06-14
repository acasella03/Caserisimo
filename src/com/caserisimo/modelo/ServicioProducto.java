package com.caserisimo.modelo;

public class ServicioProducto {
    private int idServicioProducto;
    private int idServicio;
    private int idProducto;
    private int cantidad;

    public ServicioProducto() {
    }

    public ServicioProducto(int idServicioProducto, int idServicio, int idProducto, int cantidad) {
        this.idServicioProducto = idServicioProducto;
        this.idServicio = idServicio;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdServicioProducto() {
        return idServicioProducto;
    }

    public void setIdServicioProducto(int idServicioProducto) {
        this.idServicioProducto = idServicioProducto;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
