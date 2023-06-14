package com.caserisimo.modelo;

import java.util.Date;

public class Servicio {
    private int idServicio;
    private Date inicio;
    private Date fin;
    private int idCamarero;
    private int idMesa;

    public Servicio() {
    }

    public Servicio(int idServicio, Date inicio, Date fin, int idCamarero, int idMesa) {
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

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
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
