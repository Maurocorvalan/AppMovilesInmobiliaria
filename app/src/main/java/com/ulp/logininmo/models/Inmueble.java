package com.ulp.logininmo.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class Inmueble implements Serializable {
    private int idInmueble;
    private String direccion;
    private String uso;
    private String tipo;
    private int ambientes;
    private double latitud;
    private double valor;
    private double longitud;
    private int idPropietario;
    private int foto;
    private Boolean disponible;
    private byte[] bait;
    private String imagen;
    private String imagenBase64;

    private Propietario duenio;

    public Inmueble() {
    }

    public Inmueble(int idInmueble, String direccion, String uso, String tipo, int ambientes, double latitud, double valor, double longitud, int idPropietario, int foto, Boolean disponible, byte[] bait, String imagen, String imagenBase64, Propietario duenio) {
        this.idInmueble = idInmueble;
        this.direccion = direccion;
        this.uso = uso;
        this.tipo = tipo;
        this.ambientes = ambientes;
        this.latitud = latitud;
        this.valor = valor;
        this.longitud = longitud;
        this.idPropietario = idPropietario;
        this.foto = foto;
        this.disponible = disponible;
        this.bait = bait;
        this.imagen = imagen;
        this.imagenBase64 = imagenBase64;
        this.duenio = duenio;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public byte[] getBait() {
        return bait;
    }

    public void setBait(byte[] bait) {
        this.bait = bait;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        this.duenio = duenio;
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "idInmueble=" + idInmueble +
                ", direccion='" + direccion + '\'' +
                ", uso='" + uso + '\'' +
                ", tipo='" + tipo + '\'' +
                ", ambientes=" + ambientes +
                ", latitud=" + latitud +
                ", valor=" + valor +
                ", longitud=" + longitud +
                ", idPropietario=" + idPropietario +
                ", foto=" + foto +
                ", disponible=" + disponible +
                ", bait=" + Arrays.toString(bait) +
                ", imagen='" + imagen + '\'' +
                ", imagenBase64='" + imagenBase64 + '\'' +
                ", duenio=" + duenio +
                '}';
    }
}