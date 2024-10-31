package com.movi.tucasalab3.models;

import java.io.Serializable;
import java.util.Date;

public class Contrato implements Serializable {
    private int id_Contrato;
    private int id_Inmueble;
    private int id_Inquilino;
    private String fecha_Inicio;
    private String fecha_Finalizacion;
    private double monto;
    private int id_Creado_Por;
    private int id_Terminado_Por;
    private int estado_Contrato;
    private int meses;
    private Inmueble inmueble;
    private Inquilino inquilino;
    private Pago pago;

    public Contrato(int id_Contrato, int id_Inmueble, int id_Inquilino, String fecha_Inicio, String fecha_Finalizacion, double monto, int id_Creado_Por, int id_Terminado_Por, int estado_Contrato,int meses, Inmueble inmueble, Inquilino inquilino, Pago pago) {
        this.id_Contrato = id_Contrato;
        this.id_Inmueble = id_Inmueble;
        this.id_Inquilino = id_Inquilino;
        this.fecha_Inicio = fecha_Inicio;
        this.fecha_Finalizacion = fecha_Finalizacion;
        this.monto = monto;
        this.id_Creado_Por = id_Creado_Por;
        this.id_Terminado_Por = id_Terminado_Por;
        this.estado_Contrato = estado_Contrato;
        this.meses = meses;
        this.inmueble = inmueble;
        this.inquilino = inquilino;
        this.pago = pago;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public int getId_Contrato() {
        return id_Contrato;
    }

    public void setId_Contrato(int id_Contrato) {
        this.id_Contrato = id_Contrato;
    }

    public int getId_Inmueble() {
        return id_Inmueble;
    }

    public void setId_Inmueble(int id_Inmueble) {
        this.id_Inmueble = id_Inmueble;
    }

    public int getId_Inquilino() {
        return id_Inquilino;
    }

    public void setId_Inquilino(int id_Inquilino) {
        this.id_Inquilino = id_Inquilino;
    }

    public String getFecha_Inicio() {
        return fecha_Inicio;
    }

    public void setFecha_Inicio(String fecha_Inicio) {
        this.fecha_Inicio = fecha_Inicio;
    }

    public String getFecha_Finalizacion() {
        return fecha_Finalizacion;
    }

    public void setFecha_Finalizacion(String fecha_Finalizacion) {
        this.fecha_Finalizacion = fecha_Finalizacion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getId_Creado_Por() {
        return id_Creado_Por;
    }

    public void setId_Creado_Por(int id_Creado_Por) {
        this.id_Creado_Por = id_Creado_Por;
    }

    public int getId_Terminado_Por() {
        return id_Terminado_Por;
    }

    public void setId_Terminado_Por(int id_Terminado_Por) {
        this.id_Terminado_Por = id_Terminado_Por;
    }

    public int getEstado_Contrato() {
        return estado_Contrato;
    }

    public void setEstado_Contrato(int estado_Contrato) {
        this.estado_Contrato = estado_Contrato;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
