package com.movi.tucasalab3.models;

import java.io.Serializable;

public class Inquilino implements Serializable {

    private int id_Inquilino;
    private int dni;
    private String apellido;
    private String nombre;
    private String telefono;
    private String email;
    private int Estado_Inquilino;

    public Inquilino(int id_Inquilino, int dni, String apellido, String nombre, String telefono, String email, int estado_Inquilino) {
        this.id_Inquilino = id_Inquilino;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        Estado_Inquilino = estado_Inquilino;
    }
    //Getters y Setters

    public int getId_Inquilino() {
        return id_Inquilino;
    }

    public void setId_Inquilino(int id_Inquilino) {
        this.id_Inquilino = id_Inquilino;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEstado_Inquilino() {
        return Estado_Inquilino;
    }

    public void setEstado_Inquilino(int estado_Inquilino) {
        Estado_Inquilino = estado_Inquilino;
    }

    @Override
    public String toString() {
        return "Inquilino{" +
                "id_Inquilino=" + id_Inquilino +
                ", dni=" + dni +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", Estado_Inquilino=" + Estado_Inquilino +
                '}';
    }
}
