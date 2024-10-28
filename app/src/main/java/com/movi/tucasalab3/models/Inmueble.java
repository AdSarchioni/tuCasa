package com.movi.tucasalab3.models;

import java.io.Serializable;

public class Inmueble implements Serializable {

private int id_Inmueble;
private String direccion;
private String uso;
private String ambientes;
private String banos;
private String cochera;
private String patio;
private String condicion;
private String descripcion;
private int estado_Inmueble;
private String servicios;
private double precio;
private int superficie;
private int id_Tipo_Inmueble;
private String imagen;
private Tipo_Inmueble tipo;
private Propietario propietario;



    public Inmueble(int id_Inmueble, String direccion, String uso, String ambientes, String banos, String cochera, String patio, String condicion, String descripcion, int estado_Inmueble, double precio, int superficie, Tipo_Inmueble tipo, String imagen, Propietario propietario, int id_Tipo_Inmueble) {
        this.id_Inmueble = id_Inmueble;
        this.direccion = direccion;
        this.uso = uso;
        this.ambientes = ambientes;
        this.banos = banos;
        this.cochera = cochera;

        this.patio = patio;
        this.condicion = condicion;
        this.descripcion = descripcion;
        this.estado_Inmueble = estado_Inmueble;
        this.precio = precio;
        this.superficie = superficie;
        this.tipo = tipo;
        this.imagen = imagen;
        this.propietario = propietario;
        this.id_Tipo_Inmueble = id_Tipo_Inmueble;
    }

    public Inmueble(int id_Inmueble, String direccion, String uso, String ambientes, double precio, int estado_Inmueble, String imagen, String condicion, String servicios, int id_Tipo_Inmueble) {
        this.id_Inmueble = id_Inmueble;
        this.direccion = direccion;
        this.uso = uso;
        this.ambientes = ambientes;
        this.precio = precio;
        this.estado_Inmueble = estado_Inmueble;
        this.imagen = imagen;
        this.condicion = condicion;
        this.servicios = servicios;
        this.id_Tipo_Inmueble = id_Tipo_Inmueble;
    }
   public Inmueble() {
   }
    public int getId_Inmueble() {
        return id_Inmueble;
    }

    public void setId_Inmueble(int id_Inmueble) {
        this.id_Inmueble = id_Inmueble;
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

    public String getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(String ambientes) {
        this.ambientes = ambientes;
    }

    public String getBanos() {
        return banos;
    }

    public void setBanos(String banos) {
        this.banos = banos;
    }

    public String getCochera() {
        return cochera;
    }

    public void setCochera(String cochera) {
        this.cochera = cochera;
    }

    public String getPatio() {
        return patio;
    }

    public void setPatio(String patio) {
        this.patio = patio;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado_Inmueble() {
        return estado_Inmueble;
    }

    public void setEstado_Inmueble(int estado_Inmueble) {
        this.estado_Inmueble = estado_Inmueble;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public Tipo_Inmueble getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_Inmueble tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public int getId_Tipo_Inmueble() {
        return id_Tipo_Inmueble;
    }

    public void setId_Tipo_Inmueble(int id_Tipo_Inmueble) {
        this.id_Tipo_Inmueble = id_Tipo_Inmueble;
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "id_inmueble=" + id_Inmueble +
                ", direccion='" + direccion + '\'' +
                ", uso='" + uso + '\'' +
                ", ambientes='" + ambientes + '\'' +
                ", banos='" + banos + '\'' +
                ", cochera='" + cochera + '\'' +

                ", patio='" + patio + '\'' +
                ", condicion='" + condicion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado_Inmueble='" + estado_Inmueble + '\'' +
                ", precio=" + precio +
                ", superficie=" + superficie +
                ", tipo=" + tipo +
                ", imagen='" + imagen + '\'' +
                ", propietario=" + propietario +
                ", servicios=" + servicios +
                '}';
    }
}
