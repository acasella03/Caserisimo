package com.caserisimo.modelo;

public class Servicio {
    private int idServicio;
    private String inicio;
    private String fin;
    private int idCamarero;
    private int idMesa;

    public Servicio() {
    }

    public Servicio(int idServicio, String inicio, String fin, int idCamarero, int idMesa) {
        this.idServicio = idServicio;
        this.inicio = inicio;
        this.fin = fin;
        this.idCamarero = idCamarero;
        this.idMesa = idMesa;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public int getIdCamarero() {
        return idCamarero;
    }

    public void setIdCamarero(int idCamarero) {
        this.idCamarero = idCamarero;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }
}
