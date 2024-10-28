package com.movi.tucasalab3.models;

public class Tipo_Inmueble {
    private int id_Tipo_Inmueble;
    private String Tipo;
    private int estado_Inmueble;


    public Tipo_Inmueble(int id_Tipo_Inmueble, String tipo, int estado_Inmueble) {
        this.id_Tipo_Inmueble = id_Tipo_Inmueble;
        Tipo = tipo;
        this.estado_Inmueble = estado_Inmueble;
    }

    public int getId_Tipo_Inmueble() {
        return id_Tipo_Inmueble;
    }

    public void setId_Tipo_Inmueble(int id_Tipo_Inmueble) {
        this.id_Tipo_Inmueble = id_Tipo_Inmueble;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public int getEstado_Inmueble() {
        return estado_Inmueble;
    }

    public void setEstado_Inmueble(int estado_Inmueble) {
        this.estado_Inmueble = estado_Inmueble;
    }
}
