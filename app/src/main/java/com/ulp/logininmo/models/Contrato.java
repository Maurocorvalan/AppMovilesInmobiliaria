package com.ulp.logininmo.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Contrato implements Serializable {
    private int idContrato;
    private String fechaInicio;
    private String fechaFinalizacion;
    private double montoAlquiler;
    private Boolean estado;
    private int idInquilino;
    private int idInmueble;
    private Inquilino inquilino;
    private Inmueble inmueble;

    public Contrato() {
    }

    public Contrato(int idContrato, String fechaInicio, String fechaFinalizacion, double montoAlquiler, Boolean estado, int idInquilino, int idInmueble, Inquilino inquilino, Inmueble inmueble) {
        this.idContrato = idContrato;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.montoAlquiler = montoAlquiler;
        this.estado = estado;
        this.idInquilino = idInquilino;
        this.idInmueble = idInmueble;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public double getMontoAlquiler() {
        return montoAlquiler;
    }

    public void setMontoAlquiler(double montoAlquiler) {
        this.montoAlquiler = montoAlquiler;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "idContrato=" + idContrato +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFinalizacion='" + fechaFinalizacion + '\'' +
                ", montoAlquiler=" + montoAlquiler +
                ", estado=" + estado +
                ", idInquilino=" + idInquilino +
                ", idInmueble=" + idInmueble +
                ", inquilino=" + inquilino +
                ", inmueble=" + inmueble +
                '}';
    }
}