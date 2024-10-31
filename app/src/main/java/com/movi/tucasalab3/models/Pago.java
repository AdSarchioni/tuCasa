package com.movi.tucasalab3.models;

import java.util.Date;

public class Pago {
    private int id_Pago;
    private int id_Contrato;
    private double importe;
    private int cuotaPaga;
    private int mesesPagos;
    private String fecha;
    private double multa;
    private int id_Creado_Por;
    private int id_Terminado_Por;
    private int estado_Pago;
    private Inquilino inquilino;
    private Contrato contrato;

    public Pago(int id_Pago, int id_Contrato, double importe, int cuotaPaga, int mesesPagos, String fecha, double multa, int id_Creado_Por, int id_Terminado_Por, int estado_Pago, Inquilino inquilino, Contrato contrato) {
        this.id_Pago = id_Pago;
        this.id_Contrato = id_Contrato;
        this.importe = importe;
        this.cuotaPaga = cuotaPaga;
        this.mesesPagos = mesesPagos;
        this.fecha = fecha;
        this.multa = multa;
        this.id_Creado_Por = id_Creado_Por;
        this.id_Terminado_Por = id_Terminado_Por;
        this.estado_Pago = estado_Pago;
        this.inquilino = inquilino;
        this.contrato = contrato;
    }

    public int getId_Pago() {
        return id_Pago;
    }

    public void setId_Pago(int id_Pago) {
        this.id_Pago = id_Pago;
    }

    public int getId_Contrato() {
        return id_Contrato;
    }

    public void setId_Contrato(int id_Contrato) {
        this.id_Contrato = id_Contrato;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getCuotaPaga() {
        return cuotaPaga;
    }

    public void setCuotaPaga(int cuotaPaga) {
        this.cuotaPaga = cuotaPaga;
    }

    public int getMesesPagos() {
        return mesesPagos;
    }

    public void setMesesPagos(int mesesPagos) {
        this.mesesPagos = mesesPagos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
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

    public int getEstado_Pago() {
        return estado_Pago;
    }

    public void setEstado_Pago(int estado_Pago) {
        this.estado_Pago = estado_Pago;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
